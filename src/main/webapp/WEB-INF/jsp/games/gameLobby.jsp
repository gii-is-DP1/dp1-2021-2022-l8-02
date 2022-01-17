<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageName="Lobby">
    <div class="row" style="text-align: center!important;">
        <p style="font-size:50px; font-style: italic; font-weight: bold;">MI SALA</p>
    </div>
    <div class="container text-center" >
        <div > 
        <table style="width: 100%;" >
            <tr>
                <th ><h2 class="text-center neon-text">Jugadores:</h2></th>
                <th >
                    <c:forEach items="${game.players}" var="player">
                            <p class="text-center">${player.username}</p>
                        
                    </c:forEach>
            </tr>
        </table>
    </div>
    <div >

        <table style="width: 100%;">
            <th>
                <div class="row">
                    <h2 class="text-center neon-text">Descripci&oacuten:</h2>
                </div>
            </th>
            
            <th>
                    <p class="text-center">Nombre:    ${game.name}</p>                
                    <p class="text-center">Modo:     ${game.gameMode}</p>
                    
                
            </th>
        
        </table>
    </div>
    <div style="text-align: center;">
       <table  style="width: 100%; border-spacing: 20px;" >
           <tr>
               <th style="width: 20%; margin-right: 20%;">
                    <a href="/games/leave" class="neon-button" style="font-size: 25px;">Abandonar partida</a>
               </th>
               <th style="width: 20%; margin-right: 20%;" id = "start">
               
                    <a href="/games/${game.id}/start" class="neon-button" style="font-size: 25px;">Comenzar partida</a>                
        
                </th>
                <th style="width: 20%; margin-right: 20%;">
                    <a href="#" class="neon-button" style="font-size: 25px;">Invitar jugadores</a>
                </th>
           </tr>
       </table>
    </div>

    </div>

    <script>

        function deleteButtom(){
            const boton = document.getElementById("start");
            const logged = "${logged}";
            const creator = "${creator}"; 
            if(!(logged.toUpperCase() === creator.toUpperCase())){
                boton.style.display = "none";
            }
    
    
        }
        window.addEventListener("load", deleteButtom);
    
    
    </script>
</eol:layoutEOL>