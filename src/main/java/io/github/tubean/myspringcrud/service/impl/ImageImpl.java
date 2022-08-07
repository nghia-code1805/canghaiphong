package io.github.tubean.myspringcrud.service.impl;

import io.github.tubean.myspringcrud.entity.Image;
import io.github.tubean.myspringcrud.repository.ImageRepository;
import io.github.tubean.myspringcrud.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void saveImage(Image image) {
        imageRepository.save(image);
    }

//    @Override
//    public Optional<Image> getAllFolderName(String folderName) {
//        return imageRepository.getAllByFolderName(folderName);
//    }


    @Override
    public List<Image> getAllFolder(String folderName){
        return imageRepository.getAllByFolderName(folderName);
    }

    @Override
    public Optional<Image> getImageById(Long id) {
        return imageRepository.findById(id);
    }
}
