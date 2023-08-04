package com.example.carrentalapplication.jpamodel;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "agency")

public class AgencyDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="agency_details_id",length = 10)
    private int agencyDetailsId;

    @Column(name = "agency_Name", length = 30, nullable = false)
    private String agencyName;

    @Column(name = "GST_Number", length = 10, nullable = false)
    private String GSTNumber;

    @Column(name = "mobile_number", length = 12, nullable = false)
    private String mobileNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressDetailsEntity addressDetailsEntity;

    @OneToMany(mappedBy = "agencyDetailsEntity")
    private Set<CarDetailsEntity> carDetailsEntities = new HashSet<>();

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public AddressDetailsEntity getAddressDetailsEntity() {
        return addressDetailsEntity;
    }

    public void setAddressDetailsEntity(AddressDetailsEntity addressDetailsEntity) {
        this.addressDetailsEntity = addressDetailsEntity;
    }

    public Set<CarDetailsEntity> getCarDetailsEntities() {
        return carDetailsEntities;
    }

    public void setCarDetailsEntities(Set<CarDetailsEntity> carDetailsEntities) {
        this.carDetailsEntities = carDetailsEntities;
    }
}
