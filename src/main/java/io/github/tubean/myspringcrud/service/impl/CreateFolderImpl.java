package io.github.tubean.myspringcrud.service.impl;

import io.github.tubean.myspringcrud.entity.CreateFolder;
import io.github.tubean.myspringcrud.repository.CreateFolderRepository;
import io.github.tubean.myspringcrud.service.CreateFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreateFolderImpl implements CreateFolderService {
    @Autowired
    private CreateFolderRepository createFolderRepository;

    @Override
    public List<CreateFolder> getAll() {
        return (List<CreateFolder>) createFolderRepository.findAll();
    }

    @Override
    public void save(CreateFolder createFolder) {
        createFolderRepository.save(createFolder);
    }

    @Override
    public Optional<CreateFolder> findById(Long id) {
        return createFolderRepository.findById(id);
    }

    @Override
    public List<CreateFolder> findAllContainer(String keyword) {
        return createFolderRepository.search(keyword);
    }

//    @Override
//    public List<CreateFolder> findBy(Long id) {
//        return createFolderRepository.;
//    }
}
