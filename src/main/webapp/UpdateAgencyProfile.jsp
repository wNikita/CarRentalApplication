<%@ page import="com.example.carrentalapplication.model.State" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.carrentalapplication.model.City" %>
<%@ page import="com.example.carrentalapplication.model.AgencyDetails" %>
<!DOCTYPE html>
<html>
<head>
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
<h1>Agency Details</h1>
<form action="update-agency" method="post" id="form">
    <div class="errorList">
        <%
            List<Error> errorList = (List<Error>) request.getAttribute("errorList");
            if (errorList != null && !errorList.isEmpty()) {
        %>
        <ul>
            <% for (Error error : errorList) { %>
            <div>   <li><%= error.getMessage() %></li>
                    <% } %>
        </ul>
        <%
            } %>
    </div>
<table>
    <%
        AgencyDetails agencyDetails = (AgencyDetails) request.getAttribute("agency");
    %>
    <tr>
        <th><label for="agencyName">Agency Name:</label></th>
        <td><input type="text" id="agencyName" name="agencyName" value="<%=agencyDetails.getAgencyName()%>" ></td>
    </tr>
    <tr>
        <th><label for="gstNumber">GST Number:</label></th>
        <td><input type="text" id="gstNumber" name="gstNumber" value="<%=agencyDetails.getGSTNumber()%>" ></td>
    </tr>
    <tr>
        <th><label for="mobileNumber">Mobile Number:</label></th>
        <td><input type="tel" id="mobileNumber" name="mobileNumber" value=" <%=agencyDetails.getMobileNumber()%>" ></td>
    </tr>
    <tr>
        <th><label for="Addressline">AddressLine:</label></th>
        <td><input type="text" id="Addressline" name="Addressline" value=" <%=agencyDetails.getAddressDetails().getAddressLine()%>"
                   ></td>
    </tr>
    <tr>
        <th><label for="PinCode">Pin Code:</label></th>
        <td><input type="text" id="PinCode" name="PinCode" value="<%=agencyDetails.getAddressDetails().getPinCode()%>" ></td>
    </tr>
    <tr>

        <th><label for="state">State:</label></th>
        <td>
            <select id="state" name="state" required>
                <option>Select a State</option>
                <% List<State> stateList = (List<State>) request.getAttribute("states");
                    for (State state : stateList) {
                        String selected = (state.getStateId()==(int)request.getAttribute("stateID")) ? "selected" : "";
                %>
                <option value="<%= state.getStateId() %>"<%=selected%>><%= state.getStateName() %></option>
                <% } %>
            </select>
        </td>

    </tr>
    <tr>
        <th><label for="city">City:</label></th>
        <td>
            <select id="city" name="city" required>
                <option>Select a City</option>
                <% if (request.getAttribute("cityList") != null) {
                    List<City> cityList = (List<City>) request.getAttribute("cityList");
                    for (City city : cityList) {
                        String selected = (city.getCityId() == (int) request.getAttribute("cityId")) ? "selected" : "";
                %>
                <option value="<%= city.getCityId() %>" <%= selected %>><%= city.getCityName() %></option>
                <% }
                } %>
            </select>
        </td>
    </tr>


   <tr>
        <td colspan="2" align="center">
            <form action="update-agency" method="post">
                <input type="hidden" name="agencyId" value="<%=agencyDetails.getAgencyDetailsId()%>">
                <input type="submit" value="Update">
            </form>
        </td>
    </tr>
</table>
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
   </script>
</form>
</body>
</html>

