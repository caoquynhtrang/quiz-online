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
        <jsp:include page="./header.jsp"/>
        <div class="card container p-3 mt-4" style="width: 550px">
            <h1>Take a Quiz</h1>
            <form action="DispatcherController" method="POST">
                Choose Subject: <select name="subjectId">
                    <c:forEach var="subject" items="${requestScope.SUBJECT}">
                        <option value="${subject.id}">
                            ${subject.name}
                        </option>
                    </c:forEach>

                </select> </br>
                <input class="btn btn-primary mt-3" type="submit" value="ATTEMPT QUIZ NOW" name="btAction"/>
            </form>
        </div>


    </body>
</html>
