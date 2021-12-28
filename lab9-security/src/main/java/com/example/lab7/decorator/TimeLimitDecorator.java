package com.example.lab7.decorator;

import com.example.lab7.dao.DocumentDao;
import com.example.lab7.entity.Document;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.time.LocalDateTime;

@Decorator
public abstract class TimeLimitDecorator implements DocumentDao {
    @Inject
    @Delegate
    @Any
    DocumentDao dao;

    public Document create(Document document) throws Exception {
        int startHour = 9;
        int finishHour = 23;
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getHour());
//        if (now.getHour() < startHour || now.getHour() > finishHour)
//            throw new Exception("Registration closed. Please register between " + startHour + " and " + finishHour);
        return dao.create(document);
    }
}
