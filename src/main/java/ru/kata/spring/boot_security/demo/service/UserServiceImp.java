package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService{

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserDao userDao,@Lazy PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    @Transactional
    public void addUser(User user) {
        User newUser = userDao.getUsersByName(user.getName());
        if(newUser == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.addUser(user);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void updateUser(int id, User user) {
    userDao.updateUser(id, user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
    userDao.deleteUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserName(String name) {
        return userDao.getUsersByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return user;
    }
}
