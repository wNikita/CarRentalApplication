<%@ page import="com.example.carrentalapplication.model.CarDetails" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.carrentalapplication.jpamodel.CarDetailsEntity" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
        crossorigin="anonymous">
</script>
<script>
    $(document).on('change', '#transmission', function () {
        var transmission = $(this).val();

        // get current address bar URL
        const currURL = new URL($(location).attr('href'));

        // clear existing query string value and append query string stateId and provide value of dropdown sel.
        currURL.searchParams.set("transmission", transmission);

        // Replace and trigger address BAR url With this new URL
        location.replace(currURL.toString());
    });

</script>
<head>
    <title>Car Details</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        .button-container {
            display: flex;
            gap: 5px;
        }
    </style>
</head>
<body>
<h1>Car Details</h1>
<table>
    <thead>
    <tr>
        <th>Car ID</th>
        <th>Image</th>
        <th>Car Name</th>
        <th>Registration Number</th>
        <th>Model</th>
        <th>Charge Per Day</th>
        <th>Fuel Type</th>
        <th>Transmission Mode</th>

    </tr>
    </thead>

    <tbody>
    <%-- Iterate over the car list and populate the table rows --%>

    <% List<CarDetailsEntity> carDetails = (List<CarDetailsEntity>) request.getAttribute("carDetails");

        for (CarDetailsEntity carDetails1 : carDetails) { %>
    <tr>
        <td><%= carDetails1.getCarId() %>
        </td>
        <td><img src="<%=carDetails1.getImage()%>"  alt="" width="80px" height="80px"></td>
<%--        <td><img src="<%=carDetails1.getImage()%>" alt="" width="50px" height="50px">--%>

        <td><%= carDetails1.getName()%>
        </td>
        <td><%= carDetails1.getRegistrationNumber() %>
        </td>
        <td><%= carDetails1.getModel() %>
        </td>
        <td><%= carDetails1.getChargePerDay() %>
        </td>
        <td><%=carDetails1.getFuelType()%></td>
        <td><%=carDetails1.getTransmissionType()%></td>
        <td>
            <div class="button-container">
                <form action="update-car?carDetailsID="<%=carDetails1.getCarId()%>>
                    <input type="hidden" name="carId" value="<%= carDetails1.getCarId() %>">
                    <button type="submit">Edit</button>
                </form>
                <form action="delete-car" method="post">
                    <input type="hidden" name="carId" value="<%= carDetails1.getCarId() %>">
                    <button type="submit">Delete</button>
                </form>
            </div>
        </td>
    </tr>
    <% } %>
    </tbody>

    <form action="sort" id="sortForm">
        <label for="sort">Sort By:</label>
        <select name="sort" id="sort" onchange="sortCars()">
            <option value="none" selected disabled hidden>Select Sorting</option>
            <option value="name_asc">Car Name (Ascending)</option>
            <option value="name_desc">Car Name (Descending)</option>
            <option value="rate_max">Rental Rate (Maximum)</option>
            <option value="rate_min">Rental Rate (Minimum)</option>
        </select>
    </form>
    <form action="filter" id="fuelTypeForm">
        <label for="fuelType">Filter by Fuel Type:</label>
        <select name="fuelType" id="fuelType" onchange="saveSelectedFuelType()">
            <option value="none" selected disabled hidden>Select Fuel Type</option>
            <option value="petrol">Petrol</option>
            <option value="diesel">Diesel</option>
        </select>
    </form>
    <form action="filter" id="transmissionForm">
        <label for="transmission">Filter by Transmission:</label>
        <select name="transmission" id="transmission" onchange="saveSelectedTransmission()">
            <option value="none" selected disabled hidden>Select Transmission</option>
            <option value="automatic">Automatic</option>
            <option value="manual">Manual</option>
        </select>
    </form>

    <script>
        // Retrieve the selected transmission from local storage and set it as the selected option
        document.addEventListener('DOMContentLoaded', function() {
            var transmissionSelect = document.getElementById('transmission');
            var selectedTransmission = localStorage.getItem('selectedTransmission');
            if (selectedTransmission) {
                transmissionSelect.value = selectedTransmission;
            }
        });

        // Store the selected transmission in local storage when it changes
        function saveSelectedTransmission() {
            var transmissionSelect = document.getElementById('transmission');
            var selectedTransmission = transmissionSelect.value;
            localStorage.setItem('selectedTransmission', selectedTransmission);
        }
    </script>

    <script>
        // Retrieve the selected option from local storage and set it as the selected option
        document.addEventListener('DOMContentLoaded', function() {
            var sortSelect = document.getElementById('sort');
            var selectedSort = localStorage.getItem('selectedSort');
            if (selectedSort) {
                sortSelect.value = selectedSort;
            }
        });

        // Store the selected option in local storage when it changes
        function sortCars() {
            var sortSelect = document.getElementById('sort');
            var selectedSort = sortSelect.value;
            localStorage.setItem('selectedSort', selectedSort);
            sortForm.submit();
        }
    </script>


    <script>
        // Retrieve the selected fuel type from local storage and set it as the selected option
        document.addEventListener('DOMContentLoaded', function() {
            var fuelTypeSelect = document.getElementById('fuelType');
            var selectedFuelType = localStorage.getItem('selectedFuelType');
            if (selectedFuelType) {
                fuelTypeSelect.value = selectedFuelType;
            }
        });

        // Store the selected fuel type in local storage when it changes
        function saveSelectedFuelType() {
            var fuelTypeSelect = document.getElementById('fuelType');
            var selectedFuelType = fuelTypeSelect.value;
            localStorage.setItem('selectedFuelType', selectedFuelType);
            fuelTypeForm.submit();
        }
    </script>


</table>
</body>
</html>
