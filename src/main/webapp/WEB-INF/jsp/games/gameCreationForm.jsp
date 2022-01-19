<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageName="Crear una partida">

    <div class="row"><a href="/lobby"style="font-size: 20px"  class="neon-button">Atras</a></div>
    <div class="row" style="text-align: center!important;">
        <p style="font-size:50px; font-style: italic; font-weight: bold;">CREAR PARTIDA</p>
    </div>
    
    <form:form method="POST" modelAttribute="game" class="form-horizontal">
        <div class="container">
            <div class="row" style="margin-bottom:20px; text-align: center;">
                <eol:input label="Nombre de la sala" name="name">
                    <form:input path="name" class=""/>
                </eol:input>
            </div>
            <div class="row" style="margin-bottom:20px;text-align: center;">
        <eol:input label="Sala oculta" name="hidden">
            <form:checkbox path="hidden" value="boolean" class=""/>
        </eol:input>
            </div>
            <div class="row" style="margin-bottom:20px;text-align: center;">
        <eol:input label="Modo De Juego" name="gameMode">
            
            <form:select path="gameMode">
                <form:options items="${modes}"/>
            </form:select>
        </eol:input>
            </div>
        </div>
        <div class="text-center" style="text-align: center;" >
            <button class="neon-button" style="font-size: 20px; background-color: transparent;" type="submit">Crear juego</button>
        </div>
    </form:form>
</eol:layoutEOL>
