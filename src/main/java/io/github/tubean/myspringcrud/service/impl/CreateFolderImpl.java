package io.github.tubean.myspringcrud.service.impl;

import io.github.tubean.myspringcrud.entity.CreateFolder;
import io.github.tubean.myspringcrud.repository.CreateFolderRepository;
import io.github.tubean.myspringcrud.service.CreateFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateFolderImpl implements CreateFolderService {
    @Autowired
    private CreateFolderRepository createFolderRepository;

    @Override
    public void save(CreateFolder createFolder) {
        createFolderRepository.save(createFolder);
    }
}
