<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageName="lobby">
    <div class="row" style="text-align: center!important;" style="margin-bottom: 30px;">
        <p style="font-size:50px; font-style: italic; font-weight: bold;">END OF LINE</p>
    </div>

    <div class="row" style="text-align: center!important;" style="margin-bottom: 30px;">



        <p style="font-size:25px; font-style: italic; font-weight: bold;">Hola, bienvenid@ de nuevo</p>

    </div>

    <div class="row" style="text-align: center!important;" id = "admin" >
        <a href="/usuarios" class="neon-button-sin-sombra" style= "position:none!important; font-size:25px; width: 80%; height: 100px; margin-bottom: 30px;align-items: center!important;">
            <p style="margin-top: 10px;">Listado de usuarios </p></a>
    </div>
    <div class="row " style="text-align: center!important; " >
        <a href="/games" class="neon-button-sin-sombra" style=" font-size:25px; width: 80%; height: 100px; margin-bottom: 30px;align-items: center!important;">
            <p>Buscar partida</p></a>
    </div>

    <div class="row" style="text-align: center!important;" >
        <a href="games/new" class="neon-button-sin-sombra" style= "position:none!important; font-size:25px; width: 80%; height: 100px; margin-bottom: 30px;align-items: center!important;">
            <p>Crear partida</p></a>
    </div>

    <div class="row" style="text-align: center!important;" >
        <a href="profile" class="neon-button-sin-sombra" style="position:none!important;font-size:25px; width: 80%; height: 100px; margin-bottom: 30px;align-items: center!important;">
            <p>Perfil</p></a>
    </div>

    <script>

        function deleteButtom(){
            const boton = document.getElementById("admin");
            let autorities = "${autorities}";
            console.log(autorities)
            if(!(autorities.includes("admin"))){
                boton.style.display = "none";
            }
    
    
        }
        window.addEventListener("load", deleteButtom);
        
    
    </script>

</eol:layoutEOL>