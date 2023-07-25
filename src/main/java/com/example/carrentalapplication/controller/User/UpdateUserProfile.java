package com.example.carrentalapplication.controller.User;

import com.example.carrentalapplication.Validation.UserValidation;
import com.example.carrentalapplication.dao.UserDAO;
import com.example.carrentalapplication.dto.UserDTO;
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

public class UpdateUserProfile extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("CurrentUser");

        req.setAttribute("user", user);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("UpdateUserProfile.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        UserDTO userDTO = new UserDTO();
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        String mobileNumber = request.getParameter("mobilenumber");
        String email = request.getParameter("email");

        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("CurrentUser");


            // Update the user profile
            userDTO.setFirstName(firstname);
            userDTO.setLastName(lastname);
            userDTO.setAddress(address);
            userDTO.setMobileNO(mobileNumber);
            userDTO.setEmailId(email);
            List<Error> errorList = UserValidation.validateUpdateUser(userDTO);
            if (!errorList.isEmpty()) {
                request.setAttribute("errorList", errorList);

                response.sendRedirect("user-update?userID=" + user.getUserId());
            } else {
                UserEntity userEntity = new UserEntity();
                userEntity.setFirstName(userDTO.getFirstName());
                userEntity.setLastName(userDTO.getLastName());
                userEntity.setAddress(userDTO.getAddress());
                userEntity.setMobileNumber(userDTO.getMobileNO());
                userEntity.setEmailId(userDTO.getEmailId());
                userDAO.userUpdate(userEntity, user.getUserId());
//                userDAO.updateUser(user, user.getUserId());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Admin.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}

