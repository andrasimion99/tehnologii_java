package com.lab3.lab3.controller;

import com.lab3.lab3.entity.Student;
import com.lab3.lab3.service.StudentService;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name = "studentController")
@SessionScoped
public class StudentController implements Serializable {
    private List<Student> students;
    private StudentService studentService;
    private Logger logger = Logger.getLogger(getClass().getName());

    public StudentController() {
        try {
            studentService = new StudentService();
        } catch (NamingException e) {
            logger.log(Level.SEVERE, "Error while creating studentService");
            addErrorMessage(e);
        }
        students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void loadStudents() {
        logger.info("Loading students");
        try {
            students = studentService.getStudents();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while loading the students");
            addErrorMessage(e);
        }
    }

    public void addStudent(Student student) {
        logger.info("Adding student");
        try {
            studentService.addStudent(student);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while saving the student");
            addErrorMessage(e);
        }
    }

    private void addErrorMessage(Exception e) {
        FacesMessage message = new FacesMessage("Error: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
