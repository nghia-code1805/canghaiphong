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
import java.util.List;
import java.util.Optional;

@Controller
public class viewFolder {

    @Autowired
    private CreateFolderService createFolderService;

    @RequestMapping("/")
    public String index(Model model, @Param("keyword") String keyword) {

        if (keyword != null) {
            model.addAttribute("folders", createFolderService.findAllContainer(keyword));
        } else {
            model.addAttribute("folders", createFolderService.getAll());
        }
        return "viewFolder";
    }

    @RequestMapping(value = "viewFolder")
    public String create(Model model) {
        model.addAttribute("viewFolder", new CreateFolder());
        return "viewFolder";
    }
}
