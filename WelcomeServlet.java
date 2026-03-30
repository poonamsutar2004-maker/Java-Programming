package com.demo;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (session != null) {
            String name = (String) session.getAttribute("user");

            out.println("<h2>Welcome " + name + "</h2>");
            out.println("<a href='logout'>Logout</a>");
        } else {
            out.println("<h3>Session expired!</h3>");
            out.println("<a href='index.html'>Login Again</a>");
        }
    }
}