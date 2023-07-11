package com.example.carrentalapplication.model;

public class State {
 private int stateId;
 private String stateName;

    public State() {
    }

    public State(int stateId, String stateName) {
        this.stateId = stateId;
        this.stateName = stateName;
    }

    public State(int stateID) {
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
