package com.example.carrentalapplication.jpamodel;


import javax.persistence.*;

@Entity
@Table(name = "agency")

public class AgencyDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int agencyDetailsId;

    @Column(name = "agency_Name", length = 30, nullable = false)
    private String agencyName;

    @Column(name = "GST_Number", length = 10, nullable = false)
    private String GSTNumber;

    @Column(name = "mobile_number", length = 12, nullable = false)
    private String mobileNumber;


    @Column(name = "user_id")
    private int userId;

    @Column(name = "address_id")
    private int addressId;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
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
