package com.example.carrentalapplication.jpamodel;

import javax.persistence.*;

@Entity
@Table(name = "role")

public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int roleId;

    @Column(name = "role_name",nullable = false)
    String roleName;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}
