package com.example.springbootdemo.service;

import com.example.springbootdemo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);


    void update(int id, User updatedPerson, String[] roleNames);//

    void updateUser(int id, User updatedPerson);


    void delete(int id);
    boolean saveUser(User user, String[] roleNames);
    User findUserByUsername(String username);
    void save(User user);
}
