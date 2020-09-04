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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

        <!-- JS, Popper.js, and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

    </head>
    <body style="background-color: #F5F9FC">
        <div class="card p-3 container mt-4 p-3" style="width: 550px">
            <h1>Create New Account</h1>
            <form action="DispatcherController" method="POST">
                Email* <input class="form-control" type="text" name="txtEmail" value="${param.txtEmail}" /> (11 - 30 characters)<br/>
                <c:set var="errors" value="${requestScope.CREATEERROR}" ></c:set>
                <c:if test="${not empty errors.emailLengthErr}">
                    <font color="red">${errors.emailLengthErr}</font><br/>
                </c:if>

                Password* <input class="form-control" type="password" name="txtPassword" value="${param.txtPassword}" /> (6 - 30 characters)<br/>

                <c:if test="${not empty errors.passwordLengthErr}">
                    <font color="red">${errors.passwordLengthErr}</font><br/>
                </c:if>
                Confirm* <input class="form-control" type="password" name="txtConfirm" value="${param.txtConfirm}" /><br/>

                <c:if test="${not empty errors.confirmNotMatch}">
                    <font color="red">${errors.confirmNotMatch}</font><br/>
                </c:if>
                Name* <input class="form-control" type="text" name="txtName" value="${param.txtName}" /> (2 - 20 characters)<br/>

                <c:if test="${not empty errors.nameIsExist}">
                    <font color="red">${errors.nameIsExist}</font><br/>
                </c:if>
                Fullname* <input class="form-control" type="text" name="txtFullname" value="${param.txtFullname}" /> (5 - 50 characters)<br/>

                <c:if test="${not empty errors.fullnameLengthErr}">
                    <font color="red">${errors.fullnameLengthErr}</font><br/>
                </c:if>
                <input class="btn btn-primary" type="submit" name="btAction" value="Create New Account" />
                <input class="btn btn-light" type="submit" value="Reset" />
            </form>
        </div>

    </body>
</html>
