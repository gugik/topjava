<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>



<section>


    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <h2>My meal</h2>
        </thead>
        <form method="post" action="meals">
            <dl>
                <dt>Date after</dt>
                <dd><input  name="startDate" value="${param.startDate}"></dd>
            </dl>
            <dl>
                <dt>Date before</dt>
                <dd><input  name="endDate" value="${param.endDate}"></dd>
            </dl>
            <dl>
                <dt>Time after</dt>
                <dd><input  name="startTime" value="${param.startTime}"></dd>
            </dl>
            <dl>
                <dt>Time before</dt>
                <dd><input name="endTime" value="${param.endTime}"></dd>
            </dl>
            <button type="submit">Choose</button>
        </form>
    </table>
</section>


<section>
    <h2><a href="index.html">Home</a></h2>
    <h2>Meal list</h2>
    <a href="meals?action=create">Add Meal</a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
