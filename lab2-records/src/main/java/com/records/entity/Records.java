package com.records.entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Records.getAll", query = "SELECT r FROM Records r")
@Table(name = "records")
public class Records {
    @Id
    @Column(name = "record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;
    @Column(name = "category")
    private String category;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
