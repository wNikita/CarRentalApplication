package com.example.carrentalapplication.controller.User;

import com.example.carrentalapplication.Validation.UserValidation;
import com.example.carrentalapplication.common.Constant;
import com.example.carrentalapplication.dao.UserDAO;
import com.example.carrentalapplication.dto.UserDTO;
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

public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        UserDTO userDTO = new UserDTO();

        userDTO.setEmailId(req.getParameter("email"));
        userDTO.setPassword(req.getParameter("password"));
        List<Error> errorList = UserValidation.UserValidate(userDTO);
        if (!errorList.isEmpty()) {
            req.setAttribute("errorList", errorList);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Login.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            try {
                //  User user = userDAO.getUserByCredentials(userDTO.getEmailId(), userDTO.getPassword());
                List<UserEntity> user2 = userDAO.loginCredentials(userDTO.getEmailId(), userDTO.getPassword());
                if (user2 != null) {
                    HttpSession session = req.getSession();
                    for (UserEntity user : user2)
                        session.setAttribute("CurrentUser", user);
                    checkUser(req, resp);
                } else {
                    req.setAttribute("errorMsg", "Wrong credentials");
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("Login.jsp");
                    requestDispatcher.forward(req, resp);
                }
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("CurrentUser");

        if (user.isVerified()) {
            if (user.getRoleEntity().getRoleId() == Constant.ROLE_CAR_AGENCY) {
                if (!user.isLogged()) {
                    resp.sendRedirect("agency-form");
                } else {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("Admin.jsp");
                    requestDispatcher.forward(req, resp);
                }
            } else {
                resp.sendRedirect("viewStateCity");
            }
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Verify.jsp");
            requestDispatcher.forward(req, resp);
        }

    }
}
