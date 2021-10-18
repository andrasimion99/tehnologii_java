package com.records.dao;

import com.records.entity.Records;

import javax.persistence.EntityManager;
import java.util.List;

public class RecordDAO extends JpaDAO<Records> implements GenericDAO<Records>{
    public RecordDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public Records create(Records record){
        return super.create(record);
    }

    @Override
    public Records get(Object id) {
        return super.find(Records.class, id);
    }

    @Override
    public List<Records> getAll() {
        return super.getAllWithNamedQuery("Records.getAll");
    }
}
