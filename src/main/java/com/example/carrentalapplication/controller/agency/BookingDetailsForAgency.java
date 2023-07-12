package com.example.carrentalapplication.controller.agency;

import com.example.carrentalapplication.dao.AgencyDAO;
import com.example.carrentalapplication.dao.BookDao;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.model.AgencyDetails;
import com.example.carrentalapplication.model.Book;
import com.example.carrentalapplication.model.User;

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
        User user = (User) session.getAttribute("CurrentUser");
        try {
        AgencyDAO agencyDAO=new AgencyDAO();
        AgencyDetails agencyDetails=agencyDAO.getAgenciesByUserId(user.getUserId());

        BookDao bookDao=new BookDao();

           List<Book> bookList= bookDao.BookingDataForAgencyAdmin(agencyDetails.getAgencyDetailsId());
           req.setAttribute("bookingDetails",bookList);

            RequestDispatcher requestDispatcher= req.getRequestDispatcher("AdminViewBooking.jsp");
            requestDispatcher.forward(req,resp);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
