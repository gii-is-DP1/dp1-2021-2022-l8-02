<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageName="Lista de juegos">
    <div class="row" style="text-align: center!important;">
        <p style="font-size:50px; font-style: italic; font-weight: bold;">LISTA DE JUEGOS</p>
    </div>
    <c:forEach items="${games}" var="game">
        	
    <table style="border: solid; border-radius: 10px; margin-bottom: 10px; min-width:800px; max-width: 70%; ">
        <tr>
            <th style="width: 40%;"><p>Nombre del juego: ${game.name} </p>
                <p>Modo del juego: ${game.gameMode}</p>
                <p>Id del juego: ${game.id}</p>
            </th>
           
        </tr>
       
        
    </table>
    </c:forEach>
</eol:layoutEOL>
