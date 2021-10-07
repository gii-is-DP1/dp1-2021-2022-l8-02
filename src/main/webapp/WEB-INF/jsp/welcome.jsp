<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <h2><fmt:message key="welcome"/></h2>
    <div class="row">
    <h2>Project ${title}</h2>
    <p><h2>Group ${group}</h2></p>
    <p><ul>
    <c:forEach items="${people}" var="person">
        <li>${person.firstName} &#32; ${person.lastName}</li>
    </c:forEach>
    </ul></p>
    </div>
    <div class="row">
        <div class="col-md-12 d-inline">
            <spring:url value="/resources/images/pets.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive d-inline" src="${petsImage}"/>
            <spring:url value="/resources/images/gratis-png-logotipo-de-lobo-gris-lobo-azul.png" htmlEscape="true" var="logo_equipo_chema"/>
            <img style="height: 20%; width: 20%;" class="img-responsive d-inline" src="${logo_equipo_chema}"/>
        </div>
    </div>
</petclinic:layout>
