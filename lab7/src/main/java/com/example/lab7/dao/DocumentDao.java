package com.example.lab7.dao;

import com.example.lab7.entity.Document;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DocumentDao extends GenericDaoImpl<Document> {
    public DocumentDao() {
        super(Document.class);
    }

    @Override
    public Document create(Document document) throws Exception {
        return super.create(document);
    }

    @Override
    public Document update(Document document) {
        return super.update(document);
    }

    @Override
    public Document get(Integer id) {
        return super.get(id);
    }

    public List<Document> getAll() {
        return super.getAll("Document.findAll");
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}
