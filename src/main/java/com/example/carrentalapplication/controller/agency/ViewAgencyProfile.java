package com.example.carrentalapplication.controller.agency;

import com.example.carrentalapplication.dao.AddressDAO;
import com.example.carrentalapplication.dao.AgencyDAO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.AgencyDetailsEntity;
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
import java.util.List;

public class ViewAgencyProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AgencyDAO agencyDAO = new AgencyDAO();

        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("CurrentUser");

        List<AgencyDetailsEntity> agencyDetails = null;
        try {
            agencyDetails = agencyDAO.viewAgencyByUserId(user.getUserId());
            for (AgencyDetailsEntity agencyDetailsEntity : agencyDetails) {
                request.setAttribute("agency", agencyDetailsEntity);
                agencyDetailsEntity.getAddressDetailsEntity().getAddressLine();
                agencyDetailsEntity.getAddressDetailsEntity().getCityId().getCityName();
            }
            //getAgenciesByUserId(user.getUserId());
        } catch (DAOException e) {
            e.printStackTrace();
        }


        // Forward the request to the JSP page for rendering the agency details
        RequestDispatcher dispatcher = request.getRequestDispatcher("AgencyProfile.jsp");
        dispatcher.forward(request, response);
    }
}
