<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageName="Salas Disponibles">
    <div class="row" style="text-align: center!important;">
        <p style="font-size:50px; font-style: italic; font-weight: bold;">SALAS DISPONIBLES</p>
    </div>
    <c:forEach items="${games}" var="game">
        
        
		
    <table style="border: solid; border-radius: 10px; margin-bottom: 10px; min-width:800px; max-width: 70%; ">
        <tr>
            <th style="width: 20%;"><img style="max-width: 150px; max-height: 150px;" src="\resources\images\descarga.jpg"></th>
            <th style="width: 40%;"><p>${game.name} </p>
                <p>${game.gameMode}</p>
                <p>${game.getPlayers().size()}</p></th>
            <th style="width: 20%;" ><a href="#"style="font-size: 20px"  class="neon-button">Unirse</a></th>
        </tr>
       
        
    </table>
    </c:forEach>
    <!--div class="row text-center">
        <a href="/games/new" class="neon-button">Crear Partida</a>
    </div-->
</eol:layoutEOL>
