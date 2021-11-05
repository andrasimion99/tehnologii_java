package com.lab3.lab3.service;

import com.lab3.lab3.DAO.StudentDao;
import com.lab3.lab3.entity.Student;

import java.util.List;

public class StudentService {

    private final StudentDao studentDao;

    public StudentService() {
        studentDao = StudentDao.getInstance();
    }

    public List<Student> getStudents() {
        return studentDao.getAll();
    }

    public void addStudent(Student student) {
        studentDao.add(student);
    }
}
