package io.github.tubean.myspringcrud.service;

import io.github.tubean.myspringcrud.entity.CreateFolder;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CreateFolderService {

    List<CreateFolder> getAll();

    void save(CreateFolder createFolder);

    Optional<CreateFolder> findById(Long id);

    void deleteContainer(Long id);

    public List<CreateFolder> findAllContainer(String keyword);

//    Page<CreateFolder> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

//    public Page<CreateFolder> findPage(int pageNumber);

//    List<CreateFolder> findBy(Long id);

//    public Page<CreateFolder> listAll(int pageNum, String sortField, String sortDir, String keyword);

}
