package com.example.lab7.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "select u from User u order by u.username"),
        @NamedQuery(name = "User.findByUsername", query = "select u from User u where u.username=?1 ")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "username")
    private String username;
    @Column(name = "pass")
    private String pass;
    @Column(name = "is_admin")
    private boolean isAdmin;

    public User() {
    }

    public User(String username, String pass, boolean isAdmin) {
        this.username = username;
        this.pass = pass;
        this.isAdmin = isAdmin;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
