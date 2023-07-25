package com.example.carrentalapplication.controller.User;

import com.example.carrentalapplication.Validation.UserValidation;
import com.example.carrentalapplication.common.EmailService;
import com.example.carrentalapplication.common.Utility;
import com.example.carrentalapplication.dao.RoleDao;
import com.example.carrentalapplication.dao.UserDAO;
import com.example.carrentalapplication.dto.UserDTO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.RoleEntity;
import com.example.carrentalapplication.jpamodel.UserEntity;
import com.example.carrentalapplication.model.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SignUpServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        fillSignUpMasterData(req);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("SignUp.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName(req.getParameter("firstname"));
        userDTO.setLastName(req.getParameter("lastname"));
        userDTO.setAddress(req.getParameter("address"));
        userDTO.setPassword(req.getParameter("password"));
        userDTO.setMobileNO(req.getParameter("mobilenumber"));
        userDTO.setEmailId(req.getParameter("email"));
        userDTO.setRoleId(req.getParameter("role"));

        UserDAO userDAO = new UserDAO();
        List<Error> errorList = UserValidation.validateUser(userDTO);
        if (!errorList.isEmpty()) {
            fillSignUpMasterData(req);
            req.setAttribute("errorList", errorList);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("SignUp.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            try {
                if (userDAO.checkEmailExist(userDTO.getEmailId())) {
                    List<Error> errorList1 = UserValidation.emailValidate();
                    req.setAttribute("errorList", errorList1);
                    fillSignUpMasterData(req);

                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("SignUp.jsp");
                    requestDispatcher.forward(req, resp);
                } else if (userDAO.checkMobileNumberExist(userDTO.getMobileNO())) {
                    List<Error> errorList1 = UserValidation.mobileNumberValidate();
                    req.setAttribute("errorList", errorList1);
                    fillSignUpMasterData(req);

                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("SignUp.jsp");
                    requestDispatcher.forward(req, resp);
                } else {
                    String code = Utility.generateVerificationCode();
                    userDTO.setVerificationCode(code);
                    sendMail(userDTO);

                    UserEntity user = new UserEntity();

                    RoleEntity roleEntity = new RoleEntity();
                    roleEntity.setRoleId(Integer.parseInt(userDTO.getRoleId()));
                    user.setFirstName(userDTO.getFirstName());
                    user.setLastName(userDTO.getLastName());
                    user.setAddress(userDTO.getAddress());
                    user.setPassword(userDTO.getPassword());
                    user.setMobileNumber(userDTO.getMobileNO());
                    user.setEmailId(userDTO.getEmailId());
//                    user.setRoleId(Integer.parseInt(userDTO.getRoleId()));
                    user.setRoleEntity(roleEntity);
                    user.setVerificationCode(userDTO.getVerificationCode());
                    userDAO.registerUser(user);
//                    userDTO = userDAO.addUser(userDTO);
                    req.setAttribute("userId", user.getUserId());
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("Verify.jsp");
                    requestDispatcher.forward(req, resp);
                }
            } catch (DAOException e) {
                e.printStackTrace();
            }

        }
    }

    private void sendMail(UserDTO userDTO) {
        EmailService emailService = new EmailService();

        StringBuilder mailContent = new StringBuilder();
        mailContent.append("<H1>")
                .append("Hi, ").append(userDTO.getFirstName()).append(" ").append(userDTO.getLastName())
                .append("</H1>")
                .append("Your verification code :").append(userDTO.getVerificationCode());
        emailService.sendMail(userDTO.getEmailId(), "Zoom Car:Account verification code", mailContent.toString());
    }

    private void fillSignUpMasterData(HttpServletRequest request) {
        RoleDao roleDao = new RoleDao();
        try {
            List<Role> roles = roleDao.getRole();
            request.setAttribute("Role", roles);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
