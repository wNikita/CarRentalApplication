<%@ page import="com.example.carrentalapplication.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f1f1f1;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            text-align: left;
            padding: 12px 15px;
        }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #e9e9e9;
        }
    </style>
</head>
<body>
<h1>Booking</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Total Rental Days</th>
        <th>Total Cost</th>
        <th>Car Name</th>
        <th>Car Number</th>
        <th>Booking Date</th>
        <!-- Add more table headers as needed -->
    </tr>

    <% List<Book> BookingDetails = (List<Book>) request.getAttribute("bookingDetails");

        for (Book book : BookingDetails) { %>
    <tr>
        <td><%= book.getBookingId() %></td>
        <td><%= book.getPickupDate() %></td>
        <td><%= book.getReturnDate()%></td>
        <td><%= book.getRentalDays()%></td>
        <td><%= book.getTotalCost()%></td>
        <td><%= book.getCarDetails().getName()%></td>
        <td><%= book.getCarDetails().getRegistrationNumber()%></td>
        <td><%=book.getCreatedDate()%></td>

    </tr>
    <% } %>
</table>
</body>
</html>
