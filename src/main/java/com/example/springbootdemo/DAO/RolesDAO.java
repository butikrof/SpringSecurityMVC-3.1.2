package com.example.springbootdemo.DAO;

import com.example.springbootdemo.model.Role;

import java.util.List;

public interface RolesDAO {
    Role getByName(String name);
    List<Role> getAllRoles();
    void addRole(Role role);
}
