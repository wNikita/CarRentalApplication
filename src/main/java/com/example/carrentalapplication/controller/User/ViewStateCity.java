package com.example.carrentalapplication.controller.User;

import com.example.carrentalapplication.dao.AddressDAO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.CityEntity;
import com.example.carrentalapplication.jpamodel.StateEntity;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewStateCity extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddressDAO addressDAO = new AddressDAO();

        try {
            List<StateEntity> states = addressDAO.getAllState();
            req.setAttribute("states", states);
            if (req.getParameter("stateID") != null) {
                int stateID = Integer.parseInt(req.getParameter("stateID"));
                List<CityEntity> cityList = addressDAO.getAllCityByState(stateID);
                req.setAttribute("cityList", cityList);
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Customer.jsp");
            requestDispatcher.forward(req, resp);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

}
