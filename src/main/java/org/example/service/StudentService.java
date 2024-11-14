package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.enums.AcademicLevel;
import org.example.enums.Branch;
import org.example.model.Student;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StudentService {

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
    public void delete(long studentId) {
        Student student = entityManager.find(Student.class, studentId);
        if (student != null){
            entityManager.remove(student);
        }
    }

    public Optional<Student> findStudentById(long studentId) {
        Student student = entityManager.find(Student.class, studentId);
        return Optional.ofNullable(student);
    }

    public List<Student> findAllStudents() {
        return entityManager.createQuery("SELECT s FROM Student s").getResultList();
    }

    public Optional<Student> findStudentByStdNumber(long stdNumber) {
        List<Student> results = entityManager.createQuery(
                        "SELECT s FROM Student s WHERE s.stdNumber = :stdNumber", Student.class)
                .setParameter("stdNumber", stdNumber)
                .getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public List<Student> findStudentsByAcademicLevel(AcademicLevel academicLevel) {
        return entityManager.createQuery(
                        "SELECT s FROM Student s WHERE s.academicLevel = :academicLevel", Student.class)
                .setParameter("academicLevel", academicLevel)
                .getResultList();
    }

    public List<Student> findStudentsByBranch(Branch branch) {
        return entityManager.createQuery(
                        "SELECT s FROM Student s WHERE s.branch = :branch", Student.class)
                .setParameter("branch", branch)
                .getResultList();
    }

}
