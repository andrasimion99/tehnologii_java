package com.lab3.lab3.DAO;

import com.lab3.lab3.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class StudentDao {
    private static StudentDao instance;
    EntityManagerFactory emf;
    EntityManager entityManager;

    public static StudentDao getInstance() {
        if (instance == null) {
            instance = new StudentDao();
        }
        return instance;
    }

    private StudentDao() {
        emf = Persistence.createEntityManagerFactory("lab5");
        entityManager = emf.createEntityManager();
    }

    public StudentDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Student> getAll() {
        return entityManager.createNamedQuery("Student.findAll")
                .getResultList();
    }

    public void add(Student student) {
        entityManager.getTransaction().begin();

        entityManager.persist(student);
        entityManager.flush();
        entityManager.refresh(student);

        entityManager.getTransaction().commit();
    }

    public void delete(Student student) {
        entityManager.getTransaction().begin();
        entityManager.remove(student);
        entityManager.getTransaction().commit();
    }

    public void update(Student updatedStudent) {
        Student student = entityManager.find(Student.class, updatedStudent.getStudentId());

        entityManager.getTransaction().begin();

        student.setName(updatedStudent.getName());
        student.setExams(updatedStudent.getExams());

        entityManager.getTransaction().commit();
    }
}
