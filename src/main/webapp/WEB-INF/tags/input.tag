<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of corresponding property in bean object" %>
<%@ attribute name="label" required="true" rtexprvalue="true"
              description="Label appears in red color if input is considered as invalid after submission" %>

<spring:bind path="${name}">
    <div class="form-group text-center">
        <form:label path="${name}" class="form-label">${label}</form:label>
        <jsp:doBody/>
        <c:if test="${status.error}">
            <span class="help-inline">${status.errorMessage}</span>
        </c:if>
    </div>
</spring:bind>
