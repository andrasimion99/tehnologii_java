package com.lab3.lab3.entity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "exam")
@Entity
@Table(name = "exams")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "exam_type", discriminatorType = DiscriminatorType.CHAR)
@NamedQueries({
        @NamedQuery(name = "Exam.findAll", query = "select e from Exam e order by e.startingDate"),
        @NamedQuery(name = "Exam.findById", query = "select e from Exam e where e.examId=?1 ")
})
@RequestScoped
public class Exam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Integer examId;
    @Column(name = "name")
    private String name;
    @Column(name = "exam_type")
    private String examType;
    @Column(name = "starting_time")
    private Date startingDate;
    @Column(name = "duration")
    private Integer duration;
    @ManyToMany(mappedBy = "exams")
    private List<Student> students = new ArrayList<>();

    public Exam() {
    }

    public Exam(String name, Date startingDate, Integer duration) {
        this.name = name;
        this.startingDate = startingDate;
        this.duration = duration;
    }

    public static List<String> getAllFields(){
        return  new ArrayList<String>() {
            {
                add("examId");
                add("examType");
                add("name");
                add("startingDate");
                add("duration");
            }
        };
    }


    public static List<String> getAllInputs() {
        return new ArrayList<String>() {
            {
                add("examType");
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

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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
