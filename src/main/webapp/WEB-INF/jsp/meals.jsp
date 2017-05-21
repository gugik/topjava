<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/mealsDatatables.js" defer></script>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><spring:message code="meals.title"/></h3>
            <div class="view-box">

                <form id="filter" >
                    <div>
                        <dl>
                            <dt><spring:message code="meals.startDate"/>:</dt>
                            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="meals.endDate"/>:</dt>
                            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
                        </dl>
                    </div>
                    <div>
                        <dl>
                            <dt><spring:message code="meals.startTime"/>:</dt>
                            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="meals.endTime"/>:</dt>
                            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
                        </dl>

                        <div class="panel-footer text-right">
                            <a class="btn btn-danger" type="button" onclick="clearFilter()">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a>
                            <a class="btn btn-primary" type="button" onclick="updateTable()">
                                <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                            </a>
                        </div>

                    </div>
                   <%-- <button type="submit"><spring:message code="meals.filter"/></button>--%>

                </form>

            </div>



            <%--<hr>
            <a href="meals/create"><spring:message code="meals.add"/></a>
            <hr>--%>
            <a class="btn btn-info" onclick="add()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            </a>

            <table class="table table-striped display" id="datatable">
                <thead>
                <tr>
                    <th><spring:message code="meals.dateTime"/></th>
                    <th><spring:message code="meals.description"/></th>
                    <th><spring:message code="meals.calories"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <c:forEach items="${meals}" var="meal">
                    <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                    <tr class="${meal.exceed ? 'exceeded' : 'normal'}" id="${meal.id}">
                        <td>
                                <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                                <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                                ${fn:formatDateTime(meal.dateTime)}
                        </td>
                        <td>${meal.description}</td>
                        <td>${meal.calories}</td>

                        <td><a class="btn btn-xs btn-primary">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                        </a></td>
                        <td><a class="btn btn-xs btn-danger delete">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                        </a></td>

                    </tr>
                </c:forEach>
            </table>

        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>


<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><spring:message code="meals.add"/></h2>
            </div>
            <div class="modal-body">
                <%--<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
                <h3><spring:message code="${meal.isNew() ? 'meals.add' : 'meals.edit'}"/></h3>--%>
                <form class="form-horizontal" id="detailsForm">
                    <input type="hidden" name="id" value="${meal.id}">
                    <dl>
                        <dt><spring:message code="meals.dateTime"/>:</dt>
                        <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime"></dd>
                    </dl>
                    <dl>
                        <dt><spring:message code="meals.description"/>:</dt>
                        <dd><input type="text" value="${meal.description}" size=40 name="description"></dd>
                    </dl>
                    <dl>
                        <dt><spring:message code="meals.calories"/>:</dt>
                        <dd><input type="number" value="${meal.calories}" name="calories"></dd>
                    </dl>
                    <%--<button type="submit"><spring:message code="common.save"/></button>
                    <button onclick="window.history.back()"><spring:message code="common.cancel"/></button>--%>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


</body>
</html>