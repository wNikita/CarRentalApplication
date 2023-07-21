package com.example.carrentalapplication.controller.User;

import com.example.carrentalapplication.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("CurrentUser");
        request.setAttribute("user",user);
        RequestDispatcher requestDispatcher= request.getRequestDispatcher("UserProfile.jsp");
        requestDispatcher.forward(request,resp);
    }

}
