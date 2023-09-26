<%@ page import="org.json.JSONObject" %>
<%@ page import="com.razorpay.Payment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment Result</title>
    <style>
        .center-container {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .success {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .failure {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .logo {
            margin-bottom: 16px;
            width: 100px;
        }

        .text {
            font-size: 24px;
        }
        .payment-details {
            margin-top: 24px;
            font-size: 18px;         }
    </style>
</head>
<body>
<%-- Check the 'isNull' attribute and display the content accordingly --%>
<% if (Boolean.FALSE.equals(request.getAttribute("statusIsSuccess"))) { %>
<div class="center-container">
    <div class="failure">
        <img src="car_uploads/failed.jfif" alt="Failure Logo" class="logo">
        <p class="text">Payment Failed</p>
    </div>
</div>
<% } else { %>
<div class="center-container">
    <div class="success">
        <img src="car_uploads/success.jfif" alt="Success Logo" class="logo">
        <p class="text">Payment Successful</p>
        <div class="payment-details">
            <p>Email: ${paymentDetails.get("email")}</p>
            <p>Contact: ${paymentDetails.get("contact")}</p>
            <p>Payment ID: ${paymentDetails.get("id")}</p>
            <p>Amount: ${orderDetails.get("amount")}</p>
            <p>Payment Status: ${paymentDetails.get("status")}</p>
            <p>Currency: ${paymentDetails.get("currency")}</p>

            <button onclick="redirectToHome()">OK</button> <!-- Add this button -->

        </div>
    </div>
</div>

<script>
    function redirectToHome() {
        window.location.href = "viewStateCity";
    }
</script>

</div>
<% } %>
</body>
</html>
