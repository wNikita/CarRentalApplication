package com.example.carrentalapplication.validation;

import com.example.carrentalapplication.dto.CarDetailsDTO;

import java.util.ArrayList;
import java.util.List;

public class CarDetailsValidation {

        public static List<Error> validateCarDetails(CarDetailsDTO carDetailsDTO) {
            List<Error> errorList = new ArrayList<>();

            if (Validation.isEmpty(carDetailsDTO.getName())) {
                errorList.add(new Error("Please enter car name"));
            }

            if (Validation.isEmpty(carDetailsDTO.getRegistrationNumber())) {
                errorList.add(new Error("Invalid registration number"));
            }

            if (Validation.isEmpty(carDetailsDTO.getChargePerDay() )) {
                errorList.add(new Error("Invalid charge per day"));
            }

            if (Validation.isEmpty(carDetailsDTO.getFuelType())) {
                errorList.add(new Error("Please select fuel type"));
            }

            if (Validation.isEmpty(carDetailsDTO.getInsurancePolicyNumber())) {
                errorList.add(new Error("Invalid insurance policy number"));
            }

            if (Validation.isEmpty(carDetailsDTO.getKmTravelled())) {
                errorList.add(new Error("Invalid kilometers travelled"));
            }

            if (Validation.isEmpty(carDetailsDTO.getNoOfSeats())) {
                errorList.add(new Error("Invalid number of seats"));
            }

            if (Validation.isEmpty(carDetailsDTO.getModel())) {
                errorList.add(new Error("Invalid car model number"));
            }

            if (Validation.isEmpty(carDetailsDTO.getTransmissionType())) {
                errorList.add(new Error("Please select transmission type"));
            }

            if (Validation.isEmpty(carDetailsDTO.getColor())) {
                errorList.add(new Error("Please enter car color"));
            }

            if (Validation.isEmpty(carDetailsDTO.getImage())) {
                errorList.add(new Error("Please upload car image"));
            }

            return errorList;
        }
    }


