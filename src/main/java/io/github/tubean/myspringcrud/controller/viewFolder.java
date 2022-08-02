package io.github.tubean.myspringcrud.controller;

import io.github.tubean.myspringcrud.entity.CreateFolder;
import io.github.tubean.myspringcrud.entity.Image;
import io.github.tubean.myspringcrud.service.CreateFolderService;
import io.github.tubean.myspringcrud.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class viewFolder {

    @Value("${uploadDir}")
    private String uploadFolder;

    @Autowired
    private CreateFolderService createFolderService;

    @Autowired
    private ImageService imageService;

    @RequestMapping("/viewFolders")
    public String index(Model model){
        List<CreateFolder> createFolders = createFolderService.getAll();
        model.addAttribute("folders", createFolders);
        return "viewFolder";
    }

    @RequestMapping(value = "viewFolder")
    public String create(Model model) {
        model.addAttribute("viewFolder", new CreateFolder());
        return "viewFolder";
    }

    @RequestMapping(value = "/addImage", method = RequestMethod.GET)
    public String addImage(@RequestParam("id") Long id, Model model){
        Optional<CreateFolder> idFolder = createFolderService.findById(id);
        idFolder.ifPresent(folder -> model.addAttribute("folder", folder));
        return "addImage";
    }

    @PostMapping("/image/saveImageDetails")
    public @ResponseBody
    ResponseEntity<?> createProduct(Model model, HttpServletRequest request,final @RequestParam("image") MultipartFile file, @RequestParam("id") Long id) {
        try {
            String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory, fileName).toString();
            if (fileName == null || fileName.contains("..")) {
                model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
                return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
            }
            Date createDate = new Date();
            try {
                File dir = new File(uploadDirectory);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // Save the file locally
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Optional<CreateFolder> idFolder = createFolderService.findById(id);
             String nameFolder = idFolder.get().getFolderName();

            byte[] imageData = file.getBytes();
            Image imageGallery = new Image();
            imageGallery.setName(fileName);
            imageGallery.setImage(imageData);
            imageGallery.setFolderName(nameFolder);
            imageGallery.setCreateDate(createDate);
            imageService.saveImage(imageGallery);
//            log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
            return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
//            log.info("Exception: " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
