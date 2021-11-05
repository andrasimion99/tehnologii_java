package com.lab3.lab3.service;

import com.lab3.lab3.DAO.ExamDao;
import com.lab3.lab3.entity.Exam;

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

    public Exam getExamById(Integer id) {
        return examDao.getById(id);
    }

    public List<Exam> getExamsById(List<Integer> ids) {
        List<Exam> exams = new ArrayList<>();
        for (Integer id: ids)
        {
            exams.add(examDao.getById(id));
        }
        return exams;
    }
}
