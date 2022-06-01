package com.example.springbootdemo.service;

import com.example.springbootdemo.DAO.RolesDAO;
import com.example.springbootdemo.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private final RolesDAO rolesDAO;

    public RoleServiceImpl(RolesDAO rolesDAO) {
        this.rolesDAO = rolesDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getByName(String name) {
        return rolesDAO.getByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return rolesDAO.getAllRoles();
    }

    @Override
    public void addRole(Role role) {
        rolesDAO.addRole(role);
    }
}
