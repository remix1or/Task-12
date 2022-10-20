package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public String saveUser(@ModelAttribute("newUser") User user,
                           @RequestParam("listRoles") long[] role_id) {
        userService.addUser(user, role_id);
        return "redirect:/admin";
    }

    @GetMapping()
    public String getUsers(Model model, Principal principal) {
        model.addAttribute("admin", userService.getUserName(principal.getName()));
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("user", new User());
        return "admin";
    }

    @PatchMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("listRoles") long[] role_id){
        userService.updateUser(user, role_id);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
