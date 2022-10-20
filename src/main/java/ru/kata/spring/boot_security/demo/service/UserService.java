package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void addUser(User user, long[] listRoles);

    List<User> getUsers();

    User getUserById(Long id);

    void updateUser(User user, long[] roleId);

    void deleteUser(Long id);

    User getUserName(String name);
}
