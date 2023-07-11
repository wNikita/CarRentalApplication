<%@ page import="com.example.carrentalapplication.model.User" %>
<!-- profile.jsp -->

<!DOCTYPE html>
<html>
<head>
    <title>View Agency</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 20px;
        }

        h1 {
            color: #333333;
            text-align: center;
        }

        table {
            width: 400px;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #cccccc;
        }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>Profile Details</h1>

<div class="profile-details">

    <%
        User user = (User) request.getAttribute("user");
    %>
    <p><b>First Name:</b> <%= user.getFirstName() %></p>
    <p><b>Last Name:</b> <%= user.getLastName() %></p>
    <p><b>Mobile Number:</b> <%= user.getMobileNO() %></p>
    <p><b>Address:</b> <%= user.getAddress() %></p>
    <p><b>Email:</b> <%= user.getEmailId() %></p>

    <div class="button-container">
        <form action="user-update">
<%--            <input type="hidden" name="carId" value="<%=  %>">--%>
            <button type="submit">Edit</button>
        </form>
    </div>
</div>
</body>
</html>
