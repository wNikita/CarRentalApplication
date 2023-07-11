<%@ page import="java.util.List" %>
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
<div class="form-container" >
    <h1>Add Car</h1>
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
    <form action="register-car" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" >
        </div>
        <div class="form-group">
            <label for="registrationNumber">Registration Number:</label>
            <input type="text" id="registrationNumber" name="registrationNumber" >
        </div>
        <div class="form-group">
            <label for="policyNumber">Policy Number:</label>
            <input type="text" id="policyNumber" name="policyNumber" >
        </div>
        <div>
       <label for="image">Image:</label>
        <input type="file" id="image" name="image" accept="image/*" ><br><br>
            <br><br>
            <img id="preview" src="#" alt="Selected Image" style="display: none; max-width: 400px; max-height: 400px;">

            <script>
                // Get the file input element
                const fileInput = document.getElementById('image');
                // Get the image element for preview
                const imagePreview = document.getElementById('preview');

                // Add an event listener to the file input
                fileInput.addEventListener('change', function(event) {
                    // Get the selected file
                    const selectedFile = event.target.files[0];

                    if (selectedFile) {
                        // Create a FileReader object
                        const reader = new FileReader();

                        // Set the image source once the file is loaded
                        reader.onload = function(event) {
                            imagePreview.src = event.target.result;
                            imagePreview.style.display = 'block';
                        };

                        // Read the selected file as a Data URL
                        reader.readAsDataURL(selectedFile);
                    }
                });
            </script>
        </div>
        <div class="form-group">
            <label for="fuelType">Fuel Type:</label>

            <select id="fuelType" name="fuelType" >
                <option value="none" selected disabled hidden>Select Fuel Type</option>

                <option value="Petrol">Petrol</option>
                <option value="Diesel">Diesel</option>
                <option value="Electric">Electric</option>
            </select>
        </div>
        <div class="form-group">
            <label for="transmissionType">Transmission Type:</label>
            <select id="transmissionType" name="transmissionType" >
                <option value="none" selected disabled hidden>Select Transmission Type </option>

                <option value="Manual">Manual</option>
                <option value="Automatic">Automatic</option>
            </select>
        </div>
        <div class="form-group">
            <label for="carColor">Color:</label>
            <input type="text" id="carColor" name="carColor" >
        </div>
        <div class="form-group">
            <label for="kmTravelled">Kilometers Travelled:</label>
            <input type="text" id="kmTravelled" name="kmTravelled" >
        </div>
        <div class="form-group">
            <label for="ratePerDay">Rate Per Day:</label>
            <input type="text" id="ratePerDay" name="ratePerDay" >
        </div>
        <div class="form-group">
            <label for="modelNumber">Model Number:</label>
            <input type="text" id="modelNumber" name="modelNumber" >
        </div>
        <div class="form-group">
            <label for="noOfSeats">No of Seats:</label>
            <input type="number" id="noOfSeats" name="noOfSeats" >
        </div>
        <div class="form-group">
            <button type="submit" class="button">Add Car</button>
        </div>
    </form>
</div>
</body>
</html>
