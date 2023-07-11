package com.example.carrentalapplication.dto;

import com.example.carrentalapplication.model.AddressDetails;

public class AgencyDetailsDTO {

    private String agencyName;
    private String GSTNumber;
    private  String mobileNumber;
    private String agencyDetailsId;
    private String userId;
    private AddressDetailsDTO addressDetailsDTO;


    public AddressDetailsDTO getAddressDetailsDTO() {
        return addressDetailsDTO;
    }

    public void setAddressDetailsDTO(AddressDetailsDTO addressDetailsDTO) {
        this.addressDetailsDTO = addressDetailsDTO;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAgencyDetailsId() {
        return agencyDetailsId;
    }

    public void setAgencyDetailsId(String agencyDetailsId) {
        this.agencyDetailsId = agencyDetailsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
