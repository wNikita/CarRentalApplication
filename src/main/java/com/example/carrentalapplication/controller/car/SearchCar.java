package com.example.carrentalapplication.controller.car;

import com.example.carrentalapplication.dao.CarDAO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.CarDetailsEntity;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchCar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarDAO carDAO = new CarDAO();

        int cityId = Integer.parseInt(req.getParameter("city"));
        String PickUpDate = req.getParameter("startDate");
        String ReturnDate = req.getParameter("endDate");
        try {
            List<CarDetailsEntity> carDetails = carDAO.viewCarByCity(cityId);
            req.setAttribute("carDetails", carDetails);
            req.setAttribute("PickUpdate", PickUpDate);
            req.setAttribute("ReturnDate", ReturnDate);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("CustomerCarView.jsp");
            requestDispatcher.forward(req, resp);
        } catch (DAOException e) {
            e.printStackTrace();
        }


    }
}
