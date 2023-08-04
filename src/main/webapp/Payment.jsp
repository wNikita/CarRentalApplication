<%@ page import="com.example.carrentalapplication.model.User" %>
<%@ page import="com.example.carrentalapplication.model.Book" %>
<%@ page import="com.example.carrentalapplication.jpamodel.UserEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function submitForm() {
            // Locate the form element
            var form = document.getElementById('myForm');

            // Submit the form
            form.submit();
        }

        window.onload = function() {
            submitForm();
        };
    </script>
</head>
<body>
<form method="POST" action="https://api.razorpay.com/v1/checkout/embedded" id="myForm">
    <input type="hidden" name="key_id" value="rzp_test_QEtxzC00WQzK7n"/>
    <input type="hidden" name="amount" value="${TotalAmount}"/>
    <input type="hidden" name="order_id" value="${razorpay_order_id}"/>
    <input type="hidden" name="name" value="Car Rental"/>
    <input type="hidden" name="prefill[name]" value="<%= ((UserEntity) session.getAttribute("CurrentUser")).getFirstName() %>"/>
    <input type="hidden" name="prefill[contact]" value="<%= ((UserEntity) session.getAttribute("CurrentUser")).getMobileNumber() %>"/>
    <input type="hidden" name="prefill[email]" value="<%= ((UserEntity) session.getAttribute("CurrentUser")).getEmailId() %>"/>
    <input type="hidden" name="notes[shipping address]" value="<%= ((UserEntity) session.getAttribute("CurrentUser")).getAddress() %>"/>
    <input type="hidden" name="callback_url" value="http://localhost:8080/CarRentalApplication/payment-result"/>
    <input type="hidden" name="cancel_url" value="http://localhost:8080/CarRentalApplication/payment-cancel"/>
</form>
</body>
</html>
