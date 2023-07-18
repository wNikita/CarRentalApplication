package com.example.carrentalapplication.Validation;

import com.example.carrentalapplication.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserValidation {

    public static List<Error> UserValidate(UserDTO userDTO) {
        List<Error> errorList = new ArrayList<>();
        if (Validation.isEmpty(userDTO.getEmailId())) {
            errorList.add(new Error("Please enter email"));
        }
        if (Validation.isEmpty(userDTO.getPassword())) {
            errorList.add(new Error("Please enter password"));
        }
        return errorList;
    }

    public static List<Error> validateUser(UserDTO userDTO) {
        List<Error> errorList = new ArrayList<>();
        if (Validation.isEmpty(userDTO.getFirstName())) {
            errorList.add(new Error("Please enter First name"));
        }
        if (Validation.isEmpty(userDTO.getLastName())) {
            errorList.add(new Error("Please enter Last name"));
        }
        if (Validation.isEmpty(userDTO.getMobileNO())) {
            errorList.add(new Error("Please enter mobile number"));
        }
        if(Validation.isEmpty(userDTO.getPassword()))
        {
            errorList.add(new Error("Please enter password"));
        }
        if(Validation.isEmpty(userDTO.getAddress()))
        {
            errorList.add(new Error("Please enter address"));
        }
        if(Validation.isEmpty(String.valueOf(userDTO.getRoleId())))
        {
            errorList.add(new Error("Please enter role name"));
        }
        return errorList;
    }
    public static  List<Error> emailValidate(UserDTO userDTO)
    {
        List<Error> errorList = new ArrayList<>();
         {
            errorList.add(new Error("Email is already exist"));
        } return errorList;
    }
}
