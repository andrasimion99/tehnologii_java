package com.records.service;

import com.records.dao.RecordDAO;
import com.records.entity.Records;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RecordService {
    private final RecordDAO recordDAO;

    public RecordService() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceLab2");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        recordDAO = new RecordDAO(entityManager);
    }

    public List<Records> listRecords(){
        return recordDAO.getAll();
    }
}
