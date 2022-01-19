<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageName="gameLost">
    <div class="row" style="text-align: center!important;">
        <c:choose>
            <c:when test = "${userLost}">
                <p style="font-size:50px; font-style: italic; font-weight: bold;">DERROTA...</p>
            </c:when>
            <c:otherwise>
                <p style="font-size:50px; font-style: italic; font-weight: bold;">&iexcl;&iexcl;VICTORIA!!</p>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="container text-center" >
        <div > 
        <table style="width: 100%;" >
            <tr>
                
                <th >
                    <!-- <c:forEach items="${game.players}" var="player">
                            <p class="text-center">${player.username}</p>
                        
                    </c:forEach> -->
                    <div class="row text-center">
                        <p>Puntos:    ${score}</p>
                    </div> 
            </tr>
        </table>
        <a href="/games/leave" class="neon-button" style="font-size: 20px;margin-top: 10%; margin-left: 15%;">Volver al inicio</a>
    </div>
</eol:layoutEOL>