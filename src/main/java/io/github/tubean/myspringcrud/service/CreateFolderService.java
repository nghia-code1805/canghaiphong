package io.github.tubean.myspringcrud.service;

import io.github.tubean.myspringcrud.entity.CreateFolder;

import java.util.List;
import java.util.Optional;

public interface CreateFolderService {

    List<CreateFolder> getAll();

    void save(CreateFolder createFolder);

    Optional<CreateFolder> findById(Long id);

    public List<CreateFolder> findAllContainer(String keyword);

//    List<CreateFolder> findBy(Long id);
}
