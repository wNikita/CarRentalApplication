<%--
  Created by IntelliJ IDEA.
  User: wnikita
  Date: 19-06-2023
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>verify</title>
<%--    <script>--%>

<%--        document.getElementsByName("verifyFORM")--%>
<%--        //1.Access query string userId from address bar--%>
<%--        //2. Access form by name--%>
<%--        //3. Access hidden element by name and change value--%>
<%--    </script>--%>
</head>
<body>
<center><% if(request.getAttribute("errorMsg") != null) {%>
    <div><%=request.getAttribute("errorMsg")%></div>
    <% } %>
    <h1>  </h1>
    <h2>Thank You</h2>
    <h3>please check you inbox and verify mail</h3>
    <form action="verification" method="Post">
        <label for="code">Enter Verification Code:</label>
        <input type="text" id="code" name="code" required>
        <input type="hidden" id="userId" name="userId" value=<%=request.getAttribute("userId")%> >
        <br><br>
        <input type="submit" value="Submit">
        <a href="mail-send?userId=<%=request.getAttribute("userId")%>">Resend Mail</a>
    </form>
</center>
</body>
</html>
