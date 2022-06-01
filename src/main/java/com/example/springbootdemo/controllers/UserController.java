package com.example.springbootdemo.controllers;

import com.example.springbootdemo.model.Role;
import com.example.springbootdemo.model.User;
import com.example.springbootdemo.service.RoleService;
import com.example.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;


   @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails,
                              Model model){
        String username = userDetails.getUsername();
        User user = userService.findUserByUsername(username);
        model.addAttribute("user", user);
        return "user";
    }



    @GetMapping("/{id}/editUser")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "editUser";
    }





    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid User person, BindingResult bindingResult, @PathVariable("id") int id, @RequestParam(value = "role") String[] roleNames) {
       if (bindingResult.hasErrors()) {
            return "editUser";
        }
        userService.updateUser(id, person);
        return "redirect:/login";
    }



    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/login";
    }

}
