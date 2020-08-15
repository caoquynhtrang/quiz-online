<%-- 
    Document   : adminPage
    Created on : May 25, 2020, 4:31:57 PM
    Author     : USER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <font color="red"> Welcome, ${sessionScope.USER.fullname}</font>
        <c:url var="logout" value="DispatcherController" >
            <c:param name="btAction" value="Logout"></c:param>
        </c:url>
        <a href="${logout}">Logout</a> 
        <h1>List Question and Answer</h1>
        <form action="DispatcherController" method="GET">
            Search Question: <input type="text" name="search" value="${param.search}" />
            <input type="submit" value="Search" name="btAction"/>

        </form>
        <form action="DispatcherController" method="POST">
            Choose Subject: <select name="subjectId">
                <c:forEach var="subject" items="${requestScope.SUBJECT}">
                    <option value="${subject.id}">
                        ${subject.name}
                    </option>
                </c:forEach>

                <input type="submit" value="Search Subject" name="btAction"/>
            </select> </br>
        </form>

        <form action="DispatcherController" method="POST">
            Choose Status: <select name="statusId">
                <c:forEach var="status" items="${requestScope.STATUS}">
                    <option value="${status.id}">
                        ${status.name}
                    </option>
                </c:forEach>
                <input type="submit" value="Search Status" name="btAction"/>
            </select> </br>
        </form>


        <c:url var="showSubject" value="DispatcherController">
            <c:param name="btAction" value="Show Subject"></c:param>
        </c:url>
        <a href="${showSubject}">Create Question</a>


        <c:set var="list" value="${requestScope.LIST}"></c:set>
        <c:if test="${not empty list}">
            <form action="DispatcherController">
                <select name="page">
                    <c:forEach varStatus="counter" items="${requestScope.TOTAL_PAGE}">
                        <option
                            <c:if test="${param.page eq counter.count}">
                                selected="selected"
                            </c:if>
                            >
                            ${counter.count}
                        </option>
                    </c:forEach>
                </select>
                <input type="submit" value="Change Page" name="btAction" />
            </form>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Question</th>
                        <th>Create Date</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${list}" varStatus="counter">
                    <form action="DispatcherController" method="POST" autocomplete="off">
                        <tr>
                            <td>
                                <c:if test="${not empty param.page}">
                                    ${(param.page - 1) * 5 + counter.count}
                                </c:if>
                                <c:if test="${empty param.page}">
                                    ${counter.count}
                                </c:if>
                            </td>
                            <td>
                                <input type="hidden" name="txtQuestionId" value="${dto.id}" />
                                <textarea name="txtContent" value="" rows="2"  cols="100">${dto.content}</textarea>
                                <br/>
                                <c:set var="answerTitle" value="65"/>
                                <c:forEach var="answerDTO" items="${dto.answerList}">
                                    &\#${answerTitle}) 
                                    <c:set var="answerTitle" value="${answerTitle + 1}"/> 
                                    <input type="hidden" name="txtAnswer" value="${answerDTO.id}" />
                                    <input type="text" name="${answerDTO.id}" value="${answerDTO.content}" />
                                    </br>
                                </c:forEach>
                                <c:set var="answerTitle" value="65"/>
                                Correct Answer: <select name="correctAnsId">
                                    <c:forEach var="answer" items="${dto.answerList}">

                                        <option 
                                            value="${answer.id}"
                                            <c:if test="${answer.id eq dto.correctAnswerId}">selected="selected"</c:if>
                                            >&\#${answerTitle}</option>
                                        <c:set var="answerTitle" value="${answerTitle + 1}"/> 
                                    </c:forEach>
                                </select>       
                            </td>

                            <td>${dto.createDate}</td>
                            <td>
                                <c:url var="deleteUrl" value="DispatcherController">
                                    <c:param name="txtPage" value="${param.page}"></c:param>
                                    <c:param name="btAction" value="Delete"></c:param>
                                    <c:param name="txtIdQuestion" value="${dto.id}"></c:param>
                                    <c:param name="txtQuestion" value="${dto.content}"></c:param>
                                    <c:param name="txtCreateDate" value="${dto.createDate}"></c:param>
                                    <c:param name="txtAnswer" value="${dto.answerList}"></c:param>
                                </c:url>
                                <a href="${deleteUrl}">Delete</a>
                            </td>

                            <td>
                                <input type="hidden" name="txtPage" value="${param.page}" />
                                <input type="submit" value="Update" name="btAction" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>

            </tbody>
        </table>
    </c:if>

    <c:if test="${empty list}">
        <h3>No Question and Answer in library</h3>
    </c:if>

</body>

</html>
