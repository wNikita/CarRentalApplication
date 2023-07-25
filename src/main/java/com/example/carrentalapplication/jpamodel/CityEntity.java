package com.example.carrentalapplication.jpamodel;


import javax.persistence.*;

@Entity
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cityId;

    @Column(name = "city_name",nullable = false,length = 20)
    private String cityName;

    public CityEntity() {
    }

    public CityEntity(int cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public CityEntity(int cityID) {
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
