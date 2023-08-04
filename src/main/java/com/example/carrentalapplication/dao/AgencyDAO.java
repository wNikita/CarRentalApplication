package com.example.carrentalapplication.dao;

import com.example.carrentalapplication.dto.AddressDetailsDTO;
import com.example.carrentalapplication.dto.AgencyDetailsDTO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.AddressDetailsEntity;
import com.example.carrentalapplication.jpamodel.AgencyDetailsEntity;
import com.example.carrentalapplication.jpamodel.UserEntity;
import com.example.carrentalapplication.model.AddressDetails;
import com.example.carrentalapplication.model.AgencyDetails;
import com.example.carrentalapplication.model.City;
import com.example.carrentalapplication.model.State;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AgencyDAO {

    public void registerAgency(AgencyDetailsEntity agencyDetailsEntity) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(agencyDetailsEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }

    public List<AgencyDetailsEntity> viewAgencyByUserId(int userId) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("Select s from AgencyDetailsEntity s where s.user.userId=:user_Id");
            query.setParameter("user_Id", userId);
            List<AgencyDetailsEntity> list = query.getResultList();
            for (AgencyDetailsEntity agencyDetailsEntity:list)
            {
                System.out.println(agencyDetailsEntity.getAddressDetailsEntity().getAddressLine());
                System.out.println(agencyDetailsEntity.getUser().getFirstName());
            }
            em.getTransaction().commit();
            return list;
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }

    public AgencyDetailsEntity userAgencyProfile(AgencyDetailsEntity agencyDetailsEntity) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            AgencyDetailsEntity agencyDetails = em.find(agencyDetailsEntity.getClass(), agencyDetailsEntity.getAgencyDetailsId());
            if (agencyDetails != null) {
                agencyDetails.setAgencyName(agencyDetailsEntity.getAgencyName());
                agencyDetails.setGSTNumber(agencyDetailsEntity.getGSTNumber());
                agencyDetails.setMobileNumber(agencyDetailsEntity.getMobileNumber());
                em.merge(agencyDetails);
            }
            em.getTransaction().commit();
            return agencyDetailsEntity;
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }
//    public AddressDetailsEntity updateAddress( int userId) throws DAOException {
//        try {
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
//            EntityManager em = emf.createEntityManager();
//            em.getTransaction().begin();
//            UserEntity user = em.find(userEntity.getClass(), userId);
//            if (user != null) {
//                user.setFirstName(userEntity.getFirstName());
//                user.setLastName(userEntity.getLastName());
//                user.setMobileNumber(userEntity.getMobileNumber());
//                user.setEmailId(userEntity.getEmailId());
//                user.setAddress(userEntity.getAddress());
//                em.merge(user);
//            }
//            em.getTransaction().commit();
//            return userEntity;
//        } catch (Exception ex) {
//            throw new DAOException("Exception while adding user", ex);
//        }
//    }
    public void addAgency(int UserId, AgencyDetails agencyDetails, int addressId) throws DAOException {
        try {
            String sql = "insert into agency(agency_Name,GST_Number,mobile_number,user_Id,address_id) values (?,?,?,?,?)";

            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stmt.setString(1, agencyDetails.getAgencyName());
            stmt.setString(2, agencyDetails.getGSTNumber());
            stmt.setString(3, agencyDetails.getMobileNumber());
            stmt.setInt(4, UserId);
            stmt.setInt(5, addressId);
            stmt.executeUpdate();
            System.out.println("----- Agency Register Successfully-----");
        } catch (SQLException e) {
            throw new DAOException("Error while connecting");
        }
    }

    public AgencyDetails viewAgencyDetails(int UserId) {
        AgencyDetails agencyDetails = null;
        try {
            String sql = "select * from agency where User_id=?";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stmt.setInt(1, UserId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                agencyDetails = new AgencyDetails();
                agencyDetails.setAgencyDetailsId(rs.getInt(1));
                agencyDetails.setAgencyName(rs.getString(2));
                agencyDetails.setGSTNumber(rs.getString(3));
                agencyDetails.setMobileNumber(rs.getString(4));
                agencyDetails.setUserId(rs.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agencyDetails;
    }


    public AgencyDetails getAgenciesByUserId(int userId) throws DAOException {
        AgencyDetails agency = new AgencyDetails();

        try {
            String selectQuery = "SELECT a.agency_details_id, a.agency_Name, a.GST_Number,a.mobile_number," +
                    "adr.address_id, adr.address_line,adr.pin_code, c.city_id, c.city_name, s.state_id, s.state_name " +
                    "FROM agency a " +
                    "JOIN address adr ON a.address_id = adr.address_id " +
                    "JOIN city c ON adr.city_id = c.city_id " +
                    "JOIN state s ON c.state_id = s.state_id " +
                    "WHERE a.user_Id = ?";

            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(selectQuery);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                agency.setAgencyDetailsId(rs.getInt("agency_details_id"));
                agency.setAgencyName(rs.getString("agency_Name"));
                agency.setGSTNumber(rs.getString("GST_Number"));
                agency.setMobileNumber(rs.getString("mobile_number"));
                AddressDetails addressDetails = new AddressDetails();
                addressDetails.setAddressID((rs.getInt("address_id")));
                addressDetails.setAddressLine(rs.getString("address_line"));
                addressDetails.setPinCode(rs.getString("pin_code"));
                City city = new City();
                city.setCityId(rs.getInt("city_id"));
                city.setCityName(rs.getString("city_name"));
                addressDetails.setCity(city);
                State state = new State();
                state.setStateId(rs.getInt("state_id"));
                state.setStateName(rs.getString("state_name"));
                addressDetails.setState(state);
                agency.setAddressDetails(addressDetails);

            }
        } catch (SQLException e) {
            throw new DAOException("Error occurred while retrieving agencies by user ID", e);
        }
        return agency;
    }

    public void updateAgency(AgencyDetailsDTO agencyDetailsDTO, String agencyId) throws DAOException {
        try {
            String sql = "UPDATE agency SET agency_Name=?, GST_Number=?, mobile_number=? WHERE agency_details_id=?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            // Set the updated values
            statement.setString(1, agencyDetailsDTO.getAgencyName());
            statement.setString(2, agencyDetailsDTO.getGSTNumber());
            statement.setString(3, agencyDetailsDTO.getMobileNumber());
            statement.setInt(4, Integer.parseInt(agencyId));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error occurred while retrieving agencies by user ID", e);
        }

    }

    public void updateAddress(AddressDetailsDTO addressDetailsDTO, String agencyId) throws DAOException {
        try {
            String updateQuery = "UPDATE address,agency a SET address_line = ?, pin_code =? WHERE address.address_id = a.address_id and a.agency_details_id=?;";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(updateQuery);
            stmt.setString(1, addressDetailsDTO.getAddressLine());
            stmt.setString(2, addressDetailsDTO.getPinCode());
            stmt.setInt(3, Integer.parseInt(agencyId));
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new DAOException("Error occurred while retrieving agencies by user ID", e);

        }
    }


    public void updateCity(String agencyId, String cityId) throws DAOException {

        AddressDetails addressDetails = new AddressDetails();
        try {
            String findAddressIdQuery = "SELECT address_id FROM agency WHERE agency_details_id = ?";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(findAddressIdQuery);
            stmt.setInt(1, Integer.parseInt(agencyId));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                addressDetails.setAddressID(rs.getInt("address_id"));
            }
        } catch (SQLException e) {
            throw new DAOException("Error occurred while retrieving agencies by user ID", e);

        }

        // Update the city_id in the address table
        String updateCityIdQuery = "UPDATE address SET city_id = ? WHERE address_id = ?";
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(updateCityIdQuery);
            stmt.setInt(1, Integer.parseInt(cityId));
            stmt.setInt(2, addressDetails.getAddressID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error occurred while retrieving agencies by user ID", e);

        }
    }
}





