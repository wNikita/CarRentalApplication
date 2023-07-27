package com.example.carrentalapplication.dto;


import com.example.carrentalapplication.model.CarDetails;

public class BookDTO {

    private String pickupDateDTO;
    private String returnDateDTO;
    private String rentalDaysDTO;
    private String totalCostDTO;
    private String licenseDTO;
    private String  carIdDTO;
    private int userIdDTO;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private CarDetails carDetails;

    public CarDetails getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(CarDetails carDetails) {
        this.carDetails = carDetails;
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

    public int getUserIdDTO() {
        return userIdDTO;
    }

    public void setUserIdDTO(int userIdDTO) {
        this.userIdDTO = userIdDTO;
    }

    public String getPickupDateDTO() {
        return pickupDateDTO;
    }

    public void setPickupDateDTO(String pickupDateDTO) {
        this.pickupDateDTO = pickupDateDTO;
    }

    public String getReturnDateDTO() {
        return returnDateDTO;
    }

    public void setReturnDateDTO(String returnDateDTO) {
        this.returnDateDTO = returnDateDTO;
    }

    public String getRentalDaysDTO() {
        return rentalDaysDTO;
    }

    public void setRentalDaysDTO(String rentalDaysDTO) {
        this.rentalDaysDTO = rentalDaysDTO;
    }

    public String getTotalCostDTO() {
        return totalCostDTO;
    }

    public void setTotalCostDTO(String totalCostDTO) {
        this.totalCostDTO = totalCostDTO;
    }

    public String getLicenseDTO() {
        return licenseDTO;
    }

    public void setLicenseDTO(String licenseDTO) {
        this.licenseDTO = licenseDTO;
    }

    public String getCarIdDTO() {
        return carIdDTO;
    }

    public void setCarIdDTO(String carIdDTO) {
        this.carIdDTO = carIdDTO;
    }



}
