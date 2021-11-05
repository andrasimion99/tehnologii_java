package com.lab3.lab3.controller;

import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.service.ExamService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "counterController")
@ViewScoped
public class CounterController {
    private int pos;
    private final List<Exam> examList;

    public CounterController() {
        ExamService examService = new ExamService();
        examList = examService.getExams();
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
        if (pos == examList.size() - 1) {
            pos = 0;
        } else {
            pos++;
        }
    }
}
