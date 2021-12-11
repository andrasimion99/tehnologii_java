package com.example.lab7.entity;

import javax.persistence.*;

@Entity
@Table(name = "documents")
@NamedQueries({
        @NamedQuery(name = "Document.findAll", query = "select d from Document d "),
        @NamedQuery(name = "Document.findAllByUser", query = "select d from Document d where d.userId=?1")
})
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Integer docId;
    @Column(name = "registration")
    private String registration;
    @Column(name = "filename")
    private String filename;
    @Column(name = "content")
    @Lob
    private byte[] content;
    @Column(name = "user_id")
    private Integer userId;

    public Document() {
    }

    public Document(String registration, String filename, byte[] content, Integer userId) {
        this.registration = registration;
        this.filename = filename;
        this.content = content;
        this.userId = userId;
    }

    public Document(Integer docId, String registration, String filename, byte[] content, Integer userId) {
        this.docId = docId;
        this.registration = registration;
        this.filename = filename;
        this.content = content;
        this.userId = userId;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "Document{" +
                "docId=" + docId +
                ", registration='" + registration + '\'' +
                ", filename='" + filename + '\'' +
                ", userId=" + userId +
                '}';
    }
}
