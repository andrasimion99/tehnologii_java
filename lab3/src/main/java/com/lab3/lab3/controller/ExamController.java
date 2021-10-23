package com.lab3.lab3.controller;

import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.entity.Student;
import com.lab3.lab3.service.ExamService;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name = "examController")
@SessionScoped
public class ExamController implements Serializable {
    private List<Exam> exams;
    private ExamService examService;
    private Logger logger = Logger.getLogger(getClass().getName());

    public ExamController() {
        try {
            examService = new ExamService();
        } catch (NamingException e) {
            logger.log(Level.SEVERE, "Error while creating examService");
            addErrorMessage(e);
        }
        exams = new ArrayList<>();
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void loadExams(){
        logger.info("Loading exams");
        try {
            ExamService examService = new ExamService();
            exams = examService.getExams();
        } catch (SQLException | NamingException e) {
            logger.log(Level.SEVERE, "Error while loading the exams");
            addErrorMessage(e);
        }
    }

    public void addExam(String name, Date date, Integer duration) {
        logger.info("Adding exam");
        Exam exam = new Exam(name, date, duration);
        logger.info("The name:" + name);
        logger.info("HEEEERE " + exam.getName());
        logger.info(exam.getStartingDate().toString());
        try {
            examService.addExam(exam);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while saving the exam");
            addErrorMessage(e);
        }
    }

    private void addErrorMessage(Exception e) {
        FacesMessage message = new FacesMessage("Error: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
