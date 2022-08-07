package io.github.tubean.myspringcrud.controller;

import io.github.tubean.myspringcrud.entity.CreateFolder;
import io.github.tubean.myspringcrud.entity.Image;
import io.github.tubean.myspringcrud.service.CreateFolderService;
import io.github.tubean.myspringcrud.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ImageController {

    @Autowired
    private CreateFolderService createFolderService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/showAllImage", method = RequestMethod.GET)
    public String addImage(@RequestParam("id") Long id, Model model){
        Optional<CreateFolder> idFolder = createFolderService.findById(id);
        String nameFolder = idFolder.get().getFolderName();
        List<Image> getAllByFolderName = imageService.getAllFolder(nameFolder);
        model.addAttribute("showAllImage", getAllByFolderName);
        return "showAllImage";
    }

    @GetMapping("/image/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id")Long id, HttpServletResponse response , Optional<Image> imageFindById)
        throws ServletException, IOException{
        imageFindById = imageService.getImageById(id);
        response.setContentType("image/jpeg, image/jpg, image/png");
        response.getOutputStream().write(imageFindById.get().getImage());
        response.getOutputStream().close();
    }

    @GetMapping("/image/imageDetails")
    String showImageDetails(@RequestParam("id") Long id ,Optional<Image> image, Model model){
        try {
            if (id != 0){
                image = imageService.getImageById(id);

                if (image.isPresent()){
                    model.addAttribute("id", image.get().getId());
                    model.addAttribute("name", image.get().getName());
                    model.addAttribute("nameFolder", image.get().getFolderName());
                    return "imageDetails";
                }
                return "redirect:/showAllImage";
            }
            return "redirect:/showAllImage";
        } catch (Exception ex){
            ex.printStackTrace();
            return "redirect:/showAllImage";
        }
    }
}
