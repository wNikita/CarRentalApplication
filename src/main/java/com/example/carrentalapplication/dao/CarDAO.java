package com.example.carrentalapplication.dao;

import com.example.carrentalapplication.dto.CarDetailsDTO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.CarDetailsEntity;
import com.example.carrentalapplication.jpamodel.UserEntity;
import com.example.carrentalapplication.model.CarDetails;
import com.example.carrentalapplication.model.City;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    public CarDetailsEntity addCar(CarDetailsEntity carDetailsEntity) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(carDetailsEntity);
            em.getTransaction().commit();
            return carDetailsEntity;
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }

    public List<CarDetailsEntity> viewAllCarByAgency(int AgencyId) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("Select s from CarDetailsEntity s where s.agencyDetailsEntity.agencyDetailsId=:AgencyId");
            query.setParameter("AgencyId", AgencyId);
            List<CarDetailsEntity> list = query.getResultList();
            em.getTransaction().commit();
            return list;
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }
    public List<CarDetailsEntity> viewAllCarByCarId(String CarId) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("Select s from CarDetailsEntity s where s.carId=:CarId");
            query.setParameter("CarId", CarId);
            List<CarDetailsEntity> list = query.getResultList();
            em.getTransaction().commit();
            return list;
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }

    public List<CarDetailsEntity> carNameByAscending(int agencyID ) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("Select s from CarDetailsEntity s where s.agencyDetailsEntity.agencyDetailsId=:agencyId order by s.name asc ");
            query.setParameter("agencyId", agencyID);
            List<CarDetailsEntity> list = query.getResultList();
            return list;
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }

    public List<CarDetailsEntity> carNameByDesc(int agencyID ) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("Select s from CarDetailsEntity s where s.agencyDetailsEntity.agencyDetailsId=:agencyId order by s.name desc ");
            query.setParameter("agencyId", agencyID);
            List<CarDetailsEntity> list = query.getResultList();return list;
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }
    public List<CarDetailsEntity> carRatePerDayByAsc(int agencyID ) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("Select s from CarDetailsEntity s where s.agencyDetailsEntity.agencyDetailsId=:agencyId order by s.chargePerDay asc ");
            query.setParameter("agencyId", agencyID);
            List<CarDetailsEntity> list = query.getResultList();
            return list;
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }
    public List<CarDetailsEntity> carRatePerDayByDesc(int agencyID ) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("Select s from CarDetailsEntity s where s.agencyDetailsEntity.agencyDetailsId=:agencyId order by s.chargePerDay desc ");
            query.setParameter("agencyId", agencyID);
            List<CarDetailsEntity> list = query.getResultList();
            return list;
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }
    public List<CarDetailsEntity> viewCarByCity(int cityId) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("Select s from CarDetailsEntity s where s.agencyDetailsEntity.addressDetailsEntity.cityId.cityId=:cityId");
            query.setParameter("cityId", cityId);
            List<CarDetailsEntity> list = query.getResultList();
            em.getTransaction().commit();
            return list;
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }

    public List<CarDetailsEntity> viewCarByFuelType(String fuelType,int agencyId) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("Select s from CarDetailsEntity s where s.agencyDetailsEntity.agencyDetailsId=:agencyId and s.fuelType=:fuelType");
            query.setParameter("agencyId", agencyId);
            query.setParameter("fuelType",fuelType);
            List<CarDetailsEntity> list = query.getResultList();
            em.getTransaction().commit();
            return list;
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }

    public List<CarDetailsEntity> viewCarByFuelTypeAndTransmissionType(String fuelType,String transmissionType,int agencyId) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("Select s from CarDetailsEntity s where s.agencyDetailsEntity.agencyDetailsId=:agencyId and s.fuelType=:fuelType and s.transmissionType=:transmissionType");
            query.setParameter("agencyId", agencyId);
            query.setParameter("fuelType",fuelType);
            query.setParameter("transmissionType",transmissionType);
            List<CarDetailsEntity> list = query.getResultList();
            em.getTransaction().commit();
            return list;
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }
    public CarDetailsEntity carUpdate(CarDetailsDTO carDetailsDTO, String carId) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            CarDetailsEntity carDetailsEntity1 = em.find(CarDetailsEntity.class, carId);
            if (carDetailsEntity1 != null) {
                carDetailsEntity1.setName(carDetailsDTO.getName());
                carDetailsEntity1.setRegistrationNumber(Integer.valueOf(carDetailsDTO.getRegistrationNumber()));
                carDetailsEntity1.setChargePerDay(Integer.valueOf(carDetailsDTO.getChargePerDay()));
                carDetailsEntity1.setFuelType(carDetailsDTO.getFuelType());
                carDetailsEntity1.setInsurancePolicyNumber(Integer.valueOf(carDetailsDTO.getInsurancePolicyNumber()));
                carDetailsEntity1.setKmTravelled(Integer.valueOf(carDetailsDTO.getKmTravelled()));
                carDetailsEntity1.setNoOfSeats(Integer.valueOf(carDetailsDTO.getNoOfSeats()));
                carDetailsEntity1.setModel(Integer.valueOf(carDetailsDTO.getModel()));
                carDetailsEntity1.setTransmissionType(carDetailsDTO.getTransmissionType());
                carDetailsEntity1.setColor(carDetailsDTO.getColor());
                em.merge(carDetailsEntity1);
            }
            em.getTransaction().commit();
            return carDetailsEntity1;
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }
//    public void add(CarDetailsDTO carDetailsDTO) throws DAOException {
//        try {
//            String insertQuery = "insert into car(name,number,color,model,insurance_no,no_of_seats," +
//                    "km_travelled,fuel_type,car_agency_id,transmission_type,rental_rate_per_day,image )" +
//                    "values (?,?,?,?,?,?,?,?,?,?,?,?)";
//            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(insertQuery);
//            stmt.setString(1, carDetailsDTO.getName());
//            stmt.setInt(2, Integer.parseInt(carDetailsDTO.getRegistrationNumber()));
//            stmt.setString(3, carDetailsDTO.getColor());
//            stmt.setInt(4, Integer.parseInt(carDetailsDTO.getModel()));
//            stmt.setInt(5, Integer.parseInt(carDetailsDTO.getInsurancePolicyNumber()));
//            stmt.setInt(6, Integer.parseInt(carDetailsDTO.getNoOfSeats()));
//            stmt.setInt(7, Integer.parseInt(carDetailsDTO.getKmTravelled()));
//            stmt.setString(8, carDetailsDTO.getFuelType());
//            stmt.setInt(9, carDetailsDTO.getAgencyId());
//            stmt.setString(10, carDetailsDTO.getTransmissionType());
//            stmt.setInt(11, Integer.parseInt(carDetailsDTO.getChargePerDay()));
//            stmt.setString(12, carDetailsDTO.getImage());
//            stmt.executeUpdate();
//            System.out.println("-----Car register successfully-----");
//        } catch (SQLException e) {
//            throw new DAOException("Error while connecting");
//        }
//   }

    public List<CarDetails> viewAllCar(int car_agency_id) throws DAOException {
        List<CarDetails> carDetails1 = new ArrayList<>();
        try {
            String query = "select * from car where car_agency_id=?";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query);
            stmt.setInt(1, car_agency_id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                CarDetails carDetails = new CarDetails();
                carDetails.setCarId(resultSet.getInt(1));
                carDetails.setName(resultSet.getString(2));
                carDetails.setRegistrationNumber(resultSet.getInt(3));
                carDetails.setColor(resultSet.getString(4));
                carDetails.setModel(resultSet.getInt(5));
                carDetails.setInsurancePolicyNumber(resultSet.getInt(6));
                carDetails.setNoOfSeats(resultSet.getInt(7));
                carDetails.setKmTravelled(resultSet.getInt(8));
                carDetails.setFuelType(resultSet.getString(9));
                carDetails.setTransmissionType(resultSet.getString(11));
                carDetails.setChargePerDay(resultSet.getInt(12));
                carDetails.setImage(resultSet.getString(13));
                carDetails1.add(carDetails);
            }
        } catch (Exception e) {
            throw new DAOException("Error while connecting");
        }
        return carDetails1;
    }


    public List<CarDetails> getCarByCityId(int cityId) throws DAOException {
        List<CarDetails> carDetails1 = new ArrayList<>();
        try {
            String query = "SELECT *,city1.city_name FROM car AS c JOIN agency AS a ON c.car_agency_id =" +
                    "a.agency_details_id JOIN address AS ad ON a.address_id = ad.address_id JOIN city AS " +
                    "city1 ON ad.city_id=city1.city_id WHERE ad.city_id = ?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, cityId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CarDetails carDetails = new CarDetails();
                carDetails.setCarId(resultSet.getInt(1));
                carDetails.setName(resultSet.getString(2));
                carDetails.setRegistrationNumber(resultSet.getInt(3));
                carDetails.setColor(resultSet.getString(4));
                carDetails.setModel(resultSet.getInt(5));
                carDetails.setInsurancePolicyNumber(resultSet.getInt(6));
                carDetails.setNoOfSeats(resultSet.getInt(7));
                carDetails.setKmTravelled(resultSet.getInt(8));
                carDetails.setFuelType(resultSet.getString(9));
                carDetails.setTransmissionType(resultSet.getString(11));
                carDetails.setChargePerDay(resultSet.getInt(12));
                City city = new City(resultSet.getInt(28), resultSet.getString(30));
                carDetails.setCity(city);
                carDetails1.add(carDetails);
            }
        } catch (SQLException e) {
            throw new DAOException("Something went wrong");
        }
        return carDetails1;
    }

    public CarDetails getAllCarDetails(String carID) throws DAOException {
        CarDetails carDetails = new CarDetails();
        try {
            String selectQuery = "SELECT * FROM car WHERE car_id=?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(selectQuery);
            statement.setInt(1, Integer.parseInt((carID)));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                carDetails.setCarId(resultSet.getInt(1));
                carDetails.setName(resultSet.getString(2));
                carDetails.setRegistrationNumber(resultSet.getInt(3));
                carDetails.setColor(resultSet.getString(4));
                carDetails.setModel(resultSet.getInt(5)); // Corrected column index
                carDetails.setInsurancePolicyNumber(resultSet.getInt(6));
                carDetails.setNoOfSeats(resultSet.getInt(7));
                carDetails.setKmTravelled(resultSet.getInt(8));
                carDetails.setFuelType(resultSet.getString(9));
                carDetails.setTransmissionType(resultSet.getString(11));
                carDetails.setChargePerDay(resultSet.getInt(12));
                carDetails.setImage(resultSet.getString(13));
            }
        } catch (SQLException e) {
            throw new DAOException("Something went wrong");
        }
        return carDetails;
    }


    public void UpdateCar(String carId, CarDetailsDTO carDetailsDTO) throws DAOException {
        try {
            String selectQuery = "UPDATE car SET name=?, number=?, color=?, model=?, insurance_no=?, no_of_seats=?, km_travelled=?, fuel_type=?, transmission_type=?, rental_rate_per_day=? ,image=? WHERE car_id=?";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(selectQuery);

            stmt.setString(1, carDetailsDTO.getName());
            stmt.setInt(2, Integer.parseInt(carDetailsDTO.getRegistrationNumber()));
            stmt.setString(3, carDetailsDTO.getColor());
            stmt.setInt(4, Integer.parseInt(carDetailsDTO.getModel()));
            stmt.setInt(5, Integer.parseInt(carDetailsDTO.getInsurancePolicyNumber()));
            stmt.setInt(6, Integer.parseInt(carDetailsDTO.getNoOfSeats()));
            stmt.setInt(7, Integer.parseInt(carDetailsDTO.getKmTravelled()));
            stmt.setString(8, carDetailsDTO.getFuelType());
            stmt.setString(9, carDetailsDTO.getTransmissionType());
            stmt.setInt(10, Integer.parseInt(carDetailsDTO.getChargePerDay()));
            stmt.setString(11,carDetailsDTO.getImage());
            stmt.setInt(12, Integer.parseInt(carId));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Something went wrong");

        }

    }

    public void deleteCar(int carId) throws DAOException {
        try {
            String deleteQuery = "DELETE FROM car WHERE car_id=?";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(deleteQuery);

            stmt.setInt(1, carId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Something went wrong", e);
        }
    }

    public List<CarDetails> getCarsSortedByNameAscending(int agencyId) throws DAOException {
        List<CarDetails> carList = new ArrayList<>();

        try {
            String query = "SELECT * FROM car WHERE car_agency_id=? ORDER BY name";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query);
            stmt.setInt(1, agencyId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                // Retrieve car details from the result set and create CarDetails objects
                CarDetails carDetails = new CarDetails();
                carDetails.setCarId(resultSet.getInt(1));
                carDetails.setName(resultSet.getString("name"));
                carDetails.setRegistrationNumber(resultSet.getInt(3));
                carDetails.setColor(resultSet.getString(4));
                carDetails.setModel(resultSet.getInt(5));
                carDetails.setInsurancePolicyNumber(resultSet.getInt(6));
                carDetails.setNoOfSeats(resultSet.getInt(7));
                carDetails.setKmTravelled(resultSet.getInt(8));
                carDetails.setFuelType(resultSet.getString(9));
                carDetails.setTransmissionType(resultSet.getString(11));
                carDetails.setChargePerDay(resultSet.getInt(12));
                carDetails.setImage(resultSet.getString(13));
                carList.add(carDetails);
            }
            // Add the car to the car list
        } catch (SQLException e) {
            throw new DAOException("Error in getCarsSortedByNameAscending", e);
        }
        return carList;
    }

    public List<CarDetails> getCarsSortedByNameDesc(int agencyId) throws DAOException {
        List<CarDetails> carList = new ArrayList<>();

        try {
            String query = "SELECT * FROM car WHERE car_agency_id=? ORDER BY name DESC";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query);
            stmt.setInt(1, agencyId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                // Retrieve car details from the result set and create CarDetails objects
                CarDetails carDetails = new CarDetails();
                carDetails.setCarId(resultSet.getInt(1));
                carDetails.setName(resultSet.getString("name"));
                carDetails.setRegistrationNumber(resultSet.getInt(3));
                carDetails.setColor(resultSet.getString(4));
                carDetails.setModel(resultSet.getInt(5));
                carDetails.setInsurancePolicyNumber(resultSet.getInt(6));
                carDetails.setNoOfSeats(resultSet.getInt(7));
                carDetails.setKmTravelled(resultSet.getInt(8));
                carDetails.setFuelType(resultSet.getString(9));
                carDetails.setTransmissionType(resultSet.getString(11));
                carDetails.setChargePerDay(resultSet.getInt(12));
                carDetails.setImage(resultSet.getString(13));

                carList.add(carDetails);
            }
            // Add the car to the car list
        } catch (SQLException e) {
            throw new DAOException("Error in getCarsSortedByNameAscending", e);
        }
        return carList;
    }

    public List<CarDetails> getCarsSortedByRentalRateDesc(int agencyId) throws DAOException {
        List<CarDetails> carList = new ArrayList<>();

        try {
            String query = "SELECT * FROM car where  car_agency_id=? ORDER BY rental_rate_per_day DESC";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query);
            stmt.setInt(1, agencyId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                // Retrieve car details from the result set and create CarDetails objects
                CarDetails carDetails = new CarDetails();
                carDetails.setCarId(resultSet.getInt(1));
                carDetails.setName(resultSet.getString("name"));
                carDetails.setRegistrationNumber(resultSet.getInt(3));
                carDetails.setColor(resultSet.getString(4));
                carDetails.setModel(resultSet.getInt(5));
                carDetails.setInsurancePolicyNumber(resultSet.getInt(6));
                carDetails.setNoOfSeats(resultSet.getInt(7));
                carDetails.setKmTravelled(resultSet.getInt(8));
                carDetails.setFuelType(resultSet.getString(9));
                carDetails.setTransmissionType(resultSet.getString(11));
                carDetails.setChargePerDay(resultSet.getInt(12));
                carDetails.setImage(resultSet.getString(13));

                carList.add(carDetails);
            }
            // Add the car to the car list
        } catch (SQLException e) {
            throw new DAOException("Error in getCarsSortedByNameAscending", e);
        }
        return carList;
    }

    public List<CarDetails> getCarsSortedByRentalRate(int agencyId) throws DAOException {

        {
            List<CarDetails> carList = new ArrayList<>();

            try {
                String query = "SELECT * FROM car where car_agency_id=? ORDER BY rental_rate_per_day ASC ";
                PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query);
                stmt.setInt(1, agencyId);
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    // Retrieve car details from the result set and create CarDetails objects
                    CarDetails carDetails = new CarDetails();
                    carDetails.setCarId(resultSet.getInt(1));
                    carDetails.setName(resultSet.getString("name"));
                    carDetails.setRegistrationNumber(resultSet.getInt(3));
                    carDetails.setColor(resultSet.getString(4));
                    carDetails.setModel(resultSet.getInt(5));
                    carDetails.setInsurancePolicyNumber(resultSet.getInt(6));
                    carDetails.setNoOfSeats(resultSet.getInt(7));
                    carDetails.setKmTravelled(resultSet.getInt(8));
                    carDetails.setFuelType(resultSet.getString(9));
                    carDetails.setTransmissionType(resultSet.getString(11));
                    carDetails.setChargePerDay(resultSet.getInt(12));
                    carDetails.setImage(resultSet.getString(13));

                    carList.add(carDetails);
                }
                // Add the car to the car list
            } catch (SQLException e) {
                throw new DAOException("Error in getCarsSortedByNameAscending", e);
            }
            return carList;
        }

    }
    public List<CarDetails> getCarsByFuelType(String fuelType,int agencyID) throws DAOException {
        List<CarDetails> filteredCars = new ArrayList<>();
        try {
            String  query="SELECT * FROM car WHERE  fuel_type = ? AND car_agency_id=?";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query);
            stmt.setString(1, fuelType);
            stmt.setInt(2, agencyID);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                CarDetails carDetails = new CarDetails();
                carDetails.setCarId(resultSet.getInt("car_id"));
                carDetails.setName(resultSet.getString("name"));
                carDetails.setRegistrationNumber(resultSet.getInt("number"));
                carDetails.setModel(resultSet.getInt("model"));
                carDetails.setChargePerDay(resultSet.getInt("rental_rate_per_day"));
                carDetails.setFuelType(resultSet.getString("fuel_type"));
                carDetails.setTransmissionType(resultSet.getString("transmission_type"));
                carDetails.setImage(resultSet.getString("image"));

                filteredCars.add(carDetails);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in getCarsByFuelTypeAndTransmission", e);
        }

        return filteredCars;
    }
    public List<CarDetails> getCarsByFuelTypeAndTransmission(String fuelType, String transmission,int agencyID) throws DAOException {
        List<CarDetails> filteredCars = new ArrayList<>();
        try {
            String  query="SELECT * FROM car WHERE  fuel_type = ? AND transmission_type = ?AND car_agency_id=?";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query);
            stmt.setString(1, fuelType);
            stmt.setString(2, transmission);
            stmt.setInt(3, agencyID);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                CarDetails carDetails = new CarDetails();
                carDetails.setCarId(resultSet.getInt("car_id"));
                carDetails.setName(resultSet.getString("name"));
                carDetails.setRegistrationNumber(resultSet.getInt("number"));
                carDetails.setModel(resultSet.getInt("model"));
                carDetails.setChargePerDay(resultSet.getInt("rental_rate_per_day"));
                carDetails.setFuelType(resultSet.getString("fuel_type"));
                carDetails.setTransmissionType(resultSet.getString("transmission_type"));
                carDetails.setImage(resultSet.getString("image"));

                filteredCars.add(carDetails);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in getCarsByFuelTypeAndTransmission", e);
        }

        return filteredCars;
    }
    public List<CarDetails> viewAllCarByCity(int cityId) throws DAOException {
        List<CarDetails> carDetails1 = new ArrayList<>();
        try {
            String query = "SELECT * FROM car\n" +
                    "JOIN agency ON car.car_agency_id = agency.agency_details_id\n" +
                    "JOIN address ON agency.address_id = address.address_id\n" +
                    "JOIN city ON address.city_id = city.city_id\n" +
                    "WHERE city.city_id= ?;";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query);
            stmt.setInt(1, cityId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                CarDetails carDetails = new CarDetails();
                carDetails.setCarId(resultSet.getInt(1));
                carDetails.setName(resultSet.getString(2));
                carDetails.setRegistrationNumber(resultSet.getInt(3));
                carDetails.setColor(resultSet.getString(4));
                carDetails.setModel(resultSet.getInt(5));
                carDetails.setInsurancePolicyNumber(resultSet.getInt(6));
                carDetails.setNoOfSeats(resultSet.getInt(7));
                carDetails.setKmTravelled(resultSet.getInt(8));
                carDetails.setFuelType(resultSet.getString(9));
                carDetails.setTransmissionType(resultSet.getString(11));
                carDetails.setChargePerDay(resultSet.getInt(12));
                carDetails.setImage(resultSet.getString(13));
                carDetails1.add(carDetails);
            }
        } catch (Exception e) {
            throw new DAOException("Error while connecting");
        }
        return carDetails1;
    }
}


