<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="statistics">
	<h2>Estadisticas</h2>
	    <table id="usuariosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 20%;">Nombre</th>
            <th style="width: 20%;">IDJuego</th>
            <th style="width: 20%;">IDJugador</th> 
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${statistics}" var="statistic">
            <tr>
                <td>                    
                    <c:out value="${statistic.name}"/>
                </td>
                <td>                    
                    <c:out value="${statistic.game.id}"/>
                </td>
                <td>                    
                    <c:out value="${statistic.gamer.id}"/>
                </td>
               
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout> 