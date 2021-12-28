package com.example.lab7.dao;

import com.example.lab7.entity.Document;
import com.example.lab7.interceptor.Logged;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public class DocumentDaoImpl extends GenericDaoImpl<Document> implements DocumentDao, Serializable {
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
        List<Document> list = super.getAll("Document.findAll");
        for (Document d: list) {
            d.setContent(new byte[0]);
        }
        return list;
    }

    public List<Document> getAllByUserId(int userId) {
        return super.getAllByProperty("Document.findAllByUser", userId);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}
