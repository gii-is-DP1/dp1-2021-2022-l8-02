<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<eol:layoutEOL pageName="${game.name}">
    <h2>${game.name}</h2>
    <h2>Es el turno de: ${miTurn}</h2>
    <h2>Energ&iacutea: ${energy.counter} </h2>
    <form:form method="POST" modelAttribute="power" id="poder" class="form-horizontal" action="/games/usePower">
        <eol:input label="Poderes" name="name">
            <form:select path="name">
                <form:options items="${powers}"/>
            </form:select>
        </eol:input>
        <div >
        <button  class="neon-button" style="font-size: 20px; background-color: transparent;" type="submit">Usar Poder</button>
        </div>
    </form:form>
    <div class="row text-center">
        <eol:board board="${board}"></eol:board>
    </div>
<div class="row">
        <eol:hand cards="${hand.cards}"></eol:hand>
</div>


    <form:form method="POST" modelAttribute="hand" action="/games/newHand">
        <div class="row" id = "Hand">
            <button class="neon-button" style="font-size: 20px; background-color: transparent;" type="submit">New Hand</button>
        </div>
    </form:form>

    

    <!-- <div class="row">
        <a href="/statisticsGame/${game.id}/${user.username}"><button>Statistics</button></a>
    </div> -->
    <!--<div class="row text-center">
        <p>Jugador:   ${statistiscPostGame.user.username}</p>
        <p>Partida:   ${statistiscPostGame.game.name}</p>
        <p>Puntos:    ${statistiscPostGame.point}</p>
        <p>Inciativa de la carta mas usada:     ${statistiscPostGame.maxCard.cardType.iniciative}</p>
    </div>--> 

    <script>

        function deleteHand(){
            const boton = document.getElementById("Hand");
            const round= ${game.round.number};
          
            if(round>1){
                boton.style.display = "none";
            }
    
    
        }
        window.addEventListener("load", deleteHand);
        function deletePower(){
            const boton = document.getElementById("poder");
            const usuario= "${logged}";
            const u= "${miTurn}";
            if(u!=usuario){
                boton.style.display = "none";
            }

        }
        window.addEventListener("load", deletePower);
    
    </script>


</eol:layoutEOL>


