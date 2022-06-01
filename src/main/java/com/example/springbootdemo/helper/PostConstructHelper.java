package com.example.springbootdemo.helper;

import com.example.springbootdemo.model.Role;
import com.example.springbootdemo.model.User;
import com.example.springbootdemo.service.RoleService;
import com.example.springbootdemo.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class PostConstructHelper {

    private UserService userService;
    private RoleService roleService;

    public PostConstructHelper(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

 //  @PostConstruct
    private void addUsersBeforeStart() {
        User admin = new User("Admin","Adminар", "Adminov@mail.ru", "SpB, profsuz 34-56", 892427835, "admin", "admin");
       User user = new User("User","Userov", "Userov@mail.ru", "SpB", 2053434, "user", "user");
        Role roleAdmin = new Role("ROLE_ADMIN");
       Role roleUser = new Role("ROLE_USER");
        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);
        Set<Role> adminRoleSet = new HashSet<>();
        Set<Role> userRoleSet = new HashSet<>();
        adminRoleSet.add(roleAdmin);
        userRoleSet.add(roleUser);
        admin.setRoles(adminRoleSet);
        user.setRoles(userRoleSet);
        userService.save(admin);
        userService.save(user);
    }
}
