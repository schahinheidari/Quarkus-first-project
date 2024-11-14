package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.model.Professor;

@ApplicationScoped
public class ProfessorDao implements PanacheRepository<Professor> {

}
