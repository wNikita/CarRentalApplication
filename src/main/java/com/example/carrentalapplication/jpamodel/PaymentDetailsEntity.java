package com.example.carrentalapplication.jpamodel;

import javax.persistence.*;

@Entity
@Table(name = "payment")

public class PaymentDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentId;

    @Column(name = "Payment_name",nullable = false,length = 20)
    private String paymentStatus;

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
