<%@ page import="com.example.carrentalapplication.model.State" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.carrentalapplication.model.City" %>
<%@ page import="com.example.carrentalapplication.model.User" %>
<%@ page import="com.example.carrentalapplication.jpamodel.UserEntity" %><%--<!DOCTYPE html>--%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
        crossorigin="anonymous">
</script>
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
<body>
<div  class="welcome-message">
<%--    <h1>Welcome to Car Rental System</h1>--%>
    <h1>Welcome to Car Rental System, <%= ((UserEntity) session.getAttribute("CurrentUser")).getFirstName() %>!</h1>


</div>
<form action="search-car" id="form">
    <div style="display: flex; align-items: center;">
        <div>

<%--            <script>sessionStorage.clear();</script>--%>

            <label for="state">State:</label>
            <select id="state" name="state" required>
                <option value="none" selected disabled hidden>Select an State</option>
                <%
                    List<State> stateList = (List<State>) request.getAttribute("states");
                    for (State state : stateList) {
                %>
                <option value="<%= state.getStateId() %>"><%= state.getStateName() %>
                </option>
                <% } %>
            </select>
        </div>
        <div>
        <label for="city">City:</label>
        <select id="city" name="city" required>
            <option value="none" selected disabled hidden>Select an City</option>
            <%
                if (request.getAttribute("cityList") != null) {
                    List<City> cityList = (List<City>) request.getAttribute("cityList");
                    for (City city : cityList) {
            %>
            <option value="<%= city.getCityId()%>"><%= city.getCityName() %>
            </option>
            <% }
            } %>
        </select>
        </div>
        <div style="margin-right: 10px;">
            <label for="startDate">Start Date:</label>
            <input type="date" id="startDate" name="startDate" required min="<%= getCurrentDate() %>">
        </div>
                <%!
                    public String getCurrentDate() {
                        java.util.Date currentDate = new java.util.Date();
                        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                        return sdf.format(currentDate);
                    }
                %>

        <div>
            <label for="endDate">End Date:</label>
            <input type="date" id="endDate" name="endDate" required>
        </div>
        <div class="navbar">
            <a href="user-profile">My Account</a>
            <a href="booking-details">My Booking</a>
            <a class="right" href="logout">Logout</a>
        </div>


    </div>
    <input type="submit" value="Search">
</form>

<script>
    // Set the minimum end date based on the selected start date
    document.getElementById("startDate").addEventListener("change", function() {
        var startDate = new Date(this.value);
        var endDateInput = document.getElementById("endDate");
        endDateInput.min = this.value; // Set the minimum attribute
        // Reset the end date value if it is before the selected start date
        if (new Date(endDateInput.value) < startDate) {
            endDateInput.value = this.value;
        }
    });
</script>
<script>
        $(document).ready(function () {
            populateFormData();
        $("form").submit(function (event) {
        //sessionStorage.clear();
    });
    });
    function populateFormData() {
        var state = sessionStorage.getItem("state");
        document.getElementById("state").value = state;
    }
    function saveFormData() {
        var state = document.getElementById("state").value;
        sessionStorage.setItem("state", state);
    }

        // Call the saveFormData function whenever form values change
        $(document).on('input', '#form input, #form select', function () {
            saveFormData();
        });
</script><style>
    body {
        margin: 0;
        padding: 0;
        background-image: url("car_uploads/carhome.jpg");
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        font-family: Arial, sans-serif;
    }

    .navbar {
        left: 0;
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
        padding: 18px 20px;
        text-decoration: none;
    }

    .navbar .right {
        float: right;
    }

    .welcome-message {
        text-align: center;
        font-size: 24px;
        color: #ffffff;
        padding: 30px;
    }

    select#state,
    select#city {
        font-size: 18px;
    }

    form {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        align-items: center;
        max-width: 600px;
        margin: 80px auto;
        background-color: rgba(255, 255, 255, 0.8);
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    form > div {
        flex: 0 0 33.33%;
        margin-bottom: 20px;
        padding: 0 10px;
    }

    label {
        display: block;
        font-weight: bold;
        margin-bottom: 10px;
    }

    input[type="text"],
    input[type="date"] {
        width: 100%;
        padding: 10px;
        font-size: 16px;
        border-radius: 5px;
        border: 1px solid #cccccc;
    }

    input[type="submit"] {
        width: 100%;
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
