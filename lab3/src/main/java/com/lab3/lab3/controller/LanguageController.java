package com.lab3.lab3.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Locale;

@ManagedBean(name = "languageController", eager = true)
@ApplicationScoped
public class LanguageController implements Serializable {
    private String language;
    private static Map<String,Object> countries;
    static {

        countries = new LinkedHashMap<String,Object>();
        countries.put("English", Locale.ENGLISH);
        countries.put("Italian", Locale.ITALIAN);
    }

    public LanguageController() {
        System.out.println("CONSTRUCTOR");
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void changeLanguage(String lang){
        System.out.println("HEREE" + lang);
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
            System.out.println("Key:" + entry.getValue());
            if(entry.getValue().toString().equals(lang)) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale)entry.getValue());
            }
        }
    }
}
