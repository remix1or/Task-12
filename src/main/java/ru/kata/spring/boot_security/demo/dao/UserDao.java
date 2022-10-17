package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    List<User> getUsers();
    User getUserById(int id);
    User getUsersByName(String name);
    void updateUser(User user);
    void deleteUser(int id);
}
