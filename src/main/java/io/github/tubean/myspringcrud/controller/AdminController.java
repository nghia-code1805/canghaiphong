package io.github.tubean.myspringcrud.controller;

import io.github.tubean.myspringcrud.entity.CreateFolder;
import io.github.tubean.myspringcrud.entity.Image;
import io.github.tubean.myspringcrud.service.CreateFolderService;
import io.github.tubean.myspringcrud.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

@Controller
public class AdminController {

    @Value("${uploadDir}")
    private String uploadFolder;

    @Autowired
    private CreateFolderService createFolderService;

    @Autowired
    private ImageService imageService;

    @RequestMapping("/adminContainer")
    public String index(Model model, @Param("keyword") String keyword){
        if (keyword != null){
            model.addAttribute("folders", createFolderService.findAllContainer(keyword));
        } else {
            model.addAttribute("folders", createFolderService.getAll());
        }
        return "adminViewContainer";
    }

    @RequestMapping(value = "adminViewContainer")
    public String create (Model model){
        model.addAttribute("adminViewContainer", new CreateFolder());
        return "adminViewContainer";
    }

    @RequestMapping(value = "/addImage", method = RequestMethod.GET)
    public String addImage(@RequestParam("id") Long id, Model model) {
        Optional<CreateFolder> idFolder = createFolderService.findById(id);
        idFolder.ifPresent(folder -> model.addAttribute("folder", folder));
        return "addImage";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long id, Model model ){
        Optional<CreateFolder> idFolderDelete = createFolderService.findById(id);
        Long idDelete = idFolderDelete.get().getId();
        createFolderService.deleteContainer(idDelete);
        return "redirect:/";
    }

    @PostMapping("/image/saveImageDetails")
    public @ResponseBody
    ResponseEntity<?> createProduct(Model model, HttpServletRequest request, final @RequestParam("image") MultipartFile[] file, @RequestParam("id") Long id) throws IOException {
        try {
            String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);

            for (int i = 0; i < file.length; i++) {
                MultipartFile files = file[i];
                byte[] bytes = files.getBytes();
                String fileName = files.getOriginalFilename();
                String filePath = Paths.get(uploadDirectory, fileName).toString();
                if (fileName == null || fileName.contains("..")) {
                    model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
                    return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
                }
                try {
                    File dir = new File(uploadDirectory);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    // Save the file locally
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                    stream.write(bytes);
                    stream.close();

                    Date createDate = new Date();
                    Optional<CreateFolder> idFolder = createFolderService.findById(id);
                    String nameFolder = idFolder.get().getFolderName();

                    Image imageGallery = new Image();
                    imageGallery.setName(fileName);
                    imageGallery.setImage(bytes);
                    imageGallery.setFolderName(nameFolder);
                    imageGallery.setCreateDate(createDate);
                    imageService.saveImage(imageGallery);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return new ResponseEntity<>("Product Saved With File - ", HttpStatus.OK);
    }
}
