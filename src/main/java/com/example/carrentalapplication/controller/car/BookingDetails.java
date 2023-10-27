package com.example.carrentalapplication.controller.car;

import com.example.carrentalapplication.dao.BookDao;
import com.example.carrentalapplication.exception.DAOException;
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

public class BookingDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("CurrentUser");

        BookDao bookDao = new BookDao();
        try {
            List<BookEntity> book = bookDao.viewBookingData(user.getUserId());
            req.setAttribute("bookingDetails", book);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("BookingDetails.jsp");
            requestDispatcher.forward(req, resp);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
