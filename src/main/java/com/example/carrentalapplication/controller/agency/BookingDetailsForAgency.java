package com.example.carrentalapplication.controller.agency;

import com.example.carrentalapplication.dao.AgencyDAO;
import com.example.carrentalapplication.dao.BookDao;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.AgencyDetailsEntity;
import com.example.carrentalapplication.jpamodel.BookEntity;
import com.example.carrentalapplication.jpamodel.UserEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BookingDetailsForAgency extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("CurrentUser");
        try {
            AgencyDAO agencyDAO = new AgencyDAO();
            List<AgencyDetailsEntity> agencyDetails = agencyDAO.viewAgencyByUserId(user.getUserId());
//                    getAgenciesByUserId(user.getUserId());
            for (AgencyDetailsEntity agencyDetailsEntity : agencyDetails) {
                BookDao bookDao = new BookDao();

                List<BookEntity> bookList = bookDao.viewBookingDataForAgencyAdmin(agencyDetailsEntity.getAgencyDetailsId());
                req.setAttribute("bookingDetails", bookList);

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("AdminViewBooking.jsp");
                requestDispatcher.forward(req, resp);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }
}
