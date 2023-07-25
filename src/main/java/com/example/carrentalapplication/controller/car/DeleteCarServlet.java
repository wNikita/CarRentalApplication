package com.example.carrentalapplication.controller.car;

import com.example.carrentalapplication.dao.CarDAO;
import com.example.carrentalapplication.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

          CarDAO carDAO=new CarDAO();

        int carId = Integer.parseInt(req.getParameter("carId"));
        try {
            carDAO.deleteCar(carId);
            resp.sendRedirect("view-car");
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
