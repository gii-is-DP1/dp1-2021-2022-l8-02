<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="usuarios">
	<h2>Usuarios</h2>
	    <table id="usuariosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 20%;">Username</th>
            <th style="width: 20%;">Password</th>
            <th style="width: 20%;">Email</th>            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usuarios}" var="usuario">
            <tr>
                <td>                    
                    <c:out value="${usuario.username}"/>
                </td>
                <td>
                    <c:out value="${usuario.password}"/>
                </td>                
                <td>
                    <c:out value="${usuario.email}"/>
                </td>  
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout> 