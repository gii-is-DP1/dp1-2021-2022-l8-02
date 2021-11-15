<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL>
    <h2><fmt:message key="welcome"/></h2>
    <form:form modelAttribute="game" class="form-horizontal">
        <div class="form-group">
            <form:label path="name" class="form-label">Nombre de la sala</form:label>
            <form:input path="name" class="form-control"/>
            <c:if test="${status.error}">
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                <span class="help-inline">${status.errorMessage}</span>
            </c:if>
        </div>

        <div class="form-group">
            <form:label path="hidden" class="form-label">Sala oculta</form:label>
            <form:checkbox path="hidden" value="boolean" class="form-control"/>
        </div>

        <div class="form-group">
            <form:label path="gameMode">Mode De Juego</form:label>
            <form:select path="gameMode">
                <form:options items="${modes}"/>
            </form:select>
        </div>

        <div class="form-group">
            <button class="btn btn-default" type="submit">Crear juego</button>
        </div>
    </form:form>
</eol:layoutEOL>
