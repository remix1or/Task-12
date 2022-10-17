package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Role role) {
        entityManager.persist(role);
        entityManager.flush();
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(getRoleById(id));
    }

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role createRole(String name, Long id) {
        Role role = entityManager.createQuery("SELECT r FROM Role r where r.roleName = :roleName", Role.class)
                .setParameter("roleName", name)
                .getSingleResult();
        return role;
    }

    @Override
    public Role getRoleByName(String roleName) {
        return entityManager.find(Role.class, roleName);
    }

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("select r FROM Role r", Role.class).getResultList();

    }
}


