package io.github.tubean.myspringcrud.repository;

import io.github.tubean.myspringcrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
