package com.example.carrentalapplication.jpamodel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "state")
public class StateEntity {
    @Id
    @Column(name = "state_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stateId;

    @Column(name = "state_name", nullable = false, length = 20)
    private String stateName;

    @OneToMany(mappedBy = "stateEntity")
    private List<CityEntity> cityEntities;

    public List<CityEntity> getCityEntities() {
        return cityEntities;
    }

    public void setCityEntities(List<CityEntity> cityEntities) {
        this.cityEntities = cityEntities;
    }

    public StateEntity() {
    }

    public StateEntity(int stateId, String stateName) {
        this.stateId = stateId;
        this.stateName = stateName;
    }

    public StateEntity(int stateID) {
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }


}
