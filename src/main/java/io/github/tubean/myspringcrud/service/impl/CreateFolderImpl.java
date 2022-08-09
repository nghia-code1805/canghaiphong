package io.github.tubean.myspringcrud.service.impl;

import io.github.tubean.myspringcrud.entity.CreateFolder;
import io.github.tubean.myspringcrud.repository.CreateFolderRepository;
import io.github.tubean.myspringcrud.service.CreateFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

//    @Override
//    public Page<CreateFolder> listAll(int pageNum, String sortField, String sortDir, String keyword) {
//        Pageable pageable = PageRequest.of(pageNum - 1, 5,
//                sortDir.equals("asc") ? Sort.by(sortField).ascending()
//                        : Sort.by(sortField).descending()
//        );
//        if (keyword != null){
//            return createFolderRepository.search(keyword, pageable);
//        } else {
//            return createFolderRepository.findAll(pageable);
//        }
//    }

    @Override
    public List<CreateFolder> findAllContainer(String keyword) {
        return createFolderRepository.search(keyword);
    }

//    @Override
//    public Page<CreateFolder> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
//        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
//                Sort.by(sortField).descending();
//
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
//        return this.createFolderRepository.findAll(pageable);
//    }

//    @Override
//    public List<CreateFolder> findBy(Long id) {
//        return createFolderRepository.;
//    }

}
