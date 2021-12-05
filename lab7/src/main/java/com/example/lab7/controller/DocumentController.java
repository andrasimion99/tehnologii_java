package com.example.lab7.controller;

import com.example.lab7.bean.DocumentBean;
import com.example.lab7.dao.DocumentDaoImpl;
import com.example.lab7.entity.Document;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name = "documentController")
@Named
@SessionScoped
public class DocumentController {
    @Inject
    DocumentDaoImpl documentDaoImpl;
    private final Logger logger = Logger.getLogger(getClass().getName());
    private List<Document> documents;

    public DocumentController() {
        documents = new ArrayList<>();
    }

    public void saveFile(DocumentBean bean) {
        try {
            bean.setFilename(bean.getFile().getFileName());
            byte[] fileContent = new byte[(int) bean.getFile().getSize()];
            bean.getFile().getInputStream().read(fileContent);
            bean.setContent(fileContent);
            documentDaoImpl.create(bean.ConvertToEntity());

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", bean.getFilename() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            addErrorMessage(e);
        }
    }

    public void loadDocuments() {
        documents = documentDaoImpl.getAll();
    }

    public void downloadContent(Document doc) {
        // the path is on the server glassfish-5.0\glassfish5\glassfish\domains\domain1\lab7
        String path = "../lab7/" + doc.getRegistration() + "-" + doc.getFilename();
        try {
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(doc.getContent());
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
            addErrorMessage(e);
        }
    }

    private void addErrorMessage(Exception e) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage(), "Error: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
