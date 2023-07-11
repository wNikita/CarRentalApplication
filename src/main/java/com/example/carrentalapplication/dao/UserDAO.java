package com.example.carrentalapplication.dao;

import com.example.carrentalapplication.dto.UserDTO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.model.CarDetails;
import com.example.carrentalapplication.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    public UserDTO addUser(UserDTO userDTO) throws DAOException {
        try {
            String insertQuery = "insert into user(first_name,last_name,password,address,email_id,mobile_number," +
                    "verification_code,is_account_verified,role_id) " +
                    "values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, userDTO.getFirstName());
            stmt.setString(2, userDTO.getLastName());
            stmt.setString(3, userDTO.getPassword());
            stmt.setString(4, userDTO.getAddress());
            stmt.setString(5, userDTO.getEmailId());
            stmt.setString(6, userDTO.getMobileNO());
            stmt.setString(7, userDTO.getVerificationCode());
            stmt.setBoolean(8, userDTO.isVerified());
            stmt.setInt(9, Integer.parseInt(userDTO.getRoleId()));
            stmt.executeUpdate();
            // user.setUserId(stmt.getGeneratedKeys().getInt(1));
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                userDTO.setUserId(String.valueOf(rs.getInt(1)));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception while adding user", e);
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
        return userDTO;
    }

    public boolean checkEmailExists(String email) throws DAOException {
        try {
            String query1 = "select * from user where  email_id=?";
            PreparedStatement stmt3 = DBConnection.getInstance().getConnection().prepareStatement(query1);
            stmt3.setString(1, email);
            ResultSet rs = stmt3.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DAOException("Something went wrong", e);
        }
    }

    public boolean checkMobileNumberExists(String mobilenumber) throws DAOException {
        try {
            String query1 = "select * from user where  mobile_number=?";
            PreparedStatement stmt3 = DBConnection.getInstance().getConnection().prepareStatement(query1);
            stmt3.setString(1, mobilenumber);
            ResultSet rs = stmt3.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DAOException("Something went wrong");
        }
    }

    public User getUserByCredentials(String userName, String password) throws DAOException {
        User user = null;
        try {
            String query = "select * from user u join role r on u.role_id=r.role_id where u.email_id=? AND u.password = ?";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query);
            stmt.setString(1, userName);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setAddress(rs.getString(5));
                user.setEmailId(rs.getString(6));
                user.setMobileNO(rs.getString(7));
                user.setVerificationCode(rs.getString(8));
                user.setVerified(rs.getBoolean(9));
                user.setRoleId(rs.getInt(10));
                user.setRoleName(rs.getString("role_name"));
                user.setLogged(rs.getBoolean(11));
            }
            return user;
        } catch (SQLException ex) {
            throw new DAOException("Something went wrong", ex);
        }
    }

    public void updateUserIsVerified(boolean isVerified, String email) throws DAOException {
        try {
            String update = "update user set is_account_verified=? where  email_id=?";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(update);
            stmt.setBoolean(1, isVerified);
            stmt.setString(2, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error while connecting");
        }
    }

    public void manageLoginStatus(String username, Boolean is_logged) {
        try {
            String updateQuery = "UPDATE user SET is_logged=? WHERE email_id = ?";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(updateQuery);
            stmt.setBoolean(1, is_logged);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user, int userId) throws DAOException {
        try {
            String updateQuery = "UPDATE user SET first_name=?, last_name=?, address=?, mobile_number=?, password=?, email_id=? WHERE user_id=?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(updateQuery);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getMobileNO());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getEmailId());
            statement.setInt(7, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Something went wrong");
        }
    }

    public User getUserById(int UserId) throws DAOException {
        User user = null;
        try {
            String query = "select * from user where user_id=?";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query);
             stmt.setInt(1, UserId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setAddress(rs.getString(5));
                user.setEmailId(rs.getString(6));
                user.setMobileNO(rs.getString(7));
                user.setVerificationCode(rs.getString(8));
                user.setVerified(rs.getBoolean(9));
                user.setLogged(rs.getBoolean(11));
            }
            return user;
        } catch (SQLException ex) {
            throw new DAOException("Something went wrong", ex);
        }
    }

    public void updateVerificationCode(String verificationCode, int userId) {
        try {
            String sql = "UPDATE user SET verification_code = ? WHERE user_id = ?";
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stmt.setString(1, verificationCode);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//public User updateVerificationCode(String verificationCode, int userId) {
//    User user = null;
//    try {
//        String sql = "UPDATE user SET verification_code = ? WHERE user_id = ?";
//        PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        stmt.setString(1, verificationCode);
//        stmt.setInt(2, userId);
//        int rowsAffected = stmt.executeUpdate();
//
//        if (rowsAffected > 0)
//            user.setVerificationCode(verificationCode);
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//
//    return user;
//}


}


