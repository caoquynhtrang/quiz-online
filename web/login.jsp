<%-- 
    Document   : login
    Created on : May 21, 2020, 1:28:12 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="DispatcherController" method="POST">
            Email <input type="text" name="txtEmail" value="" /></br>
            Password <input type="password" name="txtPassword" value="" /></br>
            <input type="submit" value="Login" name="btAction"/>
            <input type="reset" value="Reset" />
        </form>
        <a href="createAccount.jsp">Click here to Sign Up</a>
    </body>
</html>
