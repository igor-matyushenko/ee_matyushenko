<%--
  Created by IntelliJ IDEA.
  User: igor.matyushenko
  Date: 02.09.2019
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Flower Shop 1.0</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-left-align">
    <h1>Registration</h1>
</div>

<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">

        <table>
            <form method="post" action="registrationServlet">
                <tr>
                    <td colspan="1">Login:</td>
                    <td colspan="2"><input type="text"  name="login" required></td>
                </tr>
                <tr>
                    <td colspan="1">Passwords:</td>
                    <td colspan="2"><input type="password" name="password" required></td>
                </tr>
                <tr>
                    <td colspan="1">First Name:</td>
                    <td colspan="2"><input type="text" name="firstName"  required/></td>
                </tr>
                <tr>
                     <td colspan="1">Second Name:</td>
                     <td colspan="2"><input type="text" name="lastName"  required/></td>
                </tr>
                <tr>
                      <td colspan="1">Your address:</td>
                      <td colspan="2"><input type="text" name="address" required/></td>
                </tr>
                <tr>
                  <td colspan="1">Your email:</td>
                  <td colspan="2"><input type="text"  name="email"   required/></td>
                </tr>

                <tr>
                    <td colspan="1">Your phone:</td>
                    <td colspan="2"><input type="text"  name="phoneNumber"   required/></td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="1"><input class="w3-btn w3-grey w3-hover-light-green w3-round-large " type=submit  value="Registration"/></td>
            </form>
            <form method="post" action="indexServlet">
                <td colspan="1"><input class="w3-btn w3-black w3-hover-light-green w3-round-large " type=submit  value="Back"/></td>
            </form>
            </tr>
        </table>
    </div>

    <div class="w3-container w3-center"><%
        Object error = request.getAttribute("error");
        if(error!=null) {
            out.println("<div style=\"color: red\">" + request.getAttribute("error") + "</p>");
        }%>
    </div>

</div>

</body>
</html>