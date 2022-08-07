package io.github.tubean.myspringcrud.service;

import io.github.tubean.myspringcrud.entity.Image;

import java.util.List;
import java.util.Optional;

public interface ImageService {

    void saveImage(Image image);

//    Optional<Image> getAllFolderName(String folderName);
    List<Image> getAllFolder(String folderName);

    public Optional<Image> getImageById(Long id);

}
