package com.example.carrentalapplication.controller.agency;

import com.example.carrentalapplication.Validation.AgencyValidation;
import com.example.carrentalapplication.dao.AddressDAO;
import com.example.carrentalapplication.dao.AgencyDAO;
import com.example.carrentalapplication.dto.AddressDetailsDTO;
import com.example.carrentalapplication.dto.AgencyDetailsDTO;
import com.example.carrentalapplication.dto.CityDTO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.AgencyDetailsEntity;
import com.example.carrentalapplication.jpamodel.CityEntity;
import com.example.carrentalapplication.jpamodel.StateEntity;
import com.example.carrentalapplication.jpamodel.UserEntity;
import com.example.carrentalapplication.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UpdateAgencyProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AgencyDAO agencyDAO = new AgencyDAO();
        AddressDAO addressDAO = new AddressDAO();

        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("CurrentUser");

        List<AgencyDetailsEntity> agencyDetails1 = null;
        try {
            agencyDetails1 = agencyDAO.viewAgencyByUserId(user.getUserId());
            for (AgencyDetailsEntity agencyDetailsEntity : agencyDetails1) {
                List<StateEntity> states = addressDAO.getAllState();
                req.setAttribute("states", states);
                req.setAttribute("stateID", agencyDetailsEntity.getAddressDetailsEntity().getCityId().getStateEntity().getStateId());
                if (req.getParameter("stateID") != null) {
                    int stateID = Integer.parseInt(req.getParameter("stateID"));
                    List<CityEntity> cityList = addressDAO.getAllCityByState(stateID);
                    req.setAttribute("cityList", cityList);
                    req.setAttribute("cityId", agencyDetailsEntity.getAddressDetailsEntity().getCityId().getCityId());

                }
                req.setAttribute("agency", agencyDetailsEntity);

            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("UpdateAgencyProfile.jsp?userId=" + user.getUserId());
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String agencyId = request.getParameter("agencyId");
        String cityId = request.getParameter("city");
        AgencyDetailsDTO agencyDetailsDTO = new AgencyDetailsDTO();
        agencyDetailsDTO.setAgencyDetailsId(request.getParameter("agencyId"));
        agencyDetailsDTO.setAgencyName(request.getParameter("agencyName"));
        agencyDetailsDTO.setMobileNumber(request.getParameter("mobileNumber"));
        agencyDetailsDTO.setGSTNumber(request.getParameter("gstNumber"));
        AddressDetailsDTO addressDetailsDTO = new AddressDetailsDTO();
        addressDetailsDTO.setAddressLine(request.getParameter("Addressline"));
        addressDetailsDTO.setPinCode(request.getParameter("PinCode"));
        agencyDetailsDTO.setAddressDetailsDTO(addressDetailsDTO);
        List<Error> errorList = AgencyValidation.AgencyValidate(agencyDetailsDTO);
        if (!errorList.isEmpty()) {
            request.setAttribute("errorList", errorList);
            request.setAttribute("agency", agencyDetailsDTO);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("UpdateAgencyProfile.jsp");
            requestDispatcher.forward(request, response);
        } else {
            CityDTO cityDTO = new CityDTO();
            cityDTO.setCityId(cityId);
            agencyDetailsDTO.getAddressDetailsDTO().setCity(cityDTO);


            AgencyDAO agencyDAO = new AgencyDAO();
            try {
//                agencyDAO.updateAgency(agencyDetailsDTO, agencyId);
                setDataOfAgency(agencyDetailsDTO);
                agencyDAO.updateAddress(addressDetailsDTO, agencyId);
                agencyDAO.updateCity(agencyId, addressDetailsDTO.getCity().getCityId());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Admin.jsp");
                requestDispatcher.forward(request, response);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setDataOfAgency(AgencyDetailsDTO agency) throws DAOException {
        AgencyDAO agencyDAO = new AgencyDAO();
        AgencyDetailsEntity agencyDetailsEntity = new AgencyDetailsEntity();
        agencyDetailsEntity.setAgencyDetailsId(Integer.parseInt(agency.getAgencyDetailsId()));
        agencyDetailsEntity.setAgencyName(agency.getAgencyName());
        agencyDetailsEntity.setMobileNumber(agency.getMobileNumber());
        agencyDetailsEntity.setGSTNumber(agency.getGSTNumber());
        agencyDAO.userAgencyProfile(agencyDetailsEntity);

    }
}


