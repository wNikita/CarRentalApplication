package com.example.carrentalapplication.dao;


import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.CityEntity;
import com.example.carrentalapplication.jpamodel.StateEntity;
import com.example.carrentalapplication.jpamodel.UserEntity;
import com.example.carrentalapplication.model.AddressDetails;
import com.example.carrentalapplication.model.City;
import com.example.carrentalapplication.model.State;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {
    public List<StateEntity> getAllState() throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("Select s from StateEntity s");
            List<StateEntity> list = query.getResultList();
            em.getTransaction().commit();
            return list;
        } catch (Exception e) {
            throw new DAOException("Something went wrong", e);
        }
    }

//    public List<CityEntity> getAllCityByState(int stateId) throws DAOException {
//        try {
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
//            EntityManager em = emf.createEntityManager();
//            em.getTransaction().begin();
//            Query query = em.createQuery("Select s from CityEntity s where s.");
//            query.setParameter("stateId", stateId);
//            List<CityEntity> list = query.getResultList();
//            em.getTransaction().commit();
//            return list;
//        } catch (Exception e) {
//            throw new DAOException("Something went wrong", e);
//        }
//
//
//    }

    public List<State> getState() throws DAOException {
        try {
            List<State> stateList = new ArrayList<>();
            String query = "select state_id,state_name from state";
            Statement stmt = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                State state = new State();
                state.setStateId(rs.getInt(1));
                state.setStateName(rs.getString(2));
                stateList.add(state);
            }
            return stateList;
        } catch (SQLException e) {
            throw new DAOException("Something went wrong", e);
        }
    }

    public List<City> getCityByState(int stateId) throws DAOException {
        try {
            List<City> cityList = new ArrayList<>();
            String selectQuery = "select city_id,city_name from city where state_id=?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(selectQuery);
            statement.setInt(1, stateId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                City city = new City();
                city.setCityId(rs.getInt(1));
                city.setCityName(rs.getString(2));
                cityList.add(city);
            }
            return cityList;
        } catch (
                SQLException e) {
            throw new DAOException("Something went wrong", e);
        }

    }

    public AddressDetails addAddress(AddressDetails addressDetails, int cityId) throws DAOException {
        try {

            String insertQuery = "insert into address(address_line,pin_code,city_id) values(?,?,?)";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(insertQuery);
            statement.setString(1, addressDetails.getAddressLine());
            statement.setString(2, addressDetails.getPinCode());
            statement.setInt(3, cityId);
            statement.executeUpdate();
            return addressDetails;
        } catch (SQLException e) {
            throw new DAOException("Something went wrong", e);
        }
    }

    public AddressDetails getAddress() throws DAOException {
        AddressDetails addressDetails = new AddressDetails();
        try {
            String selectQuery = "select * from address";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(selectQuery);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                addressDetails.setAddressID(rs.getInt(1));
            }
            return addressDetails;
        } catch (SQLException e) {
            throw new DAOException("Something went wrong");
        }
    }

    public AddressDetails getAddress(int addressId) throws DAOException {
        try {

            AddressDetails addressDetails = new AddressDetails();
            String selectQuery = "SELECT a.address_id, a.address_line,a.pin_code, c.city_id,c.city_name,s.state_id, s.state_name " +
                    "FROM address a JOIN city c ON a.city_id = c.city_id JOIN state s " +
                    "ON c.state_id = s.state_id WHERE address_id=?;";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(selectQuery);
            statement.setInt(1, addressId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                addressDetails.setAddressID(rs.getInt(1));
                addressDetails.setAddressLine(rs.getString(2));
                addressDetails.setPinCode(rs.getString(3));
                City city = new City(rs.getInt(6), rs.getString(7));
                addressDetails.setCity(city);
                State state = new State(rs.getInt(4), rs.getString(5));
                addressDetails.setState(state);
            }
            return addressDetails;
        } catch (SQLException e) {
            throw new DAOException("Something went wrong", e);
        }
    }

}
