package org.example.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.model.Student;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StudentDao {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Transactional
    public Student update(Student student) {
        return entityManager.merge(student);
    }

    @Transactional
    public void delete(Student student) {
        if (!entityManager.contains(student)){
            student = entityManager.merge(student);
        }
        entityManager.remove(student);
    }

    public Optional<Student> findById(long id) {
        Student student = entityManager.find(Student.class, id);
        return student != null ? Optional.of(student) : Optional.empty();
    }

    public List<Student> findAll(){
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    public Optional<Student> findByStudentNumber(long studentNumber) {
        List<Student> results = entityManager.createQuery(
                "SELECT s FROM Student s WHERE s.stdNumber = :studentNumber", Student.class)
                .setParameter("studentNumber", studentNumber)
                .getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }
}
