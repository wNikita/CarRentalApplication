package com.example.carrentalapplication.dto;

import com.example.carrentalapplication.model.City;
import com.example.carrentalapplication.model.State;

public class AddressDetailsDTO {

    private String addressID;
    private String addressLine;
    private String pinCode;
    private StateDTO state;
    private CityDTO city;

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public StateDTO getState() {
        return state;
    }

    public void setState(StateDTO state) {
        this.state = state;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }


}
