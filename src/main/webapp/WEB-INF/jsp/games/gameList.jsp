<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageName="Salas Disponibles">
    <c:forEach items="${games}" var="game">
        <div class="row">
            <h3>${game.name}</h3>
            <a href="/games/join/${game.id}">Unirse</a>
        </div>
    </c:forEach>
    <div class="row text-center">
        <a href="/games/new" class="neon-button">Crear Partida</a>
    </div>
</eol:layoutEOL>
