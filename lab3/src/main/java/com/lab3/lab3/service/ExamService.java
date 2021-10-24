package com.lab3.lab3.service;

import com.lab3.lab3.DAO.ExamDao;
import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.entity.Student;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamService {
    private final ExamDao examDao;

    public ExamService() throws NamingException {
        examDao = ExamDao.getInstance();
    }

    public List<Exam> getExams() throws SQLException {
        return examDao.getAll();
    }

    public void addExam(Exam exam) throws SQLException {
        examDao.add(exam);
    }

    public Exam getExamById(Integer id) throws SQLException {
        return examDao.getById(id);
    }

    public List<Exam> getExamsById(List<Integer> ids) throws SQLException {
        List<Exam> exams = new ArrayList<>();
        for (Integer id: ids)
        {
            exams.add(examDao.getById(id));
        }
        return exams;
    }
}
