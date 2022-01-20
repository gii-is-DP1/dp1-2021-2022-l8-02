<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>


<eol:layoutEOL pageName="Perfil">
            <div class="row"><a href="/lobby"style="font-size: 20px"  class="neon-button">Atras</a></div>
            <div class="" style="text-align: center!important;">
                <p style="font-size:50px; font-style: italic; font-weight: bold;">MI PERFIL</p>
            </div>
            <div class=" align-items-center" style="text-align: center!important; font-size: 20px; margin-bottom: 20px;">
                <div class="col-sm"><p> ${usuario.username}</p></p></div>
                <div class="col-sm"><p> ${usuario.email}</p></p></div>
                <th style="width: 20%;" ><a href="/profile/${usuario.username}/edit"style="font-size: 17px"  class="neon-button">Editar perfil</a></th>

            </div>
            <table style=" margin-bottom: 10px; min-width:1000px; max-width: 70%; ">
                <th style="width:30% ;">
            <div class=" align-items-center scroll" style="text-align: center!important; ">
                <div class="scroll" style="border: solid; border-radius: 20px; height: 500px;
                max-height: 600px;">
                    <p style="font-size:25px;  text-decoration:underline; ">Lista de partidas</p>
                    <c:forEach items="${games}" var="game">
                    <table style="border: solid; border-radius: 10px; margin-bottom: 10px; min-width:300px; max-width: 70%; margin-left: 22px;">
                        <tr>
                            <th style="width: 20%;"><img style="max-width: 150px; max-height: 150px;" src="\resources\images\descarga.jpg"></th>
                            <th style="width: 40%;"><p>${game.name} </p>
                                <p>${game.gameMode}</p>
                                <p>${game.getPlayers().size()}</p></th>
                        </tr>
                       
                        
                    </table>
                    </c:forEach>

                </div>
            </th>
            <th style="width:30% ;">
                <div class="scroll" style="border: solid; border-radius: 20px;  border-spacing: 10px; height: 500px;
                max-height: 600px;">
                    <p style="font-size:25px;  text-decoration:underline;  ">Estad&ntilde;isticas</p>
                    <p>sdkjaskd</p>
                    <p>sdkjaskd</p>
                    <p>sdkjaskd</p>
                    <p>sdkjaskd</p>
                </div>
                </th>
                <th style="width:30% ;">
                <div class="scroll" style="border: solid; border-radius: 20px; border-spacing: 10px; height: 500px;
                max-height: 600px;">
                    <p style="font-size:25px;  text-decoration:underline; "> L&oacute;gros</p>
                    <p>sdkjaskd</p><p>sdkjaskd</p><p>sdkjaskd</p>
                    <p>sdkjaskd</p><p>sdkjaskd</p><p>sdkjaskd</p>
                    <p>sdkjaskd</p><p>sdkjaskd</p><p>sdkjaskd</p>
                    <p>sdkjaskd</p><p>sdkjaskd</p><p>sdkjaskd</p>
                    <p>sdkjaskd</p><p>sdkjaskd</p><p>sdkjaskd</p>
                    <p>sdkjaskd</p><p>sdkjaskd</p><p>sdkjaskd</p>
                    <p>sdkjaskd</p><p>sdkjaskd</p><p>sdkjaskd</p>
                    <p>sdkjaskd</p><p>sdkjaskd</p><p>sdkjaskd</p>
                    <p>sdkjaskd</p><p>sdkjaskd</p><p>sdkjaskd</p>
                    <p>sdkjaskd</p><p>sdkjaskd</p><p>sdkjaskd</p>
                </div>
            </th>
          

        </table>






</eol:layoutEOL>