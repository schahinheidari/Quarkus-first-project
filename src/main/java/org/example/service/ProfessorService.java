package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.enums.AcademicRank;
import org.example.model.Professor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProfessorService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void save(Professor professor) {
        entityManager.persist(professor);
    }

    @Transactional
    public Professor update(Professor professor) {
        return entityManager.merge(professor);
    }

    @Transactional
    public void delete(long professorId) {
        Professor prof = entityManager.find(Professor.class, professorId);
        if (prof != null){
            entityManager.remove(prof);
        }
    }
    public Optional<Professor> findProfessorById(long professorId) {
        Professor prof = entityManager.find(Professor.class, professorId);
        return Optional.ofNullable(prof);
    }
    public List<Professor> findAllProfessors() {
        return entityManager.createQuery("SELECT p FROM Professor p", Professor.class).getResultList();
    }

    public Optional<Professor> findProfessorByCoder(int code) {
        List<Professor> results = entityManager.createQuery(
                        "SELECT p FROM Professor p WHERE p.code = :code", Professor.class)
                .setParameter("code", code)
                .getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public List<Professor> findStudentsByAcademicRank(AcademicRank academicRank) {
        return entityManager.createQuery(
                        "SELECT p FROM Professor p WHERE p.academicRank = :academicRank", Professor.class)
                .setParameter("academicRank", academicRank)
                .getResultList();
    }

}
