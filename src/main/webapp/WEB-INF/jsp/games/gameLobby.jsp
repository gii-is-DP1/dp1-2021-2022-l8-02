<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageTitle="Lobby">
    <div class="container-fluid neon-border">

    <div class="row">
        <div class="col-md-6">
            <div class="row">
                <h3 class="text-center neon-text">Jugadores</h3>
            </div>
            <c:forEach items="${game.players}" var="player">
                <div class="row">
                    <h4 class="text-center">${player.username}</h4>
                </div>
            </c:forEach>
        </div>
        <div class="col-md-6">
            <div class="row">
                <h3 class="text-center neon-text">Datos</h3>
            </div>
            <div class="row">
                <h4 class="text-center">Nombre: ${game.name}</h4>
            </div>
            <div class="row">
                <h4 class="text-center">Modo: ${game.gameMode}</h4>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 text-center">
            <a href="#" class="neon-button">Abandonar partida</a>
        </div>
        <div class="col-md-4 text-center">
            <a href="/games/${game.id}/start" class="neon-button">Comenzar partida</a>
        </div>
        <div class="col-md-4 text-center">
            <a href="#" class="neon-button">Invitar jugadores</a>
        </div>
    </div>

    </div>
</eol:layoutEOL>