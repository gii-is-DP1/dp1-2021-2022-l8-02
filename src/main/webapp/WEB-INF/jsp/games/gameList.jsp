<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageTitle="Salas Disponibles">
    <c:forEach items="${games}" var="game">
        
        <div class="container-fluid text-center" style=" text-align: center; align-content: center; ">
            <div class="row " style="border: solid; border-radius: 10px; margin-bottom: 10px; min-width:800px; max-width: 70%; ">
                <div class="col-sm-4" >
                    <img style="max-width: 150px; max-height: 150px;" src="\resources\images\descarga.jpg">
                </div>
                <div class="col-sm-4"   >
                    <p>${game.name} </p>
                    <p>${game.gameMode}</p>
                    <p>${game.getPlayers().size()}</p>
                </div>
                <div class="col-sm-4 " style=" height: 100px;">
                    <div style="align-items:center;">
                        <a href="#"  class="neon-button">Unirse</a>
                    </div>
                </div>
            </div>
    </div>
    </c:forEach>
    <div class="row text-center">
        <a href="/games/new" class="neon-button">Crear Partida</a>
    </div>
</eol:layoutEOL>
