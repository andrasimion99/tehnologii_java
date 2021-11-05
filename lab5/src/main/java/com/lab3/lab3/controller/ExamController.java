package com.lab3.lab3.controller;

import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.service.ExamService;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name = "examController")
@SessionScoped
public class ExamController implements Serializable {
    private List<Exam> exams;
    private final ExamService examService;
    private final Logger logger = Logger.getLogger(getClass().getName());

    public ExamController() {
        examService = new ExamService();
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
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while loading the exams");
            addErrorMessage(e);
        }
    }

    public void addExam(String name, Date date, Integer duration) {
        logger.info("Adding exam");
        Exam exam = new Exam(name, date, duration);
        try {
            examService.addExam(exam);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while saving the exam");
            addErrorMessage(e);
        }
    }

    public void addExam(Exam exam, Date date) {
        logger.info("Adding exam");
        exam.setStartingDate(date);
        try {
            examService.addExam(exam);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while saving the exam " + e.getMessage());
            addErrorMessage(e);
        }
    }

    private void addErrorMessage(Exception e) {
        FacesMessage message = new FacesMessage("Error: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
