package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.model.User;

@ApplicationScoped
public class UserDao implements PanacheRepository<User> {

    public User findByName(String name) {
        return find("name", name).firstResult();
    }

    public void save(User user) {

    }
}
