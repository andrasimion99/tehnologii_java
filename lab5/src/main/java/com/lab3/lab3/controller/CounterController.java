package com.lab3.lab3.controller;

import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.service.ExamService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name = "counterController")
@ViewScoped
public class CounterController {
    private int pos;
    private List<Exam> examList;

    private ExamService examService;
    private Logger logger = Logger.getLogger(getClass().getName());

    public CounterController() {
        try {
            examService = new ExamService();
            examList = examService.getExams();
        } catch (NamingException | SQLException e) {
            logger.log(Level.SEVERE, "Error while creating examService and getting all exams");
            addErrorMessage(e);
        }

    }

    public String getExam() {
        return examList.get(pos).getName();
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void goNext() {
        if (pos == examList.size()) {
            pos = 0;
        } else {
            pos++;
        }
    }

    private void addErrorMessage(Exception e) {
        FacesMessage message = new FacesMessage("Error: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
