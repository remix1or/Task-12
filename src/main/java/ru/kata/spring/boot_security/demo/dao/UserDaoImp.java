package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


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
    public void updateUser(User user) {
        User userToBeUpdated = entityManager.find(User.class, user.getId());
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setLastname(user.getLastname());
        userToBeUpdated.setAge(user.getAge());
    }

    @Override
    public void deleteUser(int id) {
        User user = new User();
        user.setId(id);
        entityManager.remove(user);
        entityManager.flush();
    }


}
