<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageTitle="Salas Disponibles">
    <c:forEach items="${games}" var="game">
        <div class="row">
            <h3>${game.name}</h3>
            <a href="games/${game.id}">Unirse</a>
        </div>
    </c:forEach>
</eol:layoutEOL>
