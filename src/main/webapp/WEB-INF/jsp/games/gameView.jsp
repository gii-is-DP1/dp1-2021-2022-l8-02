<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<eol:layoutEOL pageName="${game.name}">
    <h2>${game.name}</h2>
    <form:form method="POST" modelAttribute="power" class="form-horizontal">
    <eol:input label="Poderes" name="name">
            
            <form:select path="name">
                <form:options items="${powers}"/>
            </form:select>
        </eol:input>
        <button class="neon-button" style="font-size: 20px; background-color: transparent;" type="submit">Usar Poder</button>
    </form:form>
    <div class="row text-center">
        <eol:board board="${board}"></eol:board>
    </div>
<div class="row">
        <eol:hand cards="${hand.cards}"></eol:hand>
</div>

    <!-- <div class="row">
        <a href="/statisticsGame/${game.id}/${user.username}"><button>Statistics</button></a>
    </div> -->
    <div class="row text-center">
        <p>Jugador:   ${statistiscPostGame.user.username}</p>
        <p>Partida:   ${statistiscPostGame.game.name}</p>
        <p>Puntos:    ${statistiscPostGame.point}</p>
        <p>Inciativa de la carta mas usada:     ${statistiscPostGame.maxCard.cardType.iniciative}</p>
    </div> 
</eol:layoutEOL>
