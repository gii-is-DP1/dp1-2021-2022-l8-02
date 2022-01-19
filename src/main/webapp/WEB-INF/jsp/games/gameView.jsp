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
    <h2>Energ&iacutea: ${energy.counter}</h2>
    <c:if test="${logged == miTurn}">
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
    </c:if>
    <div class="row text-center">
        <eol:board board="${board}"></eol:board>
    </div>
<div class="row">
        <eol:hand cards="${hand.cards}"></eol:hand>
</div>
<div class="row">
    <img id="${dismiss.id}" class="handCard" src="/resources/images/cards/${dismiss.getCardName()}.png">
</div>

    <c:if test="${game.round.number == 1}">
        <form:form method="POST" modelAttribute="hand" action="/games/newHand">
            <div class="row" id = "Hand">
                <button class="neon-button" style="font-size: 20px; background-color: transparent;" type="submit">New Hand</button>
            </div>
        </form:form>
    </c:if>

    <form:form method="POST" modelAttribute="hand" action="/games/DismissCard">
        <div class="row" id = "Hand">
            <button class="neon-button" style="font-size: 20px; background-color: transparent;" type="submit">Dismiss</button>
        </div>
    </form:form>    


</eol:layoutEOL>


