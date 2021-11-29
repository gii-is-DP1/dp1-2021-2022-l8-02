<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageName="${game.name}">
    <h2><fmt:message key="welcome"/>Statistics</h2>
    
        <div class="row text-center">
            <p>Jugador:   ${statistiscPostGame.user.username}</p>
            <p>Partida:   ${statistiscPostGame.game.name}</p>
            <p>Puntos:    ${statistiscPostGame.point}</p>
            <p>Inciativa de la carta mas usada:     ${statistiscPostGame.maxCard.cardType.iniciative}</p>
        </div>
</eol:layoutEOL>
