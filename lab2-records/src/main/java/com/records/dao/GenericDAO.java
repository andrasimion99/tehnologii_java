package com.records.dao;

import java.util.List;

public interface GenericDAO<T> {

    public T create(T t);

    public T get(Object id);

    public List<T> getAll();
}
