<%-- 
    Document   : createQuestion
    Created on : May 26, 2020, 2:15:52 PM
    Author     : USER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Question</title>
    </head>
    <body>
        <jsp:include page="./header.jsp"/>
        <div class="card container bg-white p-3 mt-4" style="width: 600px">
            <form action="DispatcherController" method="POST" autocomplete="off">
                <h1> Create Question And Answer </h1>



                <table border="0">

                    <tbody>
                        <tr>
                            <td>
                                Choose Subject:
                            </td>
                            <td>
                                <select class="form-control" style="width: 200px" name="subjectId">
                                    <c:forEach var="subject" items="${requestScope.SUBJECT}">
                                        <option value="${subject.id}">
                                            ${subject.name}
                                        </option>
                                    </c:forEach>
                                </select> 
                            </td>
                        </tr>
                        <tr>
                            <td>Question:</td>
                            <td><input class="form-control" style="height: 50px" type="text" name="txtQuestion" value="${param.txtQuestion}" /></br>
                                <c:set var="errors" value="${requestScope.CREATEQUESTION}" ></c:set>
                                <c:if test="${not empty errors.lengthQuestionErr}">
                                    <font color="red">${errors.lengthQuestionErr}</font><br/>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>Answer_A:</td>
                            <td><input class="form-control" type="text" name="txtAnswerA" value="${param.txtAnswerA}" /></br>
                                <c:if test="${not empty errors.lengthAnswerAErr}">
                                    <font color="red">${errors.lengthAnswerAErr}</font><br/>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>Answer_B:</td>
                            <td><input class="form-control" type="text" name="txtAnswerB" value="${param.txtAnswerB}" /></br>
                                <c:if test="${not empty errors.lengthAnswerBErr}">
                                    <font color="red">${errors.lengthAnswerBErr}</font><br/>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>Answer_C:</td>
                            <td><input class="form-control" type="text" name="txtAnswerC" value="${param.txtAnswerC}" /></br>
                                <c:if test="${not empty errors.lengthAnswerCErr}">
                                    <font color="red">${errors.lengthAnswerCErr}</font><br/>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>Answer_D:</td>
                            <td><input class="form-control" type="text" name="txtAnswerD" value="${param.txtAnswerD}" /></br>
                                <c:if test="${not empty errors.lengthAnswerDErr}">
                                    <font color="red">${errors.lengthAnswerDErr}</font><br/>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>Correct Answer:</td>
                            <td>
                                <select class="custom-select" name="txtCorrectAnswer">
                                    <option value="">-- Select Answer --</option>
                                    <option value="A">A</option>
                                    <option value="B">B</option>
                                    <option value="C">C</option>
                                    <option value="D">D</option>
                                </select>

                                <c:if test="${not empty errors.correctAnswerErr}">
                                    <font color="red">${errors.correctAnswerErr}</font><br/>
                                </c:if>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <input class="btn btn-primary" name="btAction" type="submit" value="Add Question"/>
                <input class="btn" type="reset" value="Reset" />
            </form>
        </div>

    </body>
</html>
