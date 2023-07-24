package com.example.carrentalapplication.jpamodel;


import javax.persistence.*;

@Entity
@Table(name="user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private int userId;

    @Column(name = "first_name",length = 20,nullable = false)
    private String firstName;

    @Column(name = "last_name",length = 20,nullable = false)
    private String lastName;

    @Column(name = "password",length = 15,nullable = false)
    private String password;

    @Column(name = "address",length = 20,nullable = false)
    private String address;

    @Column(name = "email_id",length = 20,nullable = false)
    private String emailId;

    @Column(name = "mobile_number", length = 20, nullable = true)
    private String mobileNumber;

    @Column(name = "role_id",length = 20,nullable = false)
    private int RoleId;



    @Column(name = "verification_code",nullable = false)
    private String verificationCode;

    @Column(name = "is_account_verified",nullable = false)
    private boolean isVerified;


    @Column(name = "is_logged",nullable = false)
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
        return this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
