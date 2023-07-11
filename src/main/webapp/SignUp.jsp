<%@ page import="java.util.List" %>
<%@ page import="com.example.carrentalapplication.model.Role" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
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
    <h2>Sign Up</h2>
    <div class="errorList">
        <%
            List<Error> errorList = (List<Error>) request.getAttribute("errorList");
            if (errorList != null && !errorList.isEmpty()) {
        %>
        <ul>
            <% for (Error error : errorList) { %>
            <div>   <li><%= error.getMessage() %></li>
            </div>            <% } %>
        </ul>
        <% } %>
    </div>
    <form action="registration-form" method="POST">
        <div class="form-group">
            <label for="role">Select Role:</label>

            <select name="role" id="role">
                <%
                    List<Role> rolesList = (List<Role>) request.getAttribute("Role");
                    for (Role role : rolesList) {
                %>
                <option value="none" selected disabled hidden>Select Role</option>
                <option value="<%= role.getRoleId() %>"><%= role.getRoleName() %></option>
                <% } %>
            </select>
            <br><br>

        </div>
        <div class="form-group">
            <label for="firstname">First Name:</label>
            <input type="text" id="firstname" name="firstname" >
        </div>
        <div class="form-group">
            <label for="lastname">Last Name:</label>
            <input type="text" id="lastname" name="lastname" >
        </div>
        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" >
        </div>
        <div class="form-group">
            <label for="mobilenumber">Mobile Number:</label>
            <input type="tel" id="mobilenumber" name="mobilenumber" >
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" >
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" >
        </div>
        <div class="form-group">
            <input type="submit" value="Sign Up" class="btn">
        </div>
    </form>
</div>
</body>
</html>
