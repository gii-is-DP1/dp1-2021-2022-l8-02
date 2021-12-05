<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageName="Uniser a partida">
    <div class="row" style="text-align: center!important;">
        <p style="font-size:30px; font-style: italic; font-weight: bold;">¿QUIERES UNIRTE A LA PARTIDA?</p>
        <p>${joinToAGame.name} </p>
        <p>${joinToAGame.gameMode}</p>
        <p>${joinToAGame.getPlayers().size()}</p></th>
        <button type="submit" style="font-size: 20px" class="neon-button">Unirse</button>
    </div>

</eol:layoutEOL>