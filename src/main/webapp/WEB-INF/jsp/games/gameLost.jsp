<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageName="gameLost">
    <div class="row" style="text-align: center!important;">
        <p style="font-size:50px; font-style: italic; font-weight: bold;">DERROTA...</p>
    </div>
    <div class="container text-center" >
        <div > 
        <table style="width: 100%;" >
            <tr>
                <th ><h2 class="text-center neon-text">Estadisticas:</h2></th>
                <th >
                    <!-- <c:forEach items="${game.players}" var="player">
                            <p class="text-center">${player.username}</p>
                        
                    </c:forEach> -->
            </tr>
        </table>
    </div>
</eol:layoutEOL>