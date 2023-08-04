<%@ page import="com.example.carrentalapplication.model.User" %>
<%@ page import="com.example.carrentalapplication.jpamodel.CarDetailsEntity" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>

</head>
<%--<form method="POST" action="https://api.razorpay.com/v1/checkout/embedded">--%>
<%--    <input type="hidden" name="key_id" value="rzp_test_qUJtvCngnjwc5a"/>--%>
<%--    <input type="hidden" name="amount" value="${Book.getTotalCostDTO()}"/>--%>
<%--    <input type="hidden" name="order_id" value="${orderId}"/>--%>
<%--    <input type="hidden" name="name" value="Car Rental"/>--%>
<%--    <input type="hidden" name="prefill[name]" value="<%= ((User) session.getAttribute("CurrentUser")).getFirstName()%>"></h1>--%>
<%--    <input type="hidden" name="prefill[contact]" value=""<%= ((User) session.getAttribute("CurrentUser")).getMobileNO()%>">"/>--%>
<%--    <input type="hidden" name="prefill[email]" value=""<%= ((User) session.getAttribute("CurrentUser")).getEmailId()%>">"/>--%>
<%--    <input type="hidden" name="notes[shipping address]" value=""<%= ((User) session.getAttribute("CurrentUser")).getAddress()%>">"/>--%>
<%--    <input type="hidden" name="callback_url" value="https://example.com/payment-callback"/>--%>
<%--    <input type="hidden" name="cancel_url" value="https://example.com/payment-cancel"/>--%>
<%--</form>--%>


<title>Car Rental System - View Car Details</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f7f7f7;
    }

    .container {
        max-width: 800px;
        margin: 0 auto;
        padding: 20px;
        display: flex;
        justify-content: space-between;
    }

    .car-details {
        flex: 1;
        background-color: #fff;
        padding: 20px;
        border-radius: 4px;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
        margin-right: 20px;
    }

    .car-image {
        width: 100%;
        max-width: 400px;
        height: auto;
        margin-bottom: 20px;
        border-radius: 4px;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
    }

    .car-title {
        font-size: 32px;
        font-weight: bold;
        margin: 0;
        color: #333;
    }

    .car-features {
        margin-bottom: 20px;
        font-size: 18px;
        color: #333;
    }

    .car-features ul {
        margin: 0;
        padding: 0;
    }

    .car-features li {
        list-style: none;
        margin-bottom: 5px;
    }

    .car-features li:before {
        content: "\2022";
        margin-right: 10px;
    }

    .car-description {
        margin-bottom: 20px;
        font-size: 18px;
        color: #333;
    }

    .rental-info {
        margin-bottom: 20px;
        font-size: 18px;
        color: #333;
    }

    .rental-info p {
        margin-bottom: 10px;
    }

    .booking-info {
        flex: 1;
        background-color: #fff;
        padding: 20px;
        border-radius: 4px;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-items: flex-end;
    }

    .button-container {
        text-align: center;
        margin-top: 20px;
    }

    .button-container button {
        padding: 10px 20px;
        font-size: 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .button-container button:hover {
        background-color: #0056b3;
    }

    .form-row {
        display: flex;
        align-items: center;
        margin-bottom: 10px;
    }

    .form-row label {
        margin-right: 10px;
    }

    .form-row input[type="date"] {
        padding: 6px;
        border-radius: 4px;
        border: 1px solid #ccc;
    }
</style>
</head>
<body>
<% List<CarDetailsEntity> carDetails = (List<CarDetailsEntity>) request.getAttribute("carDetails");

    for (CarDetailsEntity carDetails1 : carDetails) { %>
<form action="car-details" method="post" enctype="multipart/form-data">
    <div class="container">
        <div class="car-details">
            <img class="car-image" src="<%=carDetails1.getImage()%>" alt="Car Image" alt="Car Image" width="400px"
                 height="auto">
            <h2 class="car-title">Car Name: <%=carDetails1.getName()%></h2>
            <div class="car-features">
                <p>About Car:</p>
                <ul>
                    <li>Km Travelled: <%=carDetails1.getKmTravelled()%></li>
                    <li>No Of Seats: <%=carDetails1.getNoOfSeats()%></li>
                    <li>Model Number:<%=carDetails1.getModel()%></li>
                </ul>
            </div>
            <div class="car-description">
                <p>Description:</p>
                <ul>
                    <li>Registration Number: <%=carDetails1.getRegistrationNumber()%></li>
                    <li>Policy Number: <%=carDetails1.getInsurancePolicyNumber()%></li>
                    <li>Fuel Type: <%=carDetails1.getFuelType()%></li>
                    <li>Transmission Type: <%=carDetails1.getTransmissionType()%></li>
                    <li>Car Color: <%=carDetails1.getColor()%></li>
                </ul>
            </div>
        </div>

        <div class="booking-info">
            <div class="button-container">
                <div>
                    <label for="licenseImage">Image:</label>
                    <input type="file" id="licenseImage" name="licenseImage" accept="image/*"><br><br>
                    <br><br>
                    <img id="preview" src="#" alt="Selected Image"
                         style="display: none; max-width: 400px; max-height: 400px;">

                    <script>
                        // Get the file input element
                        const fileInput = document.getElementById('licenseImage');
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
                                    imagePreview.style.display = 'block';
                                };

                                // Read the selected file as a Data URL
                                reader.readAsDataURL(selectedFile);
                            }
                        });
                    </script>
                </div>
                <%--            <div class="form-row" id="PickupDate" name="PickupDate">--%>
                <%--                <label >Pickup Date:${PickupDate}</label>--%>
                <%--            </div>--%>
                <div class="form-row" id="pickupDate">
                    <label>Pickup Date: ${PickupDate}</label>
                </div>

                <div class="form-row">
                    <p>ReturnDate:${ReturnDate}</p>
                </div>
                <div class="form-row">
                    <p>Total Number Of Days:${TotalNumberOfDays}</p>
                </div>
                <div class="form-row">
                    <p>Charge Per Day:<%=carDetails1.getChargePerDay()%></p>
                </div>
                <div class="form-row">
                    <p id="amount">Total Charge for ${TotalNumberOfDays} Days:${totalCharge}</p>
                </div>
                <button type="submit" class="book-button">BOOK</button>
            </div>
        </div>
        <form action="car-details" method="post">
            <input type="hidden" name="pickUpDate" value=${PickupDate}>
            <input type="hidden" name="returnDate" value=${ReturnDate}>
            <input type="hidden" name="totalNumberOfDays" value=${TotalNumberOfDays}>
            <input type="hidden" name="totalCharge" value=${totalCharge}>
            <input type="hidden" name="carId" value=<%=carDetails1.getCarId()%>>
        </form>
    </div>
</form>
<%}%>
</div>
</body>
</html>
