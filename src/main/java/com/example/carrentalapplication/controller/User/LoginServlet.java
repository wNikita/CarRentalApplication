package com.example.carrentalapplication.controller.User;

import com.example.carrentalapplication.Validation.UserValidation;
import com.example.carrentalapplication.common.Constant;
import com.example.carrentalapplication.dao.UserDAO;
import com.example.carrentalapplication.dto.UserDTO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.model.AgencyDetails;
import com.example.carrentalapplication.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginServlet extends HttpServlet {

    UserDAO userDAO = new UserDAO();
    User user1=new User();
    UserDTO userDTO=new UserDTO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        userDTO.setEmailId(req.getParameter("email"));
        userDTO.setPassword(req.getParameter("password"));

        List<Error> errorList = UserValidation.UserValidate(userDTO);
        if (!errorList.isEmpty()) {
            req.setAttribute("errorList", errorList);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Login.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            try {
                User user = userDAO.getUserByCredentials(userDTO.getEmailId(), userDTO.getPassword());
                if (user != null) {
                    HttpSession session = req.getSession();
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
        User user = (User) session.getAttribute("CurrentUser");

        if (user.isVerified()) {
            if (user.getRoleId() == Constant.ROLE_CAR_AGENCY) {
                if (!user.isLogged()) {
                    resp.sendRedirect("agency-form");
                    userDAO.manageLoginStatus(user.getEmailId(), true);
                } else {
                    RequestDispatcher requestDispatcher= req.getRequestDispatcher("Admin.jsp");
                    requestDispatcher.forward(req,resp);
                }
            } else {
                resp.sendRedirect("viewStateCity");
            }
        } else {
            RequestDispatcher requestDispatcher= req.getRequestDispatcher("Verify.jsp");
            requestDispatcher.forward(req,resp);
        }

    }
}
