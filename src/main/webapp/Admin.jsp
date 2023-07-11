
<%@ page import="com.example.carrentalapplication.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Panel</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-image: url("car_uploads/carhome.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            font-family: Arial, sans-serif;
        }

        .navbar {
            background-color: #333;
            overflow: hidden;
            position: fixed;
            top: 0;
            width: 100%;
        }

        .navbar a {
            float: left;
            display: block;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }


        .navbar .button {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin-left: 10px;
            margin-top: 5px;
            border-radius: 5px;
        }

        .navbar .button:hover {
            background-color: #45a049;
        }

        .navbar .right {
            float: right;
        }

        h1 {
            text-align: center;
            margin-bottom: 50px;
            margin-top: 50px;
        }

    </style>
</head>
<body>
<div class="navbar">
    <a href="register-car">Add Car</a>
    <a href="view-car">View Car</a>
    <a href="view-agency">View Agency Profile</a>
    <a href="AdminViewBooking.jsp" >My Booking</a>
    <a href="user-profile">My Account</a>

    <a class="button right" href="logout">Logout</a>
</div>
</body>
</html>

<body>
<div class="panel">
    <h1>Welcome to Car Rental System, <%= ((User) session.getAttribute("CurrentUser")).getFirstName() %>!</h1>
</div>
</body>
</html>
