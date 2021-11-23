<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageTitle="${game.name}">
    <h2><fmt:message key="welcome"/>Statistics</h2>
    
        <div class="row text-center">
            <p>${statistiscPostGame.user.username}</p>
            <p>${statistiscPostGame.game.name}</p>
            <p>${statistiscPostGame.point}</p>
            <p>${statistiscPostGame.maxCard.cardType.iniciative}</p>
        </div>
</eol:layoutEOL>
