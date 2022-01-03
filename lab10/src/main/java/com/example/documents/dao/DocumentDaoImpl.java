package com.example.documents.dao;

import com.example.documents.entity.Document;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RequestScoped
public class DocumentDaoImpl implements DocumentDao {
    @PersistenceContext(name = "lab7")
    EntityManager em;

    @Override
    public Document create(Document document) {
        em.persist(document);
        em.flush();
        em.refresh(document);
        return document;
    }

    @Override
    public Document update(Document document) {
        return em.merge(document);
    }

    @Override
    public Document get(Integer id) {
        if (id == null) {
            return null;
        }
        return em.find(Document.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Document> getAll(String query) {
        return em.createNamedQuery(query).getResultList();
    }

    @Override
    public void delete(Integer id) {
        Object reference = em.getReference(Document.class, id);
        em.remove(reference);
    }
}
