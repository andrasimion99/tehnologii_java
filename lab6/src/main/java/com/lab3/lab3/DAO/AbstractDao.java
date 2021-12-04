package com.lab3.lab3.DAO;

import javax.ejb.Remote;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Remote
public abstract class AbstractDao<T> {
    @PersistenceContext(name = "lab6")
    EntityManager em;
    protected Class<T> entityClass;

    protected AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T newInstance() throws IllegalAccessException, InstantiationException {
        return entityClass.newInstance();
    }

    public T findById(Integer id) {
        if (id == null) { return null; }
        return em.find(entityClass, id);
    }

    public List<T> findAll(String queryName) {
        System.out.println("ABSTRACT DAO");
        return em.createNamedQuery(queryName)
                .getResultList();
    }


    public void persist(T entity) {
        em.persist(entity);
    }
    public void update(T entity) {
        em.merge(entity);
    }
    public void remove(T entity) {
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.remove(entity);
    }
    public T refresh(T entity) {
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.refresh(entity);
        return entity;
    }
}
