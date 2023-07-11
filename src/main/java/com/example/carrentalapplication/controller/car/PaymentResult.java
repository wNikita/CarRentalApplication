package com.example.carrentalapplication.controller.car;

import com.example.carrentalapplication.dao.BookDao;
import com.example.carrentalapplication.dao.PaymentDao;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.model.PaymentDetails;
import com.razorpay.*;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PaymentResult extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paymentId = request.getParameter("razorpay_payment_id");
        String orderId = request.getParameter("razorpay_order_id");
        String razorpaySignature = request.getParameter("razorpay_signature");


        boolean statusIsSuccess = (paymentId != null && orderId != null && razorpaySignature != null);

        if (statusIsSuccess) {
            try {
                RazorpayClient razorpay = new RazorpayClient("rzp_test_QEtxzC00WQzK7n", "w3Qf6dDLepikuqqrAks4d4LD");
                String secret = "w3Qf6dDLepikuqqrAks4d4LD";

                JSONObject options = new JSONObject();
                options.put("razorpay_order_id", orderId);
                options.put("razorpay_payment_id", paymentId);
                options.put("razorpay_signature", razorpaySignature);
                boolean status = Utils.verifyPaymentSignature(options, secret);
                if (status) {
                    statusIsSuccess = true;
                    try {
                       RazorpayClient razorpayClient = new RazorpayClient("rzp_test_QEtxzC00WQzK7n", "w3Qf6dDLepikuqqrAks4d4LD");
                        Order order = razorpayClient.Orders.fetch(orderId);
                        Payment payment = razorpayClient.Payments.fetch(paymentId);
                        Payment payment1 = razorpay.Payments.capture(paymentId, options);
                        request.setAttribute("paymentDetails",payment1);
                        request.setAttribute("orderDetails",order);
                        request.setAttribute("paymentDetails",payment);
                        PaymentDao paymentDao=new PaymentDao();
                        PaymentDetails paymentDetails1=new PaymentDetails();
                        paymentDetails1.setPaymentStatus(payment.get("method"));
                        PaymentDetails paymentDetails=paymentDao.addPaymentDetails(paymentDetails1);

                        BookDao bookDAO = new BookDao();
                        bookDAO.updatePaymentId(orderId, paymentId,paymentDetails.getPaymentId());

                    } catch (RazorpayException e) {
                        e.printStackTrace();
                    } catch (DAOException e) {
                        e.printStackTrace();
                    }


                } else {
                    statusIsSuccess = false;
                }

            } catch (RazorpayException e) {
                e.printStackTrace();
                statusIsSuccess = false;
            }
        } else {
            statusIsSuccess = false;
        }



        request.setAttribute("statusIsSuccess", statusIsSuccess);
        request.getRequestDispatcher("PaymentResult.jsp").forward(request, response);
    }
}
