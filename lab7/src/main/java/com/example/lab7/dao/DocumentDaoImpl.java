package com.example.lab7.dao;

import com.example.lab7.entity.Document;
import com.example.lab7.interceptor.Logged;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class DocumentDaoImpl extends GenericDaoImpl<Document> implements DocumentDao {
    public DocumentDaoImpl() {
        super(Document.class);
    }

    @Override
    @Logged
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
