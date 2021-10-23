package com.lab3.lab3.service;

import com.lab3.lab3.DAO.ExamDao;
import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.entity.Student;

import javax.naming.NamingException;
import java.sql.SQLException;
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
}
