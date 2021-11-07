package com.lab3.lab3.entity;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "writing_exam")
@Entity
@DiscriminatorValue("WRITE_EXAM")
@NamedQueries({
        @NamedQuery(name = "Exam.findAllWritings", query = "select e from Exam e where e.examType = 'WRITE_EXAM'")
})
public class WritingExam extends Exam {
    @Column(name = "number_questions")
    private int numberQuestions;

    public static List<String> getAllFields(){
        return  new ArrayList<String>() {
            {
                add("examId");
                add("examType");
                add("name");
                add("startingDate");
                add("duration");
                add("numberQuestions");
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
                add("numberQuestions");
            }
        };
    }

    public int getNumberQuestions() {
        return numberQuestions;
    }

    public void setNumberQuestions(int numberQuestions) {
        this.numberQuestions = numberQuestions;
    }

    @Override
    public String toString() {
        return "WritingExam{" +
                "numberQuestions=" + numberQuestions +
                "} " + super.toString();
    }
}
