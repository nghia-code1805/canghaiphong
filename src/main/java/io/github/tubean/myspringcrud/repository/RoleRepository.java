package io.github.tubean.myspringcrud.repository;

import io.github.tubean.myspringcrud.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
