package com.lab3.lab3.entity;


import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "student")
public class Student {
    private Integer studentId;
    private String name;
    private List<Exam> exams;

    public Student() {
        exams = new ArrayList<>();
    }

    public Student(String name, List<Exam> exams) {
        this.name = name;
        this.exams = exams;
    }

    public Student(Integer studentId, String name, List<Exam> exams) {
        this.studentId = studentId;
        this.name = name;
        this.exams = exams;
    }

    public List<String> getAllFields(){
        return new ArrayList<String>(){
            {
                add("studentId");
                add("name");
                add("exams");
            }
        };
    }

    public List<String> getAllInputs(){
        return new ArrayList<String>(){
            {
                add("name");
                add("exams");
            }
        };
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", exams=" + exams +
                '}';
    }
}
