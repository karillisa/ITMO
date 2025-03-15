<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style/style.css">
    <title>lab2</title>
</head>
<body>
<div id="calculator" style="width: 500px; height: 500px;"></div>
<script src="https://www.desmos.com/api/v1.8/calculator.js?apiKey=dcb31709b452b1cf9dc26972add0fda6"></script>

<script src="script/graph.js"></script>

<c:forEach var="area" items="${applicationScope.last}">
    <script type="text/javascript">
        drawPoint(${area.x}, ${area.y}, ${area.r});
    </script>
</c:forEach>


<table>
    <thead>
    <tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Result</th>
        <th>Executed at</th>
        <th>Execution time</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="area" items="${applicationScope.last}">
        <tr>
            <td>${area.x}</td>
            <td>${area.y}</td>
            <td>${area.r}</td>
            <td>${area.result ? "Попадание" : "Промах"}</td>
            <td>${area.calculatedAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))}</td>
            <td>${area.calculationTime}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<div style="text-align: center;">
    <a href="${pageContext.request.contextPath}">Back Menu</a>
</div>
</body>
</html>
