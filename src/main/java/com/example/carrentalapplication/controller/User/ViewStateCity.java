package com.example.carrentalapplication.controller.User;

import com.example.carrentalapplication.dao.AddressDAO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.model.City;
import com.example.carrentalapplication.model.State;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewStateCity extends HttpServlet {
    AddressDAO addressDAO=new AddressDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<State> states = addressDAO.getState();
            req.setAttribute("states", states);
            if (req.getParameter("stateID") != null) {
                int stateID = Integer.parseInt(req.getParameter("stateID"));
                List<City> cityList = addressDAO.getCityByState(stateID);
                req.setAttribute("cityList", cityList);
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Customer.jsp");
            requestDispatcher.forward(req, resp);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

}
