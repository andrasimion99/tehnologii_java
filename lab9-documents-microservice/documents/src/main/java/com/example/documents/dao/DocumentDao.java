package com.example.documents.dao;

import com.example.documents.entity.Document;

import java.util.List;

public interface DocumentDao {
    Document create(Document document);
    Document update(Document document);
    Document get (Integer id);
    List<Document> getAll (String query);
    void delete(Integer id);
}
