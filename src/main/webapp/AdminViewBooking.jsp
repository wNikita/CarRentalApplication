<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Booking Details</title>
    <style>
        /* Add some basic styles to the table */
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>
<h1>Order Details</h1>

<table>
    <thead>
    <tr>
        <th>Username</th>
        <th>Email</th>
        <th>Mobile</th>
        <th>Date</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <!-- Loop through orders and display details -->
    <%
        // Sample data
        List<Map<String, String>> orders = new ArrayList<>();
        Map<String, String> order1 = new HashMap<>();
        order1.put("username", "John Doe");
        order1.put("email", "johndoe@example.com");
        order1.put("mobile", "1234567890");
        order1.put("date", "2023-07-10");
        orders.add(order1);
        Map<String, String> order2 = new HashMap<>();
        order2.put("username", "Jane Smith");
        order2.put("email", "janesmith@example.com");
        order2.put("mobile", "9876543210");
        order2.put("date", "2023-07-09");
        orders.add(order2);

        for (Map<String, String> order : orders) {
            String username = order.get("username");
            String email = order.get("email");
            String mobile = order.get("mobile");
            String date = order.get("date");
    %>
    <tr>
        <td><%= username %></td>
        <td><%= email %></td>
        <td><%= mobile %></td>
        <td><%= date %></td>
        <td>
            <form action="order-details.jsp" method="post">
                <input type="hidden" name="username" value="<%= username %>">
                <input type="hidden" name="email" value="<%= email %>">
                <input type="hidden" name="mobile" value="<%= mobile %>">
                <input type="hidden" name="date" value="<%= date %>">
                <button type="submit">View</button>
            </form>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>

</body>
</html>
