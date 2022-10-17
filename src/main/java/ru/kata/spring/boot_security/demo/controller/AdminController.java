package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleService.getRoles());
        return "admin";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @GetMapping("/update/{id}")
    public String editUser(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getRoles());
        return "update";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") int id){
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(value = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
