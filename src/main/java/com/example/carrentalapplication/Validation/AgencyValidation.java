package com.example.carrentalapplication.Validation;

import com.example.carrentalapplication.dto.AgencyDetailsDTO;

import java.util.ArrayList;
import java.util.List;

public class AgencyValidation {

    public static List<Error> AgencyValidate(AgencyDetailsDTO agencyDetailsDTO) {
        List<Error> errorList = new ArrayList<>();
        if (Validation.isEmpty(agencyDetailsDTO.getAgencyName())) {
            errorList.add(new Error("Please enter agency name"));
        }
        if (Validation.isEmpty(agencyDetailsDTO.getMobileNumber())) {
            errorList.add(new Error("please enter mobile number"));
        }
        if (Validation.isEmpty(agencyDetailsDTO.getAddressDetailsDTO().getAddressLine())) {
            errorList.add(new Error("please enter address"));
        }
        if(Validation.isEmpty(agencyDetailsDTO.getAddressDetailsDTO().getPinCode()))
        {
            errorList.add(new Error("please enter pin code"));
        }
        return errorList;
    }
}
