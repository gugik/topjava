
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 26.03.2017
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>

<table class="tg" border="1">
    <tr>
        <th width="120">Date</th>
        <th width="120">Description</th>
        <th width="120">Calories</th>

        <th width="60">Update</th>
        <th width="60">Delete</th>

    </tr>

    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>

        <tr  style="color:${meal.exceed?"red":"green"}">
            <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()} </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>

            <td><a href="<c:url value='/meals?id=${meal.id}&action=edit'/>">Update</a></td>
            <td><a href="<c:url value='/meals?id=${meal.id}&action=delete'/>">Delete</a></td>
        </tr>

    </c:forEach>
</table>

<h2>${edit?"Update":"Create"} Meal </h2>

<form method="POST">

    <input name="id" value="${mealForEdit.id}" hidden="hidden"/>
    <label>date
        <input name="date" value="${mealForEdit.dateTime}"/>
    </label>
    <label>description
        <input name="description" value="${mealForEdit.description}"/>
    </label>
    <label>calories
        <input name="calories" value="${mealForEdit.calories}"/>
    </label>

    <input type="submit" value="${edit?"Update":"Create"}"/>
</form>

</body>
</html>
