<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layout pageName="game">
    <h2><fmt:message key="welcome"/></h2>
    <form:form modelAttribute="game" class="form-horizontal">
        <div class="form-group">
            <form:label path="name" class="form-label">Nombre de la sala</form:label>
            <form:input path="name" class="form-control"/>
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
    </form:form>
</eol:layout>
