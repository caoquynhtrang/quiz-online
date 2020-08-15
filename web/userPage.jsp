<%-- 
    Document   : userPage
    Created on : May 25, 2020, 4:18:58 PM
    Author     : USER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <font color="red"> Welcome, ${sessionScope.USER.fullname}</font>
        <c:url var="logout" value="DispatcherController" >
            <c:param name="btAction" value="Logout"></c:param>
        </c:url>
        <a href="${logout}">Logout</a> 
        <h1>Take a Quiz</h1>
        <form action="DispatcherController" method="POST">
            Choose Subject: <select name="subjectId">
                <c:forEach var="subject" items="${requestScope.SUBJECT}">
                    <option value="${subject.id}">
                        ${subject.name}
                    </option>
                </c:forEach>

            </select> </br>
            <input type="submit" value="ATTEMPT QUIZ NOW" name="btAction"/>
        </form>
        
    </body>
</html>
