package com.example.carrentalapplication.jpamodel;


import javax.persistence.*;
import java.util.Date;

@Table(name = "book")

public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int bookingId;

    @Column(name = "pickupDate",nullable = false)
    Date pickupDate;

    @Column(name = "returnDate",nullable = false)
    Date returnDate;

    @Column(name = "rentaldays",nullable = false,length = 10)
    int rentalDays;

    @Column(name = "totalcost",nullable = false,length = 10)
    int totalCost;

    @Column(name = "license",nullable = false)
    String license;

    @Column(name = "car_id ",nullable = false)
    int carId;

    @Column(name = "user_id ",nullable = false)
    int userId;


    @Column(name = "payment_id ")
    int paymentId;

    @Column(name = "razorpay_order_id")
    String razorpayOrderId;

    @Column(name = "razorpay_payment_id")
    String razorpayPaymentId;


    Date createdDate;
    CarDetailsEntity carDetails;


    public CarDetailsEntity getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(CarDetailsEntity carDetails) {
        this.carDetails = carDetails;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
}
