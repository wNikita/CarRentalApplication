package com.example.carrentalapplication.common;

import com.example.carrentalapplication.dao.AgencyDAO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.model.AgencyDetails;
import com.example.carrentalapplication.model.User;


public class Session {


    private static User currentUser = null;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Session.currentUser = currentUser;
    }

    public static boolean isAgencyAdmin() {
        return currentUser.getRoleId() == Constant.ROLE_CAR_AGENCY;
    }

    public static int getAgencyId() throws DAOException {
        if (!isAgencyAdmin()) {
            throw new DAOException("Something went wrong");
        }
        AgencyDAO agencyDAO = new AgencyDAO();
        AgencyDetails agencyDetails = agencyDAO.viewAgencyDetails(Session.currentUser.getUserId());
        //DAO CALL to get agencyId
        return agencyDetails.getAgencyDetailsId();
    }
}


