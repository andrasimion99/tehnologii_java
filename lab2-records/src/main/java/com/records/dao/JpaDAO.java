package com.records.dao;

import com.records.entity.Records;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class JpaDAO<T> {
    protected EntityManager entityManager;

    public JpaDAO(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    public T create(T t){
        entityManager.getTransaction().begin();

        entityManager.persist(t);
        entityManager.flush();
        entityManager.refresh(t);

        entityManager.getTransaction().commit();

        return t;
    }

    public T find(Class<T> type, Object id){
        T entity = entityManager.find(type, id);
        if(entity != null){
            entityManager.refresh(entity);
        }
        return entity;
    }

    public List<T> getAllWithNamedQuery(String queryName){
        Query query = entityManager.createNamedQuery(queryName);
        return (List<T>) query.getResultList();
    }
}
