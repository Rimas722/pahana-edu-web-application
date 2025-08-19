package com.pahanaedu.controller;

import com.pahanaedu.service.AdminService;
import com.pahanaedu.service.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AdminService adminService;

    @Override
    public void init() {
        this.adminService = new AdminServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (adminService.validateAdmin(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("adminUser", username);

            response.sendRedirect("system?action=showDashboard");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("adminUser") != null) {
            response.sendRedirect("system?action=showDashboard");
            return; 
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
