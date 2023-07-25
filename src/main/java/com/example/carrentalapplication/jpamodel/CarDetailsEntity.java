package com.example.carrentalapplication.jpamodel;

import javax.persistence.*;

@Entity
@Table(name = "car")

public class CarDetailsEntity implements Comparable<CarDetailsEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;

    @Column(name = "name",length = 10,nullable = false)
    private String name;

    @Column(name = "number",length = 10,nullable = false)
    private Integer registrationNumber;

    @Column(name = "color",length = 10,nullable = false)
    private String color;

    @Column(name = "model",length = 15,nullable = false)
    private Integer model;

    @Column(name = "fuel_type",length = 20,nullable = false)
    private String fuelType;

    @Column(name = "transmission_type",length = 20,nullable = false)
    private String transmissionType;

    @Column(name = "insurance_no",length = 25,nullable = false)
    private Integer insurancePolicyNumber;

    @Column(name = "no_of_seats",length = 10,nullable = false)
    private Integer noOfSeats;

    @Column(name = "km_travelled",length = 20,nullable = false)
    private Integer kmTravelled;

    @Column(name = "rental_rate_per_day",length = 10,nullable = false)
    private Integer chargePerDay;

    @Column(name = "car_agency_id ",length = 20,nullable = false)
    private Integer agencyId;



    @Column(name = "image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public Integer getInsurancePolicyNumber() {
        return insurancePolicyNumber;
    }

    public void setInsurancePolicyNumber(Integer insurancePolicyNumber) {
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public Integer getKmTravelled() {
        return kmTravelled;
    }

    public void setKmTravelled(Integer kmTravelled) {
        this.kmTravelled = kmTravelled;
    }

    public Integer getChargePerDay() {
        return chargePerDay;
    }

    public void setChargePerDay(Integer chargePerDay) {
        this.chargePerDay = chargePerDay;
    }

    @Override
    public int compareTo(CarDetailsEntity o) {
        return getName().compareTo(o.getName());
    }
}

