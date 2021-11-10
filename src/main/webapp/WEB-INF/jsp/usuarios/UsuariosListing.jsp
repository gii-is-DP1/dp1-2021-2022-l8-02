<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="usuarios">
    <h2>Usuarios
        <td>
            <a href="/usuarios/new" style="margin-left: 90%;">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            </a>
        </td>
    </h2>
    <table id="usuariosTable" class="table table-striped">
        <thead>
            <tr>
                <th style="width: 25%;">Username</th>
                <th style="width: 25%;">Password</th>
                <th style="width: 25%;">Email</th>
                <th style="width: 12.5%;">Edit</th>
                <th style="width: 12.5%;">Delete</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${usuarios}" var="usuario">
                <tr>
                    <td>
                        <c:out value="${usuario.username}" />
                    </td>
                    <td>
                        <c:out value="${usuario.password}" />
                    </td>
                    <td>
                        <c:out value="${usuario.email}" />
                    </td>
                    <td>
                        <a href="/usuarios/${usuario.username}/edit">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                        </a>
                    </td>
                    <td>
                        <a href="/usuarios/${usuario.username}/delete">
                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</petclinic:layout>