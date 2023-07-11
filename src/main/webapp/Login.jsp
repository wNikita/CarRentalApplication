<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        .container {
            width: 300px;
            margin: 0 auto;
            margin-top: 100px;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        }

        h2 {
            text-align: center;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
        }

        .form-group input {
            width: 100%;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .form-group .btn {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            border: none;
            color: #fff;
            cursor: pointer;
            border-radius: 3px;
        }

        .form-group .btn:hover {
            background-color: #45a049;
        }

        .form-group .btn-reset {
            background-color: #ccc;
            margin-right: 10px;
        }

        .create-account {
            text-align: center;
            margin-top: 10px;
        }

        .create-account a {
            color: #4CAF50;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">

    <h2>Login</h2>
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
    <% if(request.getAttribute("errorMsg") != null) {%>
        <div><%=request.getAttribute("errorMsg")%></div>
    <% } %>
    <form action="login" method="POST">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" >
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" >
        </div>
        <div class="form-group">
            <input type="submit" value="Submit" class="btn">
        </div>
    </form>
    <div class="create-account">
        <a href="registration-form">Create New Account</a>
        <br>
        <a href="forgetPassword.jsp">Forgot Password?</a>
    </div>


</div>
</body>
</html>
