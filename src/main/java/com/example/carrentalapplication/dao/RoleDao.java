package com.example.carrentalapplication.dao;

import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.RoleEntity;
import com.example.carrentalapplication.jpamodel.UserEntity;
import com.example.carrentalapplication.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao {

    public List<Role> getRole() throws DAOException {
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

    public List<RoleEntity> getRoleData() throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("Select s from RoleEntity s");
            List<RoleEntity> list = query.getResultList();
            em.getTransaction().commit();
            return list;
        } catch (Exception ex) {
            throw new DAOException("Exception while adding user", ex);
        }
    }
}