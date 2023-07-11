package com.example.carrentalapplication.dao;

import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.model.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao {

    public List<Role> getRole()  {
        List<Role> role = new ArrayList<>();
        try {
            String query = "select role_id,role_name from role";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Role role1=new Role();
                role1.setRoleId(resultSet.getInt(1));
                role1.setRoleName(resultSet.getString(2));
                role.add(role1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}