package com.example.carrentalapplication.jpamodel;

import com.example.carrentalapplication.jpamodel.AddressDetails;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="")

public class AgencyDetails {
    @Column(name="agency_Name")
    private String agencyName;

    @Column(name="GST_Number")
    private String GSTNumber;

    @Column(name="mobile_number")
    private  String mobileNumber;

    @Column(name="")
    private int agencyDetailsId;

    @Column(name="")
    private int userId;

    @Column(name="")
    private AddressDetails addressDetails;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public AddressDetails getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(AddressDetails addressDetails) {
        this.addressDetails = addressDetails;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAgencyDetailsId() {
        return agencyDetailsId;
    }

    public void setAgencyDetailsId(int agencyDetailsId) {
        this.agencyDetailsId = agencyDetailsId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getGSTNumber() {
        return GSTNumber;
    }

    public void setGSTNumber(String GSTNumber) {
        this.GSTNumber = GSTNumber;
    }
}
