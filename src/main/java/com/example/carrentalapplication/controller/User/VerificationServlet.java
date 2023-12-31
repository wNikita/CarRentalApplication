package com.example.carrentalapplication.controller.User;

import com.example.carrentalapplication.dao.UserDAO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.UserEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class VerificationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("CurrentUser");
        String VerifyCode = req.getParameter("code");
        if (user != null) {
            if (user.isVerified()) {
                resp.sendRedirect("login");
            }
        } else {
            req.setAttribute("code", VerifyCode);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Verify.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDAO userDAO = new UserDAO();


        String verifyCode = req.getParameter("code");
        UserEntity user = (UserEntity) session.getAttribute("CurrentUser");
        try {
            if (user != null) {
                List<UserEntity> user2 = userDAO.getUserDataById(user.getUserId());
                for (UserEntity userEntity : user2)
                    if (verifyCode.equals(userEntity.getVerificationCode())) {
                        try {
                            userDAO.userUpdateIsVerified(true, userEntity.getUserId());
                            resp.sendRedirect("Login.jsp");

                        } catch (DAOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        resp.sendRedirect("Verify.jsp");
                    }
            } else
                try {
                    int userId = Integer.parseInt(req.getParameter("userId"));
                    List<UserEntity> user1 = userDAO.getUserDataById(userId);
                    for (UserEntity userEntity1 : user1)
                        if (userEntity1.getVerificationCode().equals(verifyCode)) {
                            userDAO.userUpdateIsVerified(true, userEntity1.getUserId());
                            //    userDAO.updateUserIsVerified(true, user1.getEmailId());
                            resp.sendRedirect("Login.jsp");
                        } else {
                            resp.sendRedirect("Verify.jsp");
                        }
                } catch (DAOException e) {
                    e.printStackTrace();
                }
        } catch (DAOException e) {
            e.printStackTrace();
        }


    }
}



