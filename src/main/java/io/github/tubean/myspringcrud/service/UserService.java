package io.github.tubean.myspringcrud.service;

import io.github.tubean.myspringcrud.entity.User;

public interface UserService {
  void save(User user);

  User findByUsername(String username);
}
