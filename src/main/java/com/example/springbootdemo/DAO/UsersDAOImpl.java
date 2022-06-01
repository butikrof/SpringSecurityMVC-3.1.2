package com.example.springbootdemo.DAO;

import com.example.springbootdemo.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UsersDAOImpl implements UsersDAO{

    private final PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    public UsersDAOImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(int id, User updatedPerson) {
        User userToBeUpdated = getUserById(id);
        updatedPerson.setId(id);
        if(!userToBeUpdated.getPassword().equals(updatedPerson.getPassword())) {
            updatedPerson.setPassword(passwordEncoder.encode(updatedPerson.getPassword()));
        }
        entityManager.merge(updatedPerson);
    }




    @Override
    public void delete(int id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public boolean saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
        return true;
    }

    @Override
    public User findUserByUsername(String username) {
        Query query = entityManager.createQuery("select u from User u where u.username = :username");
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }
}
