package com.example.carrentalapplication.jpamodel;

import javax.persistence.*;
@Entity
@Table(name = "address")
public class AddressDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressID;

    @Column(name = "address_line", length = 30, nullable = false)
    private String addressLine;

    @Column(name = "pin_code", length = 7, nullable = false)
    private String pinCode;

//    @Column(name = "state_id ")


    @Column(name = "city_id ")
    private int cityId;

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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
