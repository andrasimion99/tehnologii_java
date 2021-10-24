package com.lab3.lab3.service;

import com.lab3.lab3.DAO.StudentDao;
import com.lab3.lab3.entity.Student;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class StudentService {

    private final StudentDao studentDao;

    public StudentService() throws NamingException {
        studentDao = StudentDao.getInstance();
    }

    public List<Student> getStudents() throws SQLException, NamingException {
        return studentDao.getAll();
    }

    public void addStudent(Student student) throws SQLException {
        studentDao.add(student);
    }
}
