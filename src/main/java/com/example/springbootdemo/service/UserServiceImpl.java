package com.example.springbootdemo.service;

import com.example.springbootdemo.DAO.RolesDAO;
import com.example.springbootdemo.DAO.UsersDAO;
import com.example.springbootdemo.model.Role;
import com.example.springbootdemo.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UsersDAO usersDAO;
    private final RolesDAO rolesDAO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UsersDAO usersDAO, RolesDAO rolesDAO, PasswordEncoder passwordEncoder) {
        this.usersDAO = usersDAO;
        this.rolesDAO = rolesDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return usersDAO.getAllUsers();
    }

    @Transactional(readOnly = true)
    public User getUserById(int id) {
        return usersDAO.getUserById(id);
    }


    public void update(int id, User updatedPerson, String[] roleNames) {
        Set<Role> roles = new HashSet<>();
        for (String role : roleNames) {
            roles.add(rolesDAO.getByName(role));
        }
        updatedPerson.setRoles(roles);
        usersDAO.update(id, updatedPerson);
    }


    public void updateUser(int id, User updatedPerson) {
        Set<Role> roles = new HashSet<>();

        String role = "ROLE_USER";

        roles.add(rolesDAO.getByName(role));

        updatedPerson.setRoles(roles);
        usersDAO.update(id, updatedPerson);
    }











    public void delete(int id) {
        usersDAO.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersDAO.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public boolean saveUser(User user, String[] roleNames) {
        Set<Role> roles = new HashSet<>();
        for (String role : roleNames) {
            roles.add(rolesDAO.getByName(role));
        }
        user.setRoles(roles);
        return usersDAO.saveUser(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return usersDAO.findUserByUsername(username);
    }

    @Override
    public void save(User user) {
        usersDAO.save(user);
    }

}
