package com.example.lab7.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class GenericDaoImpl<T> implements GenericDao<T> {
    @PersistenceContext(name = "lab7")
    EntityManager em;
    protected Class<T> entityClass;

    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T create(T t) throws Exception {
        em.persist(t);
        em.flush();
        em.refresh(t);
        return t;
    }

    @Override
    public T update(T t) {
        return em.merge(t);
    }

    @Override
    public T get(Integer id) {
        if (id == null) {
            return null;
        }
        return em.find(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll(String query) {
        return em.createNamedQuery(query).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getByProperty(String query, String prop){
        List<T> list = em.createNamedQuery(query).setParameter(1, prop).getResultList();
        if (list.isEmpty())
            return null;
        return list.get(0);
    }

    @Override
    public void delete(Integer id) {
        Object reference = em.getReference(entityClass, id);
        em.remove(reference);
    }
}
