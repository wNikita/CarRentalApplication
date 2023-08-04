<%@ page import="com.example.carrentalapplication.model.State" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.carrentalapplication.model.City" %>
<%@ page import="com.example.carrentalapplication.jpamodel.StateEntity" %>
<%@ page import="com.example.carrentalapplication.jpamodel.CityEntity" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous">
    </script>
    <title>Add Agency</title>
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

        form {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            margin: 0 auto;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 10px;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            border: 1px solid #cccccc;
            margin-bottom: 20px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

    </style>

    <script>
        $(document).on('change', '#state', function () {
            var stateID = $(this).val();

            // get current address bar URL
            const currURL = new URL($(location).attr('href'));

            // clear existing query string value and append query string stateId and provide value of dropdown sel.
            currURL.searchParams.set("stateID", stateID);

            // Replace and trigger address BAR url With this new URL
            location.replace(currURL.toString());
        });

    </script>
</head>
<body>
<h1>Add Agency</h1>
<form action="agency-form" method="Post" id="form">
    <label for="agencyName">Agency Name:</label>
    <input type="text" id="agencyName" name="agencyName" >

    <label for="mobileNumber">Mobile Number:</label>
    <input type="tel" id="mobileNumber" name="mobileNumber" >

    <label for="addressLine">Address Line:</label>
    <input type="text" id="addressLine" name="addressLine" >

    <label for="state">State:</label>
    <select id="state" name="state" >
        <option value="none" selected disabled hidden>Select an State</option>
        <%
            List<StateEntity> stateList = (List<StateEntity>) request.getAttribute("states");
            for (StateEntity state : stateList) {
        %>
        <option value="<%= state.getStateId() %>"><%= state.getStateName() %>
        </option>
        <% } %>
    </select>
    <label for="city">City:</label>
    <select id="city" name="city" >
        <option value="none" selected disabled hidden>Select an City</option>
        <%
            if (request.getAttribute("cityList") != null) {
                List<CityEntity> cityList = (List<CityEntity>) request.getAttribute("cityList");
                for (CityEntity city : cityList) {
        %>
        <option value="<%= city.getCityId()%>"><%= city.getCityName() %>
        </option>
        <% }
        } %>
    </select>


    <label for="pinCode">Pin Code:</label>
    <input type="tel" id="pinCode" name="pinCode" >

    <label for="gstNumber">GST Number:</label>
    <input type="text" id="gstNumber" name="gstNumber" >

    <input type="submit" value="Submit">
</form>
<script>
        $(document).ready(function () {
        populateFormData();
        $("form").submit(function (event) {
        //sessionStorage.clear();
    });
    });
    function populateFormData() {
        var agencyName = sessionStorage.getItem("agencyName");
        var mobileNumber = sessionStorage.getItem("mobileNumber");
        var addressLine = sessionStorage.getItem("addressLine");
        var state = sessionStorage.getItem("state");
        var city = sessionStorage.getItem("city");
        var pinCode = sessionStorage.getItem("pinCode");
        var gstNumber = sessionStorage.getItem("gstNumber");

        document.getElementById("agencyName").value = agencyName;
        document.getElementById("mobileNumber").value = mobileNumber;
        document.getElementById("addressLine").value = addressLine;
        document.getElementById("state").value = state;
        document.getElementById("city").value = city;
        document.getElementById("pinCode").value = pinCode;
        document.getElementById("gstNumber").value = gstNumber;
    }
    function saveFormData() {
        // Retrieve form values
        var agencyName = document.getElementById("agencyName").value;
        var mobileNumber = document.getElementById("mobileNumber").value;
        var addressLine = document.getElementById("addressLine").value;
        var state = document.getElementById("state").value;
        var city = document.getElementById("city").value;
        var pinCode = document.getElementById("pinCode").value;
        var gstNumber = document.getElementById("gstNumber").value;

        // Save form data to local storage
        sessionStorage.setItem("agencyName", agencyName);
        sessionStorage.setItem("mobileNumber", mobileNumber);
        sessionStorage.setItem("addressLine", addressLine);
        sessionStorage.setItem("state", state);
        sessionStorage.setItem("city", city);
        sessionStorage.setItem("pinCode", pinCode);
        sessionStorage.setItem("gstNumber", gstNumber);
    }

    // Call the saveFormData function whenever form values change
    $(document).on('input', '#form input, #form select', function () {
        saveFormData();
    });
</script>
</body>
</script>
</html>

