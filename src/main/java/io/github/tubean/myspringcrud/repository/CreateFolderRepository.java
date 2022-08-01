package io.github.tubean.myspringcrud.repository;

import io.github.tubean.myspringcrud.entity.CreateFolder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreateFolderRepository extends CrudRepository<CreateFolder, Long> {
}
