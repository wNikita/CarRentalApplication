package com.example.carrentalapplication.controller.car;

import com.example.carrentalapplication.dao.AddressDAO;
import com.example.carrentalapplication.dao.AgencyDAO;
import com.example.carrentalapplication.dao.CarDAO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.AgencyDetailsEntity;
import com.example.carrentalapplication.jpamodel.CarDetailsEntity;
import com.example.carrentalapplication.jpamodel.UserEntity;
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AgencyDAO agencyDAO = new AgencyDAO();
        CarDAO carDAO = new CarDAO();
        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("CurrentUser");

//        AgencyDetails agencyDetails1 = agencyDAO.viewAgencyDetails(user.getUserId());
        try {
            List<AgencyDetailsEntity> agencyDetailsEntities = agencyDAO.viewAgencyByUserId(user.getUserId());
            for (AgencyDetailsEntity agencyDetailsEntity : agencyDetailsEntities) {
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("AgencyUser", agencyDetailsEntity);

                String sortOrder = req.getParameter("sort");

                List<CarDetailsEntity> carList = null;
                if ("name_asc" .equals(sortOrder)) {
                    try {
                        carList = carDAO.carNameByAscending(agencyDetailsEntity.getAgencyDetailsId());
                        req.setAttribute("carDetails", carList);

                    } catch (DAOException e) {
                        e.printStackTrace();
                    }
                } else if ("name_desc" .equals(sortOrder)) {
                    try {
                        carList = carDAO.carNameByDesc(agencyDetailsEntity.getAgencyDetailsId());
                        req.setAttribute("carDetails", carList);

                    } catch (DAOException e) {
                        e.printStackTrace();
                    }
                } else if ("rate_max" .equals(sortOrder)) {
                    try {
                        carList = carDAO.carRatePerDayByDesc(agencyDetailsEntity.getAgencyDetailsId());
                        req.setAttribute("carDetails", carList);

                    } catch (DAOException e) {
                        e.printStackTrace();
                    }
                } else if ("rate_min" .equals(sortOrder)) {
                    try {
                        carList = carDAO.carRatePerDayByAsc(agencyDetailsEntity.getAgencyDetailsId());
                        req.setAttribute("carDetails", carList);

                    } catch (DAOException e) {
                        e.printStackTrace();
                    }
                }

                //        } else {
//            carList = carDAO.getCarsSortedByNameDescending();
//        }

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("ViewCar.jsp");
                requestDispatcher.forward(req, resp);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
    //RequestDispatcher requestDispatcher = req.getRequestDispatcher("AddCar.jsp");
    //requestDispatcher.forward(req, resp);
}


