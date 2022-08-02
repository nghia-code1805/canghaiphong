package io.github.tubean.myspringcrud.repository;

import io.github.tubean.myspringcrud.entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
}
