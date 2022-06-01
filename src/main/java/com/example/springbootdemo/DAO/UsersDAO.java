package com.example.springbootdemo.DAO;

import com.example.springbootdemo.model.User;

import java.util.List;

public interface UsersDAO {
    List<User> getAllUsers();
    User getUserById(int id);



    void update(int id, User updatedPerson);




    void delete(int id);
    boolean saveUser(User user);
    User findUserByUsername(String username);
    void save(User user);
}
