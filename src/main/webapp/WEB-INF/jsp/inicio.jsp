<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="endofline" tagdir="/WEB-INF/tags" %> 

<endofline:layoutEOL pageName="inicio">
    <div class="row">
        <p class="neon-button2" style="text-align:center;">End of Line</p>
    </div>
    <div class="row">
        <a href="/register" class="neon-button" style="font-size: 20px; margin-top: 0%;margin-left: 35%;">Registrarse</a>
    
    </div>
    <div class="row">
            
        <a href="/login" class="neon-button" style="font-size: 20px;margin-top: 10%; margin-left: 33%;">Iniciar sesi&oacute;n</a>
    </div>
           
           
    </div>
</endofline:layoutEOL>
