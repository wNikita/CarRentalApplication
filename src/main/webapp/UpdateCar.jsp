<%@ page import="java.util.List" %>
<%@ page import="com.example.carrentalapplication.jpamodel.CarDetailsEntity" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register Car</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f2f2f2;
        }

        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            margin: 0 auto;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-group select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }

        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h1>Update Car</h1>
    <div class="errorList">
        <%
            List<Error> errorList = (List<Error>) request.getAttribute("errorList");
            if (errorList != null && !errorList.isEmpty()) {
        %>
        <ul>
            <% for (Error error : errorList) { %>
            <div>
                <li><%= error.getMessage() %>
                </li>
                    <% } %>
        </ul>
        <%
            } %>
    </div>
    <% List<CarDetailsEntity> carDetails = (List<CarDetailsEntity>) request.getAttribute("carDetails");

        for (CarDetailsEntity carDetails1 : carDetails) { %>
    <form action="update-car?carDetailsId=<%=carDetails1.getCarId()%>" method="post" id="form"
          enctype="multipart/form-data">
        <div class="form-group">
            <input type="hidden" name="carId" value="<%=carDetails1.getCarId()%>">

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%=carDetails1.getName()%>">
        </div>
        <div class="form-group">
            <label for="registrationNumber">Registration Number:</label>
            <input type="text" id="registrationNumber" name="registrationNumber"
                   value="<%=carDetails1.getRegistrationNumber()%>">
        </div>
        <div class="form-group">
            <label for="policyNumber">Policy Number:</label>
            <input type="text" id="policyNumber" name="policyNumber"
                   value="<%=carDetails1.getInsurancePolicyNumber()%>">
        </div>
        <div class="form-group">
            <label for="fuelType">Fuel Type:</label>
            <select id="fuelType" name="fuelType">
<%--                                <option value="Petrol" <%=carDetails1.getFuelType()== 'Petrol' ? 'selected' : ''%>>Petrol</option>--%>
<%--                                <option value="Diesel" <%=carDetails1.getFuelType()== 'Diesel' ? 'selected' : ''%>>Diesel</option>--%>
<%--                                <option value="Electric" <%=carDetails1.getFuelType()== 'Electric' ? 'selected' : ''%>>Electric</option>--%>
                <option value="Petrol">Petrol</option>
                <option value="Diesel">Diesel</option>
                <option value="Electric">Electric</option>

            </select>
        </div>
        <%--        <div>--%>
        <%--                <img id="carImage" src="${image}" alt="" width="80px" height="80px">--%>
        <%--        </div>--%>
        <div>
            <label for="image">Image:</label>
            <input type="file" id="image" name="image" accept="image/*"><br><br>
            <br><br>
            <img id="preview" src="<%=carDetails1.getImage()%>" alt="Selected Image"
                 style="max-width: 400px; max-height: 400px;">

            <script>
                // Get the file input element
                const fileInput = document.getElementById('image');
                // Get the image element for preview
                const imagePreview = document.getElementById('preview');

                // Add an event listener to the file input
                fileInput.addEventListener('change', function (event) {
                    // Get the selected file
                    const selectedFile = event.target.files[0];

                    if (selectedFile) {
                        // Create a FileReader object
                        const reader = new FileReader();

                        // Set the image source once the file is loaded
                        reader.onload = function (event) {
                            imagePreview.src = event.target.result;
                        };

                        // Read the selected file as a Data URL
                        reader.readAsDataURL(selectedFile);
                    }
                });
            </script>
        </div>


        <div class="form-group">
            <label for="transmissionType">Transmission Type:</label>
<%--            <select id="transmissionType" name="transmissionType" value="${carDetails.getTransmissionType()}">--%>
                <select id="transmissionType" name="transmissionType" >

                <%--                <option value="Manual" ${carDetails.getTransmissionType() == 'Manual' ? 'selected' : ''}>Manual</option>--%>
<%--                <option value="Automatic" ${carDetails.getTransmissionType() == 'Automatic' ? 'selected' : ''}>--%>
<%--                    Automatic--%>
<%--                </option>--%>
    <option value="manual">Manual</option>
    <option value="automatic">Automatic</option>

            </select>
        </div>
        <div class="form-group">
            <label for="carColor">Color:</label>
            <input type="text" id="carColor" name="carColor" value="<%=carDetails1.getColor()%>">
        </div>
        <div class="form-group">
            <label for="kmTravelled">Kilometers Travelled:</label>
            <input type="text" id="kmTravelled" name="kmTravelled" value="<%=carDetails1.getKmTravelled()%>">
        </div>
        <div class="form-group">
            <label for="ratePerDay">Rate Per Day:</label>
            <input type="text" id="ratePerDay" name="ratePerDay" value="<%=carDetails1.getChargePerDay()%>">
        </div>
        <div class="form-group">
            <label for="modelNumber">Model Number:</label>
            <input type="text" id="modelNumber" name="modelNumber" value="<%=carDetails1.getModel()%>">
        </div>
        <div class="form-group">
            <label for="noOfSeats">No of Seats:</label>
            <input type="number" id="noOfSeats" name="noOfSeats" value="<%=carDetails1.getNoOfSeats()%>">
        </div>
        <div class="form-group">
            <button type="submit" class="button">Update</button>
        </div>
    </form>


    <%--<script>--%>

    <%--    $(document).ready(function () {--%>
    <%--    populateFormData();--%>
    <%--    $("form").submit(function (event) {--%>
    <%--    event.preventDefault(); // Prevent the default form submission--%>

    <%--    populateFormData(); // Populate the form data--%>

    <%--    // Additional code to submit the form data or perform other actions--%>
    <%--    // ...--%>
    <%--    });--%>
    <%--    });--%>

    <%--    function populateFormData() {--%>
    <%--    // Retrieve form values from session storage--%>
    <%--    var name = sessionStorage.getItem("name");--%>
    <%--    var registrationNumber = sessionStorage.getItem("registrationNumber");--%>
    <%--    var policyNumber = sessionStorage.getItem("policyNumber");--%>
    <%--    var FuelType = sessionStorage.getItem("FuelType");--%>
    <%--    var transmission = sessionStorage.getItem("transmission");--%>
    <%--    var carColor = sessionStorage.getItem("carColor");--%>
    <%--    var kmTravelled = sessionStorage.getItem("kmTravelled");--%>
    <%--    var ratePerDay = sessionStorage.getItem("ratePerDay");--%>
    <%--    var modelNumber = sessionStorage.getItem("modelNumber");--%>
    <%--    var noOfSeats = sessionStorage.getItem("noOfSeats");--%>

    <%--    // Populate the form fields with the retrieved values--%>
    <%--    document.getElementById("name").value = name;--%>
    <%--    document.getElementById("registrationNumber").value = registrationNumber;--%>
    <%--    document.getElementById("policyNumber").value = policyNumber;--%>
    <%--    document.getElementById("FuelType").value = FuelType;--%>
    <%--    document.getElementById("transmission").value = transmission;--%>
    <%--    document.getElementById("carColor").value = carColor;--%>
    <%--    document.getElementById("kmTravelled").value = kmTravelled;--%>
    <%--    document.getElementById("ratePerDay").value = ratePerDay;--%>
    <%--    document.getElementById("modelNumber").value = modelNumber;--%>
    <%--    document.getElementById("noOfSeats").value = noOfSeats;--%>
    <%    }%>
    <%--</script>--%>
</div>
</body>
</html>
