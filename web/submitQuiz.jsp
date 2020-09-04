<%-- 
    Document   : submitQuiz
    Created on : Jun 1, 2020, 5:22:04 PM
    Author     : USER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Submit Quiz Page</title>
    </head>
    <body>
        <jsp:include page="./header.jsp"/>
        <div class="card container mt-4 p-3" style="width: 550px">
            <h1 class="text-success">Submit Success!!</h1>

            <h3>My Quiz: ${requestScope.CORRECT} / ${requestScope.TOTAL}</h3>
            <h2>Score: ${requestScope.MARK} </h2>
        </div>




    </body>
</html>
