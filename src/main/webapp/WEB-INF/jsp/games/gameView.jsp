<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageName="${game.name}">
    <h2><fmt:message key="welcome"/></h2>
    <div class="row text-center">
        <eol:board board="${board}"></eol:board>
    </div>
    <div class="row">
        <eol:hand cards="${deck.cards}"></eol:hand>
    </div>
    <div class="row">
        <a href="/statisticsGame/${game.id}/${user.username}"><button>Statistics</button></a>
    </div>
</eol:layoutEOL>
