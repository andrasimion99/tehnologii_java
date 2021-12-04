package com.lab3.lab3.service;

import com.lab3.lab3.DAO.StudentDao;
import com.lab3.lab3.entity.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {

    private final StudentDao studentDao;

    public StudentService() {
        studentDao = StudentDao.getInstance();
    }

    public List<Student> getStudents() throws SQLException {
        return studentDao.getAll();
    }

    public void addStudent(Student student) {
        studentDao.add(student);
    }

    public void deleteStudent(Student student) {
        studentDao.delete(student);
    }

    public void updateStudent(Student student)
    {
        studentDao.update(student);
    }
}
