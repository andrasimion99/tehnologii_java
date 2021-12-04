package com.lab3.lab3.entity;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "student")
@Entity
@Table(name = "students")
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select s from Student s order by s.name"),
        @NamedQuery(name = "Student.findById", query = "select s from Student s where s.studentId=?1 ")
})
@RequestScoped
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "exam_and_student",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id", referencedColumnName = "exam_id"))
    private List<Exam> exams = new ArrayList<>();

    public Student() {
    }

    public Student(String name, List<Exam> exams) {
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
