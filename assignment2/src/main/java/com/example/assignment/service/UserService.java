package com.example.assignment.service;

import com.example.assignment.entity.User;

import java.util.List;

public interface UserService {
    boolean checkUser(String username, String password);
    List<User> getAll();
    void create(User u);
    void update(User u);
    void deleteById(String id);
    User getById(String id);
    boolean isAdmin(String username);
}
