package com.example.carrentalapplication.dao;

import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.CarDetailsEntity;
import com.example.carrentalapplication.jpamodel.PaymentDetailsEntity;
import com.example.carrentalapplication.model.PaymentDetails;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PaymentDao {

    public PaymentDetailsEntity addPayment(PaymentDetailsEntity paymentDetailsEntity) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(paymentDetailsEntity);
            em.getTransaction().commit();
            return paymentDetailsEntity;
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }

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
