<%@ tag language="java" pageEncoding="UTF-8" dynamic-attributes="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:forEach var="item" items="${map}">
    <c:if test="${item.value == 'true'}">
        <li>${item.key}: ${System.getProperty(item.key)}</li>
    </c:if>
</c:forEach>