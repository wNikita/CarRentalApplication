package com.example.carrentalapplication.jpamodel;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="")

public class Role {
    @Column(name="")

    int roleId;
    @Column(name="")

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
