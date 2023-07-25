package com.example.carrentalapplication.controller.agency;

import com.example.carrentalapplication.dao.AddressDAO;
import com.example.carrentalapplication.dao.AgencyDAO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.UserEntity;
import com.example.carrentalapplication.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ViewAgencyProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Replace with your own DAO class
        AgencyDAO agencyDAO = new AgencyDAO();

        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("CurrentUser");

        AgencyDetails agencyDetails = null;
        try {
            agencyDetails = agencyDAO.getAgenciesByUserId(user.getUserId());
        } catch (DAOException e) {
            e.printStackTrace();
        }
        request.setAttribute("agency", agencyDetails);


        // Forward the request to the JSP page for rendering the agency details
        RequestDispatcher dispatcher = request.getRequestDispatcher("AgencyProfile.jsp");
        dispatcher.forward(request, response);
    }
}
