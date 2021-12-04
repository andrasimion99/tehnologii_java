package com.lab3.lab3.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resources")
@NamedQueries({
        @NamedQuery(name = "Resource.findAll", query = "select r from Resource r"),
        @NamedQuery(name = "Resource.findById", query = "select r from Resource r where r.resourceId=?1 ")
})
public class Resource implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private Integer resourceId;
    @Column(name = "name")
    private String name;
    @Column(name = "resource_type")
    private String resourceType;
    @ManyToMany(mappedBy = "resources")
    private List<Exam> exams = new ArrayList<>();

    public Resource() {
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", name='" + name + '\'' +
                ", resourceType='" + resourceType + '\'' +
                '}';
    }
}
