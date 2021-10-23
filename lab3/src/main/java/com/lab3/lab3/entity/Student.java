package com.lab3.lab3.entity;


import javax.faces.bean.ManagedBean;

@ManagedBean(name = "student")
public class Student {
    private Integer studentId;
    private String name;

    public Student() {
    }

    public Student(Integer studentId, String name) {
        this.studentId = studentId;
        this.name = name;
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

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                '}';
    }
}
