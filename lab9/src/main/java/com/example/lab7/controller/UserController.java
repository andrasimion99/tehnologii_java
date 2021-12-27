package com.example.lab7.controller;

import com.example.lab7.bean.UserBean;
import com.example.lab7.dao.UserDaoImpl;
import com.example.lab7.entity.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name = "userController")
@Named
@SessionScoped
public class UserController {
    @Inject
    UserDaoImpl userDaoImpl;
    private final Logger logger = Logger.getLogger(getClass().getName());

    public UserController() {
    }

    public void register(UserBean userbean) {
        try {
            userDaoImpl.create(userbean.ConvertToEntity());
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while registering the user");
            addErrorMessage(e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            addErrorMessage(e);
        }
    }


    public void login(UserBean userBean) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            System.out.println(userBean.getUsername() + " " + userBean.getPass());
            request.login(userBean.getUsername(), userBean.getPass());
            externalContext.log("Login successful: " + userBean.getUsername());
            User user = userDaoImpl.findByUsername(userBean.getUsername());
//            User user = userDaoImpl.login(userBean.ConvertToEntity());
            setCookie("user", user.getUsername());
            setCookie("userId", String.valueOf(user.getUserId()));
            setCookie("isAdmin", String.valueOf(user.isAdmin()));
            FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
        } catch (ServletException | RuntimeException | IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
            addErrorMessage(e);
        }
    }

    public void logout() {
        removeCookie("user");
        removeCookie("isAdmin");
        removeCookie("userId");
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)
                context.getExternalContext().getRequest();
        try {
            request.logout();
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (ServletException | IOException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
            e.printStackTrace();
        }
    }

    private void setCookie(String cookieName, String cookieValue) throws UnsupportedEncodingException {
        Map<String, Object> properties = new HashMap<>();
        properties.put("maxAge", 31536000);
        properties.put("path", "/");
        FacesContext.getCurrentInstance().getExternalContext().addResponseCookie(cookieName, URLEncoder.encode(cookieValue, "UTF-8"), properties);
    }

    private void removeCookie(String cookieName) {
        Cookie cookie = (Cookie) FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().get(cookieName);
        cookie.setValue("");
        Map<String, Object> properties = new HashMap<>();
        properties.put("maxAge", 0);
        properties.put("path", "/");
        FacesContext.getCurrentInstance().getExternalContext().addResponseCookie(cookie.getName(), cookie.getValue(), properties);
    }

    private void addErrorMessage(Exception e) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage(), "Error: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
