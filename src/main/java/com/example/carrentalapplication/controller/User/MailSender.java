package com.example.carrentalapplication.controller.User;

import com.example.carrentalapplication.common.EmailService;
import com.example.carrentalapplication.common.Utility;
import com.example.carrentalapplication.dao.UserDAO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.UserEntity;
import com.example.carrentalapplication.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MailSender extends HttpServlet {

EmailService emailService=new EmailService();
UserDAO userDAO=new UserDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("CurrentUser");
        String verificationCode=req.getParameter("code");
        if(user!=null) {
            String code = Utility.generateVerificationCode();

            try {
                userDAO.updateOfVerificationCode(code,user.getUserId());
                User user2 = userDAO.getUserById(user.getUserId());
                sendMail(user2);
                req.setAttribute("userID",user2.getUserId());
                req.setAttribute("code",verificationCode);
                RequestDispatcher requestDispatcher= req.getRequestDispatcher("verification?userId="+user2.getUserId());
                requestDispatcher.forward(req,resp);

            } catch (DAOException e) {
                e.printStackTrace();
            }

        }
        else {
            int userId = Integer.parseInt(req.getParameter("userId"));
            try {
                String code = Utility.generateVerificationCode();
                userDAO.updateOfVerificationCode(code,user.getUserId());
                User user2=userDAO.getUserById(userId);
                sendMail(user2);
                req.setAttribute("userId",user2.getUserId());
                req.setAttribute("code",verificationCode);
                RequestDispatcher requestDispatcher= req.getRequestDispatcher("verification"+userId);
                requestDispatcher.forward(req,resp);
//                resp.sendRedirect("verification");
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }

    }

    private void sendMail(User user) {
        StringBuilder mailContent = new StringBuilder();
        mailContent.append("<H1>")
                .append("Hi, ").append(user.getFirstName()).append(" ").append(user.getLastName())
                .append("</H1>")
                .append("Your verification code :").append(user.getVerificationCode());
        emailService.sendMail(user.getEmailId(), "Zoom Car:Account verification code", mailContent.toString());
    }
}
