package com.example.lab7.bean;

import com.example.lab7.entity.Document;
import com.example.lab7.entity.User;
import org.primefaces.model.file.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.Arrays;

@ManagedBean(name = "documentBean")
@RequestScoped
public class DocumentBean {
    private UploadedFile file;
    private String filename;
    private byte[] content;
    @ManagedProperty("#{cookie['userId'].value}")
    private String username;

    public DocumentBean() {
    }

    public DocumentBean(UploadedFile file, String filename, byte[] content, String username) {
        this.file = file;
        this.filename = filename;
        this.content = content;
        this.username = username;
    }

    public Document ConvertToEntity(){
        return new Document(filename, content, Integer.parseInt(username));
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "DocumentBean{" +
                "file=" + file +
                ", filename='" + filename + '\'' +
                ", content=" + Arrays.toString(content) +
                ", username='" + username + '\'' +
                '}';
    }
}
