package com.example.demo.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/simple-status")
public class SimpleStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1. Set the content type to text/plain
        resp.setContentType("text/plain"); 
        
        // 2. Set HTTP status to 200 OK
        resp.setStatus(HttpServletResponse.SC_OK); 
        
        // 3. Write the required message to the response body
        resp.getWriter().write("API Rate Limiter & Quota Manager is running");
    }
}