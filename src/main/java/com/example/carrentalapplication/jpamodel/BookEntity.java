package com.example.carrentalapplication.jpamodel;


import javax.persistence.*;

@Entity
@Table(name = "book")

public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_Id")
    private int bookingId;

    @Column(name = "pickupDate", nullable = false)
    private String pickupDate;

    @Column(name = "returnDate", nullable = false)
    private String returnDate;

    @Column(name = "rentaldays", nullable = false, length = 10)
    private String rentalDays;

    @Column(name = "totalcost", nullable = false, length = 10)
    private String totalCost;

    @Column(name = "license", nullable = false)
    private String license;

    //    @Column(name = "car_id ", nullable = false)
//    private int carId;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private PaymentDetailsEntity paymentDetailsEntity;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarDetailsEntity carDetailsEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


//    @Column(name = "payment_id ")
//    private int paymentId;

    @Column(name = "razorpay_order_id")
    private String razorpayOrderId;

    @Column(name = "razorpay_payment_id")
    private String razorpayPaymentId;

    public PaymentDetailsEntity getPaymentDetailsEntity() {
        return paymentDetailsEntity;
    }

    public void setPaymentDetailsEntity(PaymentDetailsEntity paymentDetailsEntity) {
        this.paymentDetailsEntity = paymentDetailsEntity;
    }
//    CarDetailsEntity carDetails;


    //    public CarDetailsEntity getCarDetails() {
//        return carDetails;
//    }
//
//    public void setCarDetails(CarDetailsEntity carDetails) {
//        this.carDetails = carDetails;
//    }
    public CarDetailsEntity getCarDetailsEntity() {
        return carDetailsEntity;
    }

    public void setCarDetailsEntity(CarDetailsEntity carDetailsEntity) {
        this.carDetailsEntity = carDetailsEntity;
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

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(String rentalDays) {
        this.rentalDays = rentalDays;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {

        this.totalCost = totalCost;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

//    public int getCarId() {
//        return carId;
//    }
//
//    public void setCarId(int carId) {
//        this.carId = carId;
//    }


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


}
