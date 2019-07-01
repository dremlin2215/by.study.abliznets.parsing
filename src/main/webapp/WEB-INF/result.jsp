<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<%@page import="beans.ObligatoryDevice"%>--%>
<%--<%@page import="beans.NonobligatoryDevice"%>--%>

<%--<jsp:useBean id="ObligatoryDevice" class="beans.ObligatoryDevice"/>--%>
<%--<jsp:useBean id="NonobligatoryDevice" class="beans.NonobligatoryDevice"/>--%>

<html>
<head>
    <title>Result set</title>
</head>

<body>
<h1 align="center">Results of parsing</h1>
<p></p>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Company</th>
        <th>Shipment date</th>
        <th>Price</th>
        <th>Cooler included</th>
        <th>Ports</th>
        <th>Energy consumption</th>
        <%--<th>IsObligatory</th>--%>
    </tr>
    <c:forEach items="${devices}" var="device">
        <tr>
            <td>${device.id}</td>
            <td>${device.name}</td>
            <td>${device.shipment.company}</td>
            <td>${device.shipment.date}</td>
            <td>${device.shipment.price}</td>
            <td>${device.hasCooler}</td>
            <td>${device.ports}</td>
            <td>${device.energyConsumption}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
