package com.lab3.lab3.controller;

import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.service.ExamService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ManagedBean(name = "examSelectorController")
@ViewScoped
public class ExamSelectorController {
    private String selectedExam;
    private List<Exam> examList;
    private ExamService examService;
    private Logger logger = Logger.getLogger(getClass().getName());

    public ExamSelectorController() {
        try {
            examService = new ExamService();
            examList = examService.getExams();
        } catch (NamingException | SQLException e) {
            logger.log(Level.SEVERE, "Error while creating examService and getting all exams");
            addErrorMessage(e);
        }
    }

    public List<String> completeText(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> examNameList = new ArrayList<>();
        for (Exam exam : examList) {
            examNameList.add(exam.getName());
        }
        return examNameList.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }

    public String getSelectedExam() {
        return selectedExam;
    }

    public void setSelectedExam(String selectedExam) {
        this.selectedExam = selectedExam;
    }

    private void addErrorMessage(Exception e) {
        FacesMessage message = new FacesMessage("Error: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
