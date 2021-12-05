package com.lab3.lab3.DAO;

import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.entity.Project;
import com.lab3.lab3.entity.WritingExam;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "examDao")
public class ExamDao extends AbstractDao<Exam>{
    @PersistenceContext(unitName = "lab6")
    EntityManager entityManager;

    public ExamDao() {
        super(Exam.class);
    }

    public Exam getById(Integer id) {
        return (Exam) entityManager.createNamedQuery("Exam.findById")
                .setParameter(1,id)
                .getSingleResult();
    }

    public List<Exam> getAll() {
        System.out.println("EXAM DAO");
        return super.findAll("Exam.findAll");
    }

    public void add(Exam exam) {
        super.persist(exam);
        super.refresh(exam);
    }

    public void update(Exam exam) {
        super.update(exam);
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
        return entityManager.createNamedQuery("Exam.findAllProjects")
                .getResultList();
    }

    public List<WritingExam> getAllWritingExams(){
        return entityManager.createNamedQuery("Exam.findAllWritings")
                .getResultList();
    }
}
