<%@ page import="com.example.carrentalapplication.model.User" %>
<%@ page import="com.example.carrentalapplication.jpamodel.UserEntity" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        .container {
            width: 400px;
            margin: 0 auto;
            margin-top: 100px;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        }

        h2 {
            text-align: center;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
        }

        .form-group input {
            width: 100%;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .form-group .btn {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            border: none;
            color: #fff;
            cursor: pointer;
            border-radius: 3px;
        }

        .form-group .btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Update User Profile</h2>
    <form action="user-update" method="POST">
        <%
            UserEntity user = (UserEntity) request.getAttribute("user");
        %>
        <div class="form-group">
            <label for="firstname">First Name:</label>
            <input type="text" id="firstname" name="firstname" value="<%= user.getFirstName()%>">
        </div>
        <div class="form-group">
            <label for="lastname">Last Name:</label>
            <input type="text" id="lastname" name="lastname" value= "<%=user.getLastName()%>">
        </div>
        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="<%= user.getAddress() %>" >
        </div>
        <div class="form-group">
            <label for="mobilenumber">Mobile Number:</label>
            <input type="tel" id="mobilenumber" name="mobilenumber" value="<%= user.getMobileNumber() %>" >
        </div>
<%--        <div class="form-group">--%>
<%--            <label for="password">Password:</label>--%>
<%--            <input type="password" id="password" name="password" value="${password}" required>--%>
<%--        </div>--%>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= user.getEmailId() %>" >
        </div>
        <div class="form-group">
            <input type="submit" value="Update" class="btn">
        </div>
    </form>
</div>
