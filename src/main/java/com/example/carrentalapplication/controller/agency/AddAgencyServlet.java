package com.example.carrentalapplication.controller.agency;

import com.example.carrentalapplication.dao.AddressDAO;
import com.example.carrentalapplication.dao.AgencyDAO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AddAgencyServlet extends HttpServlet {

    AddressDAO addressDAO = new AddressDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<State> states = addressDAO.getState();
            req.setAttribute("states", states);
            if (req.getParameter("stateID") != null) {
                int stateID = Integer.parseInt(req.getParameter("stateID"));
                List<City> cityList = addressDAO.getCityByState(stateID);
                req.setAttribute("cityList", cityList);
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("AddAgency.jsp");
            requestDispatcher.forward(req, resp);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddressDetails addressDetails = new AddressDetails();
        addressDetails.setAddressLine(req.getParameter("addressLine"));
        addressDetails.setPinCode(req.getParameter("pinCode"));
        int cityId = Integer.parseInt(req.getParameter("city"));
        City city = new City(cityId);
        addressDetails.setCity(city);

        AddressDAO addressDAO = new AddressDAO();
        try {
            addressDAO.addAddress(addressDetails, cityId);
            addressDetails = addressDAO.getAddress();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("CurrentUser");

        int addressID = addressDetails.getAddressID();
        AgencyDetails agencyDetails = new AgencyDetails();
        agencyDetails.setAgencyName(req.getParameter("agencyName"));
        agencyDetails.setGSTNumber(req.getParameter("gstNumber"));
        agencyDetails.setMobileNumber(req.getParameter("mobileNumber"));

        AgencyDAO agencyDAO = new AgencyDAO();
        try {
          agencyDAO.addAgency(user.getUserId(), agencyDetails, addressID);
          RequestDispatcher requestDispatcher= req.getRequestDispatcher("Admin.jsp");
          requestDispatcher.forward(req,resp);
            } catch (DAOException e) {
            e.printStackTrace();
        }

    }
}
