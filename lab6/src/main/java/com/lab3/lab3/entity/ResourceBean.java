package com.lab3.lab3.entity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "resourceBean")
@RequestScoped
public class ResourceBean {
    private Integer resourceId;
    private String name;
    private String resourceType;

    public static List<String> getAllFields(){
        return  new ArrayList<String>() {
            {
                add("resourceId");
                add("resourceType");
                add("name");
            }
        };
    }

    public static List<String> getAllInputs() {
        return new ArrayList<String>() {
            {
                add("resourceType");
                add("name");
            }
        };
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public String toString() {
        return "ResourceBean{" +
                "resourceId=" + resourceId +
                ", name='" + name + '\'' +
                ", resourceType='" + resourceType + '\'' +
                '}';
    }
}
