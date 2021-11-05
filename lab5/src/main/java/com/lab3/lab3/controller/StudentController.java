package com.lab3.lab3.controller;

import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.entity.Student;
import com.lab3.lab3.service.ExamService;
import com.lab3.lab3.service.StudentService;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name = "studentController")
@SessionScoped
public class StudentController implements Serializable {
    private List<Student> students;
    private final StudentService studentService;
    private final ExamService examService;
    private final Logger logger = Logger.getLogger(getClass().getName());

    public StudentController() {
        studentService = new StudentService();
        examService = new ExamService();
        students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void loadStudents() {
        logger.info("Loading students");
        try {
            students = studentService.getStudents();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while loading the students");
            addErrorMessage(e);
        }
    }

    public void addStudent(String name, String examsId) {
        logger.info("Adding student");
        List<Integer> ids = new ArrayList<>();
        for (String number:examsId.split(",")) {
            ids.add(Integer.parseInt(number));
        }
        System.out.println("IDS:" + ids);

        try {
            List<Exam> exams = examService.getExamsById(ids);
            Student student= new Student(name, exams);
            studentService.addStudent(student);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while saving the student");
            addErrorMessage(e);
        }
    }

    public void addStudent(Student student, String examsId) {
        logger.info("Adding student");
        List<Integer> ids = new ArrayList<>();
        for (String number:examsId.split(",")) {
            ids.add(Integer.parseInt(number));
        }
        System.out.println("IDS:" + ids);

        try {
            List<Exam> exams = examService.getExamsById(ids);
            student.setExams(exams);
            studentService.addStudent(student);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while saving the student " + e.getMessage());
            addErrorMessage(e);
        }
    }

    private void addErrorMessage(Exception e) {
        FacesMessage message = new FacesMessage("Error: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
