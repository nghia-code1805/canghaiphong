package io.github.tubean.myspringcrud.repository;

import io.github.tubean.myspringcrud.entity.CreateFolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreateFolderRepository extends JpaRepository<CreateFolder, Long> {
    @Query(value = "select * from folder cf where cf.FOLDER_NAME like CONCAT('%',:keyword,'%')", nativeQuery = true)
    List<CreateFolder> search(@Param("keyword")String keyword);
}
