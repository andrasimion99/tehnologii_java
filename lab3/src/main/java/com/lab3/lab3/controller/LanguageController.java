package com.lab3.lab3.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Locale;

@ManagedBean(name = "languageController", eager = true)
@SessionScoped
public class LanguageController implements Serializable {
    private static final Map<String,Object> countries;
    static {

        countries = new LinkedHashMap<>();
        countries.put("English", Locale.ENGLISH);
        countries.put("Italian", Locale.ITALIAN);
    }

    public LanguageController() {
    }

    public void changeLanguage(String lang){
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
            if(entry.getValue().toString().equals(lang)) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale)entry.getValue());
            }
        }
    }
}
