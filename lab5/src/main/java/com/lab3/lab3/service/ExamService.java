package com.lab3.lab3.service;

import com.lab3.lab3.DAO.ExamDao;
import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.entity.Project;
import com.lab3.lab3.entity.WritingExam;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class ExamService {
    private final ExamDao examDao;

    public ExamService() {
        examDao = ExamDao.getInstance();
    }

    public List<Exam> getExams() {
        return examDao.getAll();
    }

    public void addExam(Exam exam) {
        examDao.add(exam);
    }

    public void addProject(Project project) {
        examDao.add(project);
    }

    public void addWritingExam(WritingExam writingExam) {
        examDao.add(writingExam);
    }

    public Exam getExamById(Integer id) {
        return examDao.getById(id);
    }

    public List<Exam> getExamsById(List<Integer> ids) {
        List<Exam> exams = new ArrayList<>();
        for (Integer id : ids) {
            exams.add(examDao.getById(id));
        }
        return exams;
    }

    public List<Project> getProjects() {
        return examDao.getAllProjects();
    }

    public List<WritingExam> getWritingExams() {
        return examDao.getAllWritingExams();
    }
}
