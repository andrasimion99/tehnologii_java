package com.records.controller;

import com.records.service.RecordService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateRecordServlet", value = "/CreateRecordServlet")
public class CreateRecordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String value = request.getParameter("value");
        String category = request.getParameter("category");

        Cookie cookie = new Cookie("category", category);//creating cookie object
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);

        RecordService recordService = new RecordService();
        recordService.createRecord(category, name, value);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/createRecord.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/createRecord.jsp");
        requestDispatcher.forward(request, response);
    }
}
