package com.example.lab7.bean;

import com.example.lab7.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean {
    @NotNull
    @Size(min = 1, max = 50)
    private String username;
    @Size(min = 4, message = "The password should have at least 4 characters")
    private String pass;
    private boolean admin;

    public UserBean() {
    }

    public UserBean(String username, String pass, boolean isAdmin) {
        this.username = username;
        this.pass = pass;
        this.admin = isAdmin;
    }

    public User ConvertToEntity() {
        return new User(this.username, this.pass, this.admin);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", admin=" + admin +
                '}';
    }
}
