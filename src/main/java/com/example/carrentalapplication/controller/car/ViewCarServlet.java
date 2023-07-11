package com.example.carrentalapplication.controller.car;

import com.example.carrentalapplication.dao.AgencyDAO;
import com.example.carrentalapplication.dao.CarDAO;
import com.example.carrentalapplication.exception.DAOException;
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
        AgencyDAO agencyDAO=new AgencyDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("CurrentUser");
        AgencyDetails agencyDetails1=agencyDAO.viewAgencyDetails(user.getUserId());

        CarDAO carDAO=new CarDAO();
        try {
            List<CarDetails> carDetails = carDAO.viewAllCar(agencyDetails1.getAgencyDetailsId());


            req.setAttribute("carDetails", carDetails);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher=req.getRequestDispatcher("ViewCar.jsp");
        requestDispatcher.forward(req,resp);
    }

}
