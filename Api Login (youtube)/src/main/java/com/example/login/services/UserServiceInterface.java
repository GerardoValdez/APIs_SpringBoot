package com.example.login.services;

import com.example.login.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserServiceInterface {
    User authenticateUser(User user);
    boolean noRegisteredUser(User user);
    User createUser(User user);
    List<User> getAllUsers();
    User findById(Long id);
    boolean deleteUser(Long id);
    User updateUser(User user, Long id);
}
