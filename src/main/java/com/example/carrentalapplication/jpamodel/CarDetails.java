//package com.example.carrentalapplication.jpamodel;
//
//import javax.persistence.Column;
//import javax.persistence.Table;
//
//@Table(name="")
//
//public class CarDetails implements  Comparable<CarDetails>{
//    @Column(name="car_id ")
//    private Integer carId;
//
//    @Column(name="name")
//    private String name;
//
//    @Column(name="number")
//    private Integer registrationNumber;
//
//    @Column(name="color")
//    private String color;
//
//    @Column(name="model")
//    private Integer model;
//
//    @Column(name="fuel_type")
//    private String fuelType;
//
//    @Column(name="transmission_type")
//    private String transmissionType;
//
//    @Column(name="insurance_no")
//    private Integer insurancePolicyNumber;
//
//    @Column(name="no_of_seats")
//    private Integer noOfSeats;
//
//    @Column(name="km_travelled\t")
//    private Integer kmTravelled;
//
//    @Column(name="rental_rate_per_day")
//    private Integer chargePerDay;
//
//    @Column(name="car_agency_id ")
//    private Integer agencyId;
//
//    @Column(name="")
//    private City city;
//
//    @Column(name="image")
//    private String image;
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public City getCity() {
//        return city;
//    }
//
//    public void setCity(City city) {
//        this.city = city;
//    }
//
//    public Integer getCarId() {
//        return carId;
//    }
//
//    public void setCarId(Integer carId) {
//        this.carId = carId;
//    }
//
//    public Integer getAgencyId() {
//        return agencyId;
//    }
//
//    public void setAgencyId(Integer agencyId) {
//        this.agencyId = agencyId;
//    }
//
//    public String getFuelType() {
//        return fuelType;
//    }
//
//    public void setFuelType(String fuelType) {
//        this.fuelType = fuelType;
//    }
//
//    public String getTransmissionType() {
//        return transmissionType;
//    }
//
//    public void setTransmissionType(String transmissionType) {
//        this.transmissionType = transmissionType;
//    }
//
//
//
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getRegistrationNumber() {
//        return registrationNumber;
//    }
//
//    public void setRegistrationNumber(Integer registrationNumber) {
//        this.registrationNumber = registrationNumber;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public Integer getModel() {
//        return model;
//    }
//
//    public void setModel(Integer model) {
//        this.model = model;
//    }
//
//    public Integer getInsurancePolicyNumber() {
//        return insurancePolicyNumber;
//    }
//
//    public void setInsurancePolicyNumber(Integer insurancePolicyNumber) {
//        this.insurancePolicyNumber = insurancePolicyNumber;
//    }
//
//    public Integer getNoOfSeats() {
//        return noOfSeats;
//    }
//
//    public void setNoOfSeats(Integer noOfSeats) {
//        this.noOfSeats = noOfSeats;
//    }
//
//    public Integer getKmTravelled() {
//        return kmTravelled;
//    }
//
//    public void setKmTravelled(Integer kmTravelled) {
//        this.kmTravelled = kmTravelled;
//    }
//
//    public Integer getChargePerDay() {
//        return chargePerDay;
//    }
//
//    public void setChargePerDay(Integer chargePerDay) {
//        this.chargePerDay = chargePerDay;
//    }
//
//    @Override
//    public int compareTo(CarDetails o) {
//        return getName().compareTo(o.getName());
//    }
//}
//
