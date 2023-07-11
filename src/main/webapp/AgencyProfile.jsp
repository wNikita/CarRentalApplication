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
<h1>Agency Details</h1>
<table>
    <tr>
        <th>Agency Name:</th>
        <td>${agency.agencyName}</td>
    </tr>
    <tr>
        <th>GST-Number:</th>
        <td>${agency.GSTNumber}</td>
    </tr>
    <tr>
        <th>Mobile Number:</th>
        <td>${agency.mobileNumber}</td>
    </tr>
    <tr>
        <th>Address Line:</th>
        <td>${agency.addressDetails.addressLine}</td>
    </tr>
    <tr>
        <th>Pin Code:</th>
        <td>${agency.addressDetails.pinCode}</td>
    </tr>
    <tr>
        <th>State:</th>
        <td>${agency.addressDetails.state.stateName}</td>
    </tr>
    <tr>
        <th>City:</th>
        <td>${agency.addressDetails.city.cityName}</td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <form action="update-agency">
                <input type="hidden" name="agencyId" value="${agency.agencyDetailsId}">
                <input type="submit" value="Update">
            </form>
        </td>
    </tr>
</table>

</body>
</html>
