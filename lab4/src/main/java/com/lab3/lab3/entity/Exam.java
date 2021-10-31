package com.lab3.lab3.entity;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ManagedBean(name = "exam")
public class Exam implements Serializable {
    private Integer examId;
    private String name;
    private Date startingDate;
    private Integer duration;

    public Exam() {
    }

    public Exam(String name, Date startingDate, Integer duration) {
        this.name = name;
        this.startingDate = startingDate;
        this.duration = duration;
    }

    public Exam(Integer examId, String name, Date startingDate, Integer duration) {
        this.examId = examId;
        this.name = name;
        this.startingDate = startingDate;
        this.duration = duration;
    }

    public List<String> getAllFields(){
        return new ArrayList<String>(){
            {
                add("examId");
                add("name");
                add("startingDate");
                add("duration");
            }
        };
    }

    public List<String> getAllInputs(){
        return new ArrayList<String>(){
            {
                add("name");
                add("startingDate");
                add("duration");
            }
        };
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId=" + examId +
                ", name='" + name + '\'' +
                ", startingDate=" + startingDate +
                ", duration=" + duration +
                '}';
    }
}
