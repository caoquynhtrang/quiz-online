<%-- 
    Document   : quiz
    Created on : May 31, 2020, 8:40:28 PM
    Author     : USER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Page</title>
    </head>
    <body>
        <h1>Quiz - Now</h1>
        <form  action="DispatcherController" method="POST">
            <input id="submitBtn" type="submit" value="Submit Quiz" name="btAction"/></br>
            <span id="countDownSpan"></span>
        </form>


        <c:set var="quizDTO" value="${sessionScope.USER_TAKE_QUIZ}"/>
        <c:if test="${empty requestScope.ERROR}">
            <c:set var="dto" value="${requestScope.QUESTION}"></c:set>
            <c:if test="${not empty dto}">
                <form action="DispatcherController" method="POST">
                    <select name="page">
                        <c:forEach varStatus="counter" items="${sessionScope.TOTAL_PAGE}">
                            <option
                                <c:if test="${param.page eq counter.count}">
                                    selected="selected"
                                </c:if>
                                >
                                ${counter.count}
                            </option>
                        </c:forEach>
                    </select>
                    <input id="countDownTxt" type="hidden" name="txtCountDown" value="${sessionScope.COUNT_DOWN}" />


                    <input type="submit" value="Change Page Quiz" name="btAction"/>

                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Question</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <c:if test="${not empty param.page}">
                                        ${param.page}
                                    </c:if>
                                    <c:if test="${empty param.page}">
                                        ${1}
                                    </c:if>
                                </td>
                                <td> 
                                    <input type="hidden" name="txtQuestionId" value="${dto.id}" />
                                    <p name="txtContent" value="" rows="2"  cols="100">${dto.content}</p>
                                    <br/>
                                    <c:set var="answerTitle" value="65"/>
                                    <c:forEach var="answerDTO" items="${dto.answerList}">
                                        <input type="radio" name="txtAnswerId" value="${answerDTO.id}" 
                                               <c:if test="${quizDTO.checkChosenAnswer(answerDTO.id ,dto.id)}">
                                                   checked="checked"
                                               </c:if>
                                               />
                                        &\#${answerTitle})
                                        <span>${answerDTO.content}</span>
                                        </br>
                                        <c:set var="answerTitle" value="${answerTitle + 1}"/> 
                                    </c:forEach>
                                </td>

                            </tr>
                            </form>
                        </tbody>
                    </table>

                </c:if>
                <p style="width: 400px; border: 1px solid black">
                    <c:set var="listQuizQuestion" value="${quizDTO.listQuizQuestion}"/>
                    <c:forEach varStatus="counter" var="element" items="${listQuizQuestion}">
                        <span
                            <c:if test="${element.answerId > 0}">
                                style="background-color: greenyellow"
                            </c:if>
                            >${counter.count}</span>
                    </c:forEach>
                </p>
            </c:if>
            <c:if test="${not empty requestScope.ERROR}">
                <h3>${requestScope.ERROR}</h3>
            </c:if>
            <script>
                const countDownInputElement = document.getElementById("countDownTxt");
                const countDownSpanElement = document.getElementById("countDownSpan");
                function secondToMinute(duration) {
                    const minutes = Math.floor(duration / 60);
                    let second = duration % 60;
                    if (second < 10) {
                        second = "0" + second;
                    }
                    return minutes + " : " + second;
                }
                let defaultDuration = countDownInputElement.value;
                function setCountDownElement(duration) {
                    countDownSpanElement.innerHTML = secondToMinute(duration);
                    countDownInputElement.value = duration;
                }
                function countDownFrom() {
                    setCountDownElement(defaultDuration);
                    if (defaultDuration > 0) {
                        setTimeout(function () {
                            defaultDuration -= 1;
                            countDownFrom()
                        }, 1000);
                    } else {
                        document.getElementById("submitBtn").click();
                    }
                }
                countDownFrom();
            </script>


    </body>

</html>
