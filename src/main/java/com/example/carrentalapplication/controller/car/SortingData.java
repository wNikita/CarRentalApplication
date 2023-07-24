package com.example.carrentalapplication.controller.car;

import com.example.carrentalapplication.dao.AddressDAO;
import com.example.carrentalapplication.dao.AgencyDAO;
import com.example.carrentalapplication.dao.CarDAO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.model.AgencyDetails;
import com.example.carrentalapplication.model.CarDetails;
import com.example.carrentalapplication.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SortingData extends HttpServlet {
    AgencyDAO agencyDAO = new AgencyDAO();
    CarDAO carDAO = new CarDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("CurrentUser");

        AgencyDetails agencyDetails1 = agencyDAO.viewAgencyDetails(user.getUserId());
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("AgencyUser", agencyDetails1);

        String sortOrder = req.getParameter("sort");

        List<CarDetails> carList = null;
        if ("name_asc".equals(sortOrder)) {
            try {
                carList = carDAO.getCarsSortedByNameAscending(agencyDetails1.getAgencyDetailsId());
            } catch (DAOException e) {
                e.printStackTrace();
            }
        } else if ("name_desc".equals(sortOrder)) {
            try {
                carList = carDAO.getCarsSortedByNameDesc(agencyDetails1.getAgencyDetailsId());
            } catch (DAOException e) {
                e.printStackTrace();
            }
        } else if ("rate_max".equals(sortOrder)) {
            try {
                carList = carDAO.getCarsSortedByRentalRateDesc(agencyDetails1.getAgencyDetailsId());
            } catch (DAOException e) {
                e.printStackTrace();
            }
        } else if ("rate_min".equals(sortOrder)) {
            try {
                carList = carDAO.getCarsSortedByRentalRate(agencyDetails1.getAgencyDetailsId());
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }

        //        } else {
//            carList = carDAO.getCarsSortedByNameDescending();
//        }

        req.setAttribute("carDetails", carList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("ViewCar.jsp");
        requestDispatcher.forward(req, resp);
    }
    //RequestDispatcher requestDispatcher = req.getRequestDispatcher("AddCar.jsp");
    //requestDispatcher.forward(req, resp);
}


