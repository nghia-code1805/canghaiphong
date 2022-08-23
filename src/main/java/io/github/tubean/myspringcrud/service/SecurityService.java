package io.github.tubean.myspringcrud.service;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
