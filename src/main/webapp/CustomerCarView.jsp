<%@ page import="com.example.carrentalapplication.model.CarDetails" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.carrentalapplication.jpamodel.CarDetailsEntity" %><%--<%@ page import="com.example.carrentalapplication.model.CarDetails" %>--%>


<!DOCTYPE html>
<html>
<head>
    <title>Cars</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        h1 {
            text-align: center;
            margin-top: 40px;
        }

        .car-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            margin-top: 40px;
        }

        .car-block {
            width: 220px;
            padding: 20px;
            margin: 20px;
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.15);
        }

        .car-block img {
            display: block;
            margin: 0 auto 10px;
            width: 150px;
            height: 100px;
            object-fit: cover;
            border-radius: 5px;
        }

        .car-block h2 {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .car-block p {
            margin-bottom: 10px;
        }

        .book-button {
            display: block;
            width: 100%;
            padding: 8px 12px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            text-align: center;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .book-button:hover {
            background-color: #0056b3;
        }

        .no-cars {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            padding: 20px;
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
            border-radius: 4px;
            text-align: center;
        }

        .no-cars p {
            font-size: 18px;
            margin: 0;
        }
    </style>

<%--    <script>--%>

<%--        function sendViewDeal(param){--%>
<%--            var carId = $(param).attr("carId");--%>
<%--            $("input[carId]").value(carId)--%>
<%--            --%>
<%--        }--%>

<%--    </script>--%>

</head>
<body>
<h1>View Cars</h1>
<%
    List<CarDetailsEntity> carDetails = (List<CarDetailsEntity>) request.getAttribute("carDetails");
    if (carDetails != null) {
%>
<%--<form action="car-details?carDetailsID="<%=carDetails1.getCarId()%>>--%>
<%--    <input type="hidden" name="carId" value="<%= carDetails1.getCarId() %>">--%>
<%--    <input type="hidden" name="pickUpDate" value="${ PickUpdate}">--%>
<%--    <input type="hidden" name="returnDate" value="${ReturnDate}">--%>
<%--    <button type="submit"  class="book-button" >View Deal</button>--%>
<%--</form>--%>
<div class="car-container">
    <% for (CarDetailsEntity carDetails1 : carDetails) { %>
    <div class="car-block">
        <img src="<%=carDetails1.getImage()%>" alt="Car Image">
        <h2><%=carDetails1.getName() %></h2>
        <p>Model: <%= carDetails1.getModel()%></p>
        <p>Charge Per Day: <%=carDetails1.getChargePerDay() %></p>

<%--        <button onclick="sendViewDeal(this)" type="button" class="book-button" carId="<%=carDetails1.getCarId()%>">View Deal</button>--%>

            <form action="car-details?carDetailsID="<%=carDetails1.getCarId()%>>
                <input type="hidden" name="carId" value="<%= carDetails1.getCarId() %>">
                <input type="hidden" name="pickUpDate" value="${ PickUpdate}">
                <input type="hidden" name="returnDate" value="${ReturnDate}">
                <button  type="submit"  class="book-button" carId="">View Deal</button>
            </form>
    </div>
    <% } %>
</div>
<% } %>

<div class="no-cars" id="noCarsMessage">
    <p>No cars found.</p>
</div>

<script>
    // JavaScript to show the alert if no cars found
    document.addEventListener('DOMContentLoaded', function() {
        var carContainer = document.querySelector('.car-container');
        var noCarsMessage = document.getElementById('noCarsMessage');

        if (!carContainer || carContainer.children.length === 0) {
            noCarsMessage.style.display = 'block';
        }
    });
</script>
</body>

</body>
</html>


