package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u ", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUsersByName(String name) {
        return entityManager.createQuery("SELECT u FROM User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void updateUser(int id, User user) {
        User userToBeUpdated = entityManager.find(User.class, id);
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setLastname(user.getLastname());
        userToBeUpdated.setAge(user.getAge());
        userToBeUpdated.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUserById(id));
        entityManager.flush();
    }


}
