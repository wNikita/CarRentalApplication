package com.example.carrentalapplication.Validation;

public class Validation {
    public static boolean isEmpty(String val) {
        if (val == null || val.trim().isEmpty()) {
            return true;
        }
        return false;
    }
}

