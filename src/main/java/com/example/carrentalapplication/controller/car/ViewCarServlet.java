package com.example.carrentalapplication.controller.car;

import com.example.carrentalapplication.dao.AgencyDAO;
import com.example.carrentalapplication.dao.CarDAO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.AgencyDetailsEntity;
import com.example.carrentalapplication.jpamodel.CarDetailsEntity;
import com.example.carrentalapplication.jpamodel.UserEntity;
import com.example.carrentalapplication.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

public class ViewCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AgencyDAO agencyDAO = new AgencyDAO();
        try {
            HttpSession session = req.getSession();
            UserEntity user = (UserEntity) session.getAttribute("CurrentUser");
            List<AgencyDetailsEntity> agencyDetails1 = agencyDAO.viewAgencyByUserId(user.getUserId());
            for (AgencyDetailsEntity agencyDetailsEntity : agencyDetails1) {
                CarDAO carDAO = new CarDAO();
                List<CarDetailsEntity> carDetails = carDAO.viewAllCarByAgency(agencyDetailsEntity.getAgencyDetailsId());
                req.setAttribute("carDetails", carDetails);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("ViewCar.jsp");
        requestDispatcher.forward(req, resp);
    }

}
