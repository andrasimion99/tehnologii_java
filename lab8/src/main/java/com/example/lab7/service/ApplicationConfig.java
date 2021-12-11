package com.example.lab7.service;


//import javax.enterprise.context.ApplicationScoped;

import javax.faces.bean.ApplicationScoped;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationScoped
public class ApplicationConfig extends Application {
    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(DocumentService.class);
    }
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
}

