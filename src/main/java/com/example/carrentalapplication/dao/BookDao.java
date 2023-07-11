package com.example.carrentalapplication.dao;

import com.example.carrentalapplication.dto.BookDTO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.model.Book;
import com.example.carrentalapplication.model.CarDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {


    public void BookCar(BookDTO bookDTO) throws DAOException {
        try {
            String sql = "INSERT INTO book (pickupDate, returnDate, rentaldays, totalcost, license, car_id, user_id,razorpay_order_id) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);

            stmt.setString(1, bookDTO.getPickupDateDTO());
            stmt.setString(2, bookDTO.getReturnDateDTO());
            stmt.setInt(3, Integer.parseInt(bookDTO.getRentalDaysDTO()));
            stmt.setString(4, (bookDTO.getTotalCostDTO()));
            stmt.setString(5, bookDTO.getLicenseDTO());
            stmt.setString(6, bookDTO.getCarIdDTO());
            stmt.setInt(7, bookDTO.getUserIdDTO());
            stmt.setString(8, bookDTO.getRazorpayOrderId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error while connecting", e);
        }
    }

    public void updatePaymentId(String orderId, String razorPaymentId, int paymentId) throws DAOException {

        try {
            String sql = "UPDATE book SET razorpay_payment_id = ?,payment_id=? WHERE razorpay_order_id = ?";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stmt.setString(1, razorPaymentId);
            stmt.setString(2, String.valueOf(paymentId));
            stmt.setString(3, orderId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error while connecting", e);
        }
    }


    public List<Book> BookingData(int userId) throws DAOException {
        List<Book> book = new ArrayList<>();

        try {

            String sql = "SELECT b.booking_Id,b.pickupDate,b.returnDate,b.rentaldays,b.totalcost,b.created_date," +
                    " c.name, c.number FROM book b INNER JOIN car c ON b.car_id = c.car_id WHERE b.user_id = ?";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Book book1 = new Book();
                book1.setBookingId(resultSet.getInt(1));
                book1.setPickupDate(resultSet.getDate(2));
                book1.setReturnDate(resultSet.getDate(3));
                book1.setRentalDays(resultSet.getInt(4));
                book1.setTotalCost(resultSet.getInt(5));
                book1.setCreatedDate(resultSet.getDate(6));
                CarDetails carDetails = new CarDetails();
                carDetails.setName(resultSet.getString(7));
                carDetails.setRegistrationNumber(resultSet.getInt(8));
                book1.setCarDetails(carDetails);
                book.add(book1);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while connecting", e);
        }
        return book;
    }

    public List<Book> BookingDataForAgencyAdmin(int agencyId) throws DAOException {
        List<Book> book = new ArrayList<>();

        try {
            String sql = "SELECT b.booking_Id,b.pickupDate,b.returnDate,b.rentaldays,b.totalcost,b.created_date," +
                    " c.name, c.number FROM book b INNER JOIN car c ON b.car_id = c.car_id WHERE c.car_agency_id = ? ";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stmt.setInt(1, agencyId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Book book1 = new Book();
                book1.setBookingId(resultSet.getInt(1));
                book1.setPickupDate(resultSet.getDate(2));
                book1.setReturnDate(resultSet.getDate(3));
                book1.setRentalDays(resultSet.getInt(4));
                book1.setTotalCost(resultSet.getInt(5));
                book1.setCreatedDate(resultSet.getDate(6));
                CarDetails carDetails = new CarDetails();
                carDetails.setName(resultSet.getString(7));
                carDetails.setRegistrationNumber(resultSet.getInt(8));
                book1.setCarDetails(carDetails);
                book.add(book1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}

