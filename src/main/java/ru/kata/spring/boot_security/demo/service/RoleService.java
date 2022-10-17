package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    void save(Role role);
    void delete(Long id);
    Role getRoleById(Long id);
    Role createRole(String name, Long id);
    Role getRoleByName(String roleName);
    List<Role> getRoles();
}
