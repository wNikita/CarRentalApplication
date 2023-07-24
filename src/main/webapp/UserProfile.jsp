<%@ page import="com.example.carrentalapplication.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.carrentalapplication.jpamodel.UserEntity" %>
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
        List<UserEntity> user = (List<UserEntity> )request.getAttribute("user");
    for (UserEntity user1 : user) { %>
    <p><b>First Name:</b> <%= user1.getFirstName() %></p>
    <p><b>Last Name:</b> <%= user1.getLastName() %></p>
    <p><b>Mobile Number:</b> <%= user1.getMobileNumber() %></p>
    <p><b>Address:</b> <%= user1.getAddress() %></p>
    <p><b>Email:</b> <%= user1.getEmailId() %></p>
    <% } %>

    <div class="button-container">
        <form action="user-update">
<%--            <input type="hidden" name="carId" value="<%=  %>">--%>
            <button type="submit">Edit</button>
        </form>
    </div>
</div>
</body>
</html>
