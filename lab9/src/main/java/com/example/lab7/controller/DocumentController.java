package com.example.lab7.controller;

import com.example.lab7.bean.DocumentBean;
import com.example.lab7.dao.DocumentDaoImpl;
import com.example.lab7.entity.Document;
//import com.example.lab7.filter.CacheDocumentsFilter;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name = "documentController")
//@Named
@ApplicationScoped
public class DocumentController implements Serializable {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private List<Document> documents;
    private String documentsUri = "http://localhost:8080/lab7-1.0-SNAPSHOT/resources/documents";
    private Client client;
    @Inject
    Event<Document> documentUpdatedEvent;
    @Inject
    DocumentDaoImpl documentDao;


    public DocumentController() {
        client = ClientBuilder.newClient();
    }

    public void saveFile(DocumentBean bean) {
        try {
            bean.setFilename(bean.getFile().getFileName());
            byte[] fileContent = new byte[(int) bean.getFile().getSize()];
            bean.getFile().getInputStream().read(fileContent);
            bean.setContent(fileContent);
            Document doc = bean.ConvertToEntity();
            client.target(documentsUri)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(doc, MediaType.APPLICATION_JSON));
            documentUpdatedEvent.fire(doc);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", bean.getFilename() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            addErrorMessage(e);
        }
    }

    public void loadDocuments() throws UnsupportedEncodingException {
//        documents = client.target(documentsUri)
//                .request(MediaType.APPLICATION_JSON)
//                .get(new GenericType<ArrayList<Document>>() {});
        documents = documentDao.getAll();
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
        if (documents == null) {
            documents = documentDao.getAll();
        }
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public void onDocumentUpdate(@Observes Document document){
        documents = null;
    }

}
