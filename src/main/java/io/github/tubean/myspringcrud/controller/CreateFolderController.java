package io.github.tubean.myspringcrud.controller;

import io.github.tubean.myspringcrud.entity.CreateFolder;
import io.github.tubean.myspringcrud.service.CreateFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CreateFolderController {
    @Autowired
    private CreateFolderService createFolderService;



    @RequestMapping(value = "createFolder", method = RequestMethod.POST)
    public String save(CreateFolder createFolder){
        createFolderService.save(createFolder);
        return "nghiant";
    }
}
