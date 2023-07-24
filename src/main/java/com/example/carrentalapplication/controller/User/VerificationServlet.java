package com.example.carrentalapplication.controller.User;

import com.example.carrentalapplication.dao.UserDAO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class VerificationServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("CurrentUser");
        if (user.isVerified()) {
            resp.sendRedirect("login");
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Verify.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();


        String verifyCode = req.getParameter("code");
        User user = (User) session.getAttribute("CurrentUser");

        if(user!=null) {
            if (verifyCode.equals(user.getVerificationCode())) {
                try {
                    userDAO.updateUserIsVerified(true, user.getEmailId());
                    resp.sendRedirect("Login.jsp");

                }catch (DAOException e) {
                    e.printStackTrace();
                }
            } else
            {
                resp.sendRedirect("Verify.jsp");
            }
        }
        else
            try {
                int userId = Integer.parseInt(req.getParameter("userId"));
                User user1=userDAO.getUserById(userId);
                if(user1.getVerificationCode().equals(verifyCode))
                {
                    userDAO.updateUserIsVerified(true, user1.getEmailId());
                    resp.sendRedirect("Login.jsp");
                }
                else
                {
                    resp.sendRedirect("Verify.jsp");
                }
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }

    }



