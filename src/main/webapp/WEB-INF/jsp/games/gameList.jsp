<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageName="Salas Disponibles">
    <c:forEach items="${games}" var="game">
        
        
            <div class="row " style="border: solid; border-radius: 10px; margin-bottom: 10px; min-width:800px; max-width: 70%; ">
                <div class="col-sm-4" style="width: 30%; margin-left: 0px;" >
                    <img style="max-width: 150px; max-height: 150px;" src="/resources/images/descarga.png">
                </div>
                <div class="col-sm-4" style="width: 30%;margin-left: 0px;"  >
                    <p>${game.name} </p>
                    <p>${game.gameMode}</p>
                    <p>${game.getPlayers().size()}</p>
                </div>
                <div class="col-sm-4" style=" height: 100px; width: 30%; margin-left: 0px;">
                    <div style="align-items:center;width: 50%;">
                        <a href="#"  class="neon-button" style="width: 50%!important;">Unirse</a>
                    </div>
                </div>
            </div>

    </c:forEach>
    <!--div class="row text-center">
        <a href="/games/new" class="neon-button">Crear Partida</a>
    </div-->
</eol:layoutEOL>
