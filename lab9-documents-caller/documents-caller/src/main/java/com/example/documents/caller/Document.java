package com.example.documents.caller;

public class Document {
    private Integer docId;
    private String registration;
    private String filename;
    private byte[] content;
    private Integer userId;

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
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
                ", registration='" + registration + '\'' +
                ", filename='" + filename + '\'' +
                ", userId=" + userId +
                '}';
    }
}
