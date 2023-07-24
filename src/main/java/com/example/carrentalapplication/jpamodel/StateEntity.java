package com.example.carrentalapplication.jpamodel;

import javax.persistence.*;

@Entity
@Table(name = "state")
public class StateEntity {
    @Id
//    @Column(name = "state_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int stateId;

    @Column(name = "state_name",nullable = false)
    private String stateName;

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
