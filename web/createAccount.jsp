<%-- 
    Document   : createAccount
    Created on : May 20, 2020, 10:29:24 PM
    Author     : USER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="DispatcherController" method="POST">
            Email* <input type="text" name="txtEmail" value="${param.txtEmail}" /> (11 - 30 characters)<br/>
            <c:set var="errors" value="${requestScope.CREATEERROR}" ></c:set>
            <c:if test="${not empty errors.emailLengthErr}">
                <font color="red">${errors.emailLengthErr}</font><br/>
            </c:if>

            Password* <input type="password" name="txtPassword" value="${param.txtPassword}" /> (6 - 30 characters)<br/>

            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">${errors.passwordLengthErr}</font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="${param.txtConfirm}" /><br/>

            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">${errors.confirmNotMatch}</font><br/>
            </c:if>
            Name* <input type="text" name="txtName" value="${param.txtName}" /> (2 - 20 characters)<br/>

            <c:if test="${not empty errors.nameIsExist}">
                <font color="red">${errors.nameIsExist}</font><br/>
            </c:if>
            Fullname* <input type="text" name="txtFullname" value="${param.txtFullname}" /> (5 - 50 characters)<br/>

            <c:if test="${not empty errors.fullnameLengthErr}">
                <font color="red">${errors.fullnameLengthErr}</font><br/>
            </c:if>
            <input type="submit" name="btAction" value="Create New Account" />
            <input type="submit" value="Reset" />
        </form>
    </body>
</html>
