<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageTitle="Crear una partida">
    <form:form method="POST" modelAttribute="game" class="form-horizontal">
        <eol:input label="Nombre de la sala" name="name">
            <form:input path="name" class="form-control"/>
        </eol:input>

        <eol:input label="Sala oculta" name="hidden">
            <form:checkbox path="hidden" value="boolean" class="form-control"/>
        </eol:input>

        <eol:input label="Modo De Juego" name="gameMode">
            <br>
            <form:select path="gameMode">
                <form:options items="${modes}"/>
            </form:select>
        </eol:input>

        <div class="form-group text-center">
            <button class="neon-button" type="submit">Crear juego</button>
        </div>
    </form:form>
</eol:layoutEOL>
