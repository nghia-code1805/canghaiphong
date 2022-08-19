package io.github.tubean.myspringcrud.controller;

import io.github.tubean.myspringcrud.entity.CreateFolder;
import io.github.tubean.myspringcrud.service.CreateFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Controller
public class CreateFolderController {
    @Autowired
    private CreateFolderService createFolderService;

    @RequestMapping(value = "createContainer")
    public String create(Model model){
        model.addAttribute("createContainer", new CreateFolder());
        return "createContainer";
    }

    @RequestMapping(value = "saveFolder", method = RequestMethod.POST)
    public String save(CreateFolder createFolder) throws IOException, URISyntaxException {
//        String nameFolder = createFolder.getFolderName();
//        File folder = createResourceSubFolder(nameFolder);
        Date date = new Date();
        createFolder.setCreateDate(date);
        createFolderService.save(createFolder);
        return "redirect:/";
    }

    private static File createResourceSubFolder(String folderName) throws URISyntaxException, IOException {
        java.net.URL url = CreateFolderController.class.getResource("/static/");
        File fullPathToSubfolder = new File(url.toURI()).getAbsoluteFile();
        String projectFolder = fullPathToSubfolder.getAbsolutePath().split("target")[0];
        File testResultsFolder = new File(projectFolder + "src/main/resources/static/" + folderName);
        if (!testResultsFolder.exists()) {
            testResultsFolder.mkdir();
        }
        return testResultsFolder;
    }




}
