<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>


<eol:layoutEOL pageName="Perfil">
            <div class="row"><a href="/lobby"style="font-size: 20px"  class="neon-button">Atras</a></div>
            <div class="" style="text-align: center!important;">
                <p style="font-size:50px; font-style: italic; font-weight: bold;">MI PERFIL</p>
            </div>
            <div class=" align-items-center" style="text-align: center!important; font-size: 20px; margin-bottom: 20px;">
                <div class="col-sm"><p> ${usuario.username}</p></p></div>
                <div class="col-sm"><p> ${usuario.email}</p></p></div>
                <th style="width: 20%;" ><a href="/profile/${usuario.username}/edit"style="font-size: 17px"  class="neon-button">Editar perfil</a></th>

            </div>
            <table style=" margin-bottom: 10px; min-width:1000px; max-width: 90%; ">
                <th style="width:30% ;">
            <div class=" align-items-center scroll text-center" style="text-align: center!important; ">
                <div class="scroll" style="border: solid; border-radius: 20px; height: 500px;
                max-height: 600px;">
                    
                    <p style="font-size:25px;  text-decoration:underline; ">Partidas creadas por mi</p>
                    <c:forEach items="${myGames}" var="game">
                        <table style="border: solid; border-radius: 10px; margin-bottom: 10px; min-width:80%;  margin-left: 10%; margin-right: 10%;">
                            <tr>
                                <th style="width: 40%;">
                                    <p>Nombre: ${game.name} </p>
                                    <p>Modo de juego: ${game.gameMode}</p>
                                    <c:forEach items="${game.getPlayers()}" var="player">
                                        <p>${player}</p>
                                    </c:forEach> 
                                    
                            </tr>
                           
                            
                        </table>
                    </c:forEach>
                </div>
            </th>
            <c:if test="${admin }">
            <th style="width:30% ;">
                <div class="scroll" style="border: solid; border-radius: 20px;  border-spacing: 10px; height: 500px;
                max-height: 600px;">
                    <p style="font-size:25px;  text-decoration:underline;  ">Partidas en curso</p>
                    <c:forEach items="${gameActives}" var="game">
                        <table style="border: solid; border-radius: 10px; margin-bottom: 10px; min-width:80%;  margin-left: 10%; margin-right: 10%;">
                            <tr>
                                <th style="width: 40%;">
                                    <p>Nombre: ${game.name} </p>
                                    <p>Modo de juego: ${game.gameMode}</p>
                                    <p>Participantes:</p>
                                    <c:forEach items="${game.getPlayers()}" var="player">
                                        <p>${player.username}</p>
                                    </c:forEach> 
                                    
                                </th>
                            </tr>
                           
                            
                        </table>
                    </c:forEach>
                </div>
                </th>
                <th style="width:30% ;">
                <div class="scroll" style="border: solid; border-radius: 20px; border-spacing: 10px; height: 500px;
                max-height: 600px;">
                    <p style="font-size:25px;  text-decoration:underline; "> Partidas jugadas</p>
                    <c:forEach items="${allGames}" var="game">
                        <table style="border: solid; border-radius: 10px; margin-bottom: 10px; min-width:80%;  margin-left: 10%; margin-right: 10%;">
                            <tr>
                                <th style="width: 40%;">
                                    <p>Nombre: ${game.name} </p>
                                    <p>Modo de juego: ${game.gameMode}</p>
                                    <p>Participantes:
                                        <c:forEach items="${game.getPlayers()}" var="player">
                                        <p>${player}</p>
                                    </c:forEach> </p>
                                    
                                    
                            </tr>
                           
                            
                        </table>
                    </c:forEach>
                </div>
            </th>
        </c:if>

        </table>






</eol:layoutEOL>