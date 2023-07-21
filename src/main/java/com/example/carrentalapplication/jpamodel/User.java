package com.example.carrentalapplication.jpamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="password")
    private String password;

    @Column(name="address")
    private String address;

    @Column(name="email_id")
    private String emailId;

    @Column(name="mobile_number")
    private String MobileNO;

    @Column(name="role_id")
    private int RoleId;

    @Column(name="role_name")
    private String roleName;

    @Column(name="verification_code")
    private String verificationCode;

    @Column(name="is_account_verified")
    private boolean isVerified;

    @Column(name="user_Id")
    private int userId;

    @Column(name="is_logged")
    private boolean isLogged;


    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public boolean setVerified(boolean verified) {
        return isVerified = verified;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public String setEmailId(String emailId) {
        return   this.emailId = emailId;
    }

    public String getMobileNO() {
        return MobileNO;
    }

    public void setMobileNO(String mobileNO) {
        MobileNO = mobileNO;
    }


}
