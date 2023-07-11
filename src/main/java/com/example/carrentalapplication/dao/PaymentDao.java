package com.example.carrentalapplication.dao;

import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.model.PaymentDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PaymentDao {

    public PaymentDetails addPaymentDetails(PaymentDetails payment) throws DAOException {
        try {
            String sql="INSERT INTO payment (Payment_type) VALUES (?)";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,payment.getPaymentStatus());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                payment.setPaymentId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new DAOException("Error while connecting", e);
        }
        return payment;
    }

}
