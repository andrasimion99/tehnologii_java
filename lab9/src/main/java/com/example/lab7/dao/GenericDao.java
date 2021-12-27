package com.example.lab7.dao;

import java.util.List;

public interface GenericDao<T> {
    T create(T t) throws Exception;
    T update(T t);
    T get (Integer id);
    List<T> getAll (String query);
    void delete(Integer id);
    T getByProperty(String query, String prop);
    List<T> getAllByProperty(String query, String prop);
    List<T> getAllByProperty(String query, int prop);
}