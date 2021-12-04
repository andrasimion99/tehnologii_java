package com.example.lab7.controller;

import com.example.lab7.bean.DocumentBean;
import com.example.lab7.dao.DocumentDao;
import com.example.lab7.entity.Document;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name = "documentController")
@Named
@SessionScoped
public class DocumentController {
    @Inject
    DocumentDao documentDao;
    private final Logger logger = Logger.getLogger(getClass().getName());
    private List<Document> documents;
    private Integer startHour = 9;
    private Integer finishHour = 12;

    public DocumentController() {
        documents = new ArrayList<>();
    }

    public void saveFile(DocumentBean bean) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getHour());
        try {
            if(now.getHour() < startHour || now.getHour() > finishHour)
                throw new Exception("Registration closed. Please register between " + startHour + " and " + finishHour);
            bean.setFilename(bean.getFile().getFileName());
            byte[] fileContent = new byte[(int) bean.getFile().getSize()];
            bean.getFile().getInputStream().read(fileContent);
            bean.setContent(fileContent);
            documentDao.create(bean.ConvertToEntity());

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", bean.getFilename() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            addErrorMessage(e);
        }
    }

    public void loadDocuments() {
        documents = documentDao.getAll();
        logSubmissions();
    }

    public void downloadContent(Document doc) {
        // the path is on the server glassfish-5.0\glassfish5\glassfish\domains\domain1\lab7
        String path = "../lab7/" + doc.getFilename();
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

    private void logSubmissions()
    {
        String path = "../lab7/submissions.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for(Document doc: documents)
            {
                writer.append(doc.toString());
                writer.append('\n');
            }
            writer.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
            addErrorMessage(e);
        }
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
