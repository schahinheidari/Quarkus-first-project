package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.model.User;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class UserService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Transactional
    public void delete(long userId){
        User user = entityManager.find(User.class, userId);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    public Optional<User> findUserById(long userId){
        User user = entityManager.find(User.class, userId);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public List<User> findAllUsers(){
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public Optional<User> findUserByNationalCode(long nationalCode){
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.nationalCode = :nationalCode", User.class)
                .setParameter("nationalCode", nationalCode)
                .getResultList();
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }
}
