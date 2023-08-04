package com.example.carrentalapplication.jpamodel;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id ")
    private int addressID;

    @Column(name = "address_line", length = 30, nullable = false)
    private String addressLine;

    @Column(name = "pin_code", length = 7, nullable = false)
    private String pinCode;

//    @Column(name = "state_id ")

    @ManyToOne
    @JoinColumn(name = "city_id ")
    private CityEntity cityId;

//    @OneToOne
//    @JoinColumn(name = "city_id")
//    private CityEntity cityEntity;

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public CityEntity getCityId() {
        return cityId;
    }

    public void setCityId(CityEntity cityId) {
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
