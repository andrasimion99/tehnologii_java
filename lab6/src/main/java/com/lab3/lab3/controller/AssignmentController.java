package com.lab3.lab3.controller;

import com.lab3.lab3.DAO.ExamDao;
import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.entity.Resource;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
@Startup
@Named("assignmentController")
@ApplicationScoped
public class AssignmentController {
    @EJB
    private ExamDao examDao;
    private List<Exam> exams;
    private Map<Integer, List<Resource>> assignments;

    @PostConstruct
    public void init() {
        exams = examDao.getAll();
        assignments = new HashMap<>();
        for (Exam exam : exams) {
            assignments.put(exam.getExamId(), exam.getResources());
        }
        printAssignments();
    }

    public List<Exam> getExams() {
        return exams;
    }

    public Map<Integer, List<Resource>> getAssignments() {
        return assignments;
    }

    public void setAssignments(Map<Integer, List<Resource>> assignments) {
        this.assignments = assignments;
    }

    public void add(Exam exam) {
        assignments.put(exam.getExamId(), exam.getResources());
        printAssignments();
    }

    private void printAssignments() {
        for (Integer examId : assignments.keySet()) {
            System.out.println("exam: " + examId + ", value: ");
            for (Resource r : assignments.get(examId)) {
                System.out.println(r.getName() + " ");
            }
        }
    }
}
