package com.example.lab7.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "documents")
@NamedQueries({
        @NamedQuery(name = "Document.findAll", query = "select d from Document d ")
})
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Integer docId;
    @Column(name = "filename")
    private String filename;
    @Column(name = "content")
    @Lob
    private byte[] content;
    @Column(name = "user_id")
    private Integer userId;

    public Document() {
    }

    public Document(String filename, byte[] content, Integer userId) {
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

    @Override
    public String toString() {
        return "Document{" +
                "docId=" + docId +
                ", filename='" + filename + '\'' +
                ", userId=" + userId +
                '}';
    }
}
