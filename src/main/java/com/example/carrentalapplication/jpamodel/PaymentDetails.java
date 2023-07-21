package com.example.carrentalapplication.jpamodel;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="")

public class PaymentDetails {
    @Column(name="")

    int paymentId;
    @Column(name="")

    String paymentStatus;

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
