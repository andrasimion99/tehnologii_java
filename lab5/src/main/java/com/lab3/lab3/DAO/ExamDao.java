package com.lab3.lab3.DAO;

import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.entity.Project;
import com.lab3.lab3.entity.WritingExam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;

public class ExamDao {
    private static ExamDao instance;
    EntityManagerFactory emf;
    EntityManager entityManager;

    public static ExamDao getInstance() {
        if (instance == null) {
            instance = new ExamDao();
        }
        return instance;
    }

    private ExamDao() {
        emf = Persistence.createEntityManagerFactory("lab5");
        entityManager = emf.createEntityManager();
    }

    public Exam getById(Integer id) {
        return (Exam) entityManager.createNamedQuery("Exam.findById")
                .setParameter(1,id)
                .getSingleResult();
    }

    public List<Exam> getAll() {
        return entityManager.createNamedQuery("Exam.findAll")
                .getResultList();
    }

    public void add(Exam exam) {
        entityManager.getTransaction().begin();

        entityManager.persist(exam);
        entityManager.flush();
        entityManager.refresh(exam);

        entityManager.getTransaction().commit();
    }

    public void add(Project project) {
        entityManager.getTransaction().begin();

        entityManager.persist(project);
        entityManager.flush();
        entityManager.refresh(project);

        entityManager.getTransaction().commit();
    }

    public void add(WritingExam writingExam) {
        entityManager.getTransaction().begin();

        entityManager.persist(writingExam);
        entityManager.flush();
        entityManager.refresh(writingExam);

        entityManager.getTransaction().commit();
    }

    public List<Project> getAllProjects(){
        return entityManager.createNamedQuery("Exam.findAllProjects").getResultList();
    }

    public List<WritingExam> getAllWritingExams(){
        return entityManager.createNamedQuery("Exam.findAllWritings").getResultList();
    }
}
