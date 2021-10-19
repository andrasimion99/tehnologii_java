package com.records.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.lang.model.type.NullType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Objects;

@WebServlet(name = "InputServlet", value = "/InputServlet")
public class InputServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = "categories.json";
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
        String content = new String(Files.readAllBytes(file.toPath()));
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(content);
            request.setAttribute("categories", json.values().toArray());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie c : cookies)
            {
                if(c.getName().equals("category")){
                    request.setAttribute("cookieCategory", c.getValue());
                }
            }
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/input.jsp");
        requestDispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
