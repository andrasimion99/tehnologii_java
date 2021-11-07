package com.lab3.lab3.entity;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "project")
@Entity
@DiscriminatorValue("PROJECT")
@NamedQueries({
        @NamedQuery(name = "Exam.findAllProjects", query = "select e from Exam e where e.examType = 'PROJECT'"),
        @NamedQuery(name = "Exam.findProjectById", query = "select e from Exam e where e.examId=?1 and e.examType = 'PROJECT'")
})
public class Project extends Exam {
    @Column(name = "number_slides")
    private int numberSlides;


    public static List<String> getAllFields(){
        return  new ArrayList<String>() {
            {
                add("examId");
                add("examType");
                add("name");
                add("startingDate");
                add("duration");
                add("numberSlides");
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
                add("numberSlides");
            }
        };
    }

    public int getNumberSlides() {
        return numberSlides;
    }

    public void setNumberSlides(int numberSlides) {
        this.numberSlides = numberSlides;
    }

    @Override
    public String toString() {
        return "Project{" +
                "numberSlides=" + numberSlides +
                "} " + super.toString();
    }
}
