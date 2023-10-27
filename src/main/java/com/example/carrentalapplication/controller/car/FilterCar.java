package com.example.carrentalapplication.controller.car;

import com.example.carrentalapplication.dao.AgencyDAO;
import com.example.carrentalapplication.dao.CarDAO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.AgencyDetailsEntity;
import com.example.carrentalapplication.jpamodel.CarDetailsEntity;
import com.example.carrentalapplication.jpamodel.UserEntity;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FilterCar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AgencyDAO agencyDAO = new AgencyDAO();
        CarDAO carDAO = new CarDAO();

        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("CurrentUser");
//        AgencyDetails agencyDetails1 = agencyDAO.viewAgencyDetails(user.getUserId());
        try {
            List<AgencyDetailsEntity> agencyDetailsEntities = agencyDAO.viewAgencyByUserId(user.getUserId());
            {
                for (AgencyDetailsEntity agencyDetailsEntity : agencyDetailsEntities) {
                    String fuelTypeFilter = req.getParameter("fuelType");
                    List<CarDetailsEntity> carDetails = carDAO.viewCarByFuelType(fuelTypeFilter, agencyDetailsEntity.getAgencyDetailsId());
                    req.setAttribute("carDetails", carDetails);

                    if (req.getParameter("transmission") != null) {
                        String transmissionFilter = req.getParameter("transmission");

                        List<CarDetailsEntity> filteredCars = carDAO.viewCarByFuelTypeAndTransmissionType(fuelTypeFilter, transmissionFilter, agencyDetailsEntity.getAgencyDetailsId());

                        req.setAttribute("carDetails", filteredCars);
                    }
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("ViewCar.jsp");
                    requestDispatcher.forward(req, resp);
                }
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}

