package com.example.carrentalapplication.controller.agency;

import com.example.carrentalapplication.dao.AddressDAO;
import com.example.carrentalapplication.dao.AgencyDAO;
import com.example.carrentalapplication.dao.UserDAO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.*;
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AddressDAO addressDAO = new AddressDAO();

            List<StateEntity> states = addressDAO.getAllState();
            req.setAttribute("states", states);
            if (req.getParameter("stateID") != null) {
                int stateID = Integer.parseInt(req.getParameter("stateID"));
                List<CityEntity> cityList = addressDAO.getAllCityByState(stateID);
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
        UserDAO userDAO = new UserDAO();
        addressDetails.setAddressLine(req.getParameter("addressLine"));
        addressDetails.setPinCode(req.getParameter("pinCode"));
        int cityId = Integer.parseInt(req.getParameter("city"));
        City city = new City(cityId);
        addressDetails.setCity(city);

        AddressDAO addressDAO = new AddressDAO();
        try {
            AddressDetailsEntity addressDetailsEntity = new AddressDetailsEntity();
            addressDetailsEntity.setAddressLine(addressDetails.getAddressLine());
            addressDetailsEntity.setPinCode(addressDetails.getPinCode());
            CityEntity cityEntity = new CityEntity();
            cityEntity.setCityId(cityId);
            addressDetailsEntity.setCityId(cityEntity);
            addressDAO.addAddress(addressDetailsEntity);
            addressDetailsEntity.getAddressID();

            HttpSession session = req.getSession();
            UserEntity user = (UserEntity) session.getAttribute("CurrentUser");

//        int addressID = addressDetails.getAddressID();
            AgencyDetails agencyDetails = new AgencyDetails();
            agencyDetails.setAgencyName(req.getParameter("agencyName"));
            agencyDetails.setGSTNumber(req.getParameter("gstNumber"));
            agencyDetails.setMobileNumber(req.getParameter("mobileNumber"));

            AgencyDAO agencyDAO = new AgencyDAO();
            AgencyDetailsEntity agencyDetailsEntity = new AgencyDetailsEntity();
//            AddressDetailsEntity addressDetailsEntity=new AddressDetailsEntity();
            addressDetailsEntity.setAddressID(addressDetailsEntity.getAddressID());
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(user.getUserId());
            agencyDetailsEntity.setAgencyName(agencyDetails.getAgencyName());
            agencyDetailsEntity.setGSTNumber(agencyDetails.getGSTNumber());
            agencyDetailsEntity.setMobileNumber(agencyDetails.getGSTNumber());
            agencyDetailsEntity.setAddressDetailsEntity(addressDetailsEntity);
            agencyDetailsEntity.setUser(user);
            agencyDAO.registerAgency(agencyDetailsEntity);
//            agencyDAO.addAgency(user.getUserId(), agencyDetails, addressID);
//            userDAO.manageLoginStatus(user.getEmailId(), true);
            userDAO.userLoginStatus(user.getEmailId(), true);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Admin.jsp");
            requestDispatcher.forward(req, resp);
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }
}
