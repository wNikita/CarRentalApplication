package com.example.carrentalapplication.jpamodel;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="")

public class AddressDetails {
    @Column(name="address_id ")
    private int addressID;

    @Column(name="address_line")
    private String addressLine;

    @Column(name="pin_code")
    private String pinCode;

    @Column(name="")
    private State state;

@Column(name="")
    private City city;

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }


}
