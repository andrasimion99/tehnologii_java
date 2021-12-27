package com.example.lab7.bean;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import java.util.UUID;

@ApplicationScoped
public class RegistrationGenerator {
    @Produces
    @Named("registration")
    String getRegistrationNumber() {
        return UUID.randomUUID().toString();
    }
}