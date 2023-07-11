package com.example.carrentalapplication.model;

public class AgencyDetails {

    private String agencyName;
    private String GSTNumber;
    private  String mobileNumber;
    private int agencyDetailsId;
    private int userId;
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
