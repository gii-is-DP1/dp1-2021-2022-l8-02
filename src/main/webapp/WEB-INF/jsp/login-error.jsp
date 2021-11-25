<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="endofline" tagdir="/WEB-INF/tags" %>

<endofline:layoutEOL pageName="loginError">
    <div class="row">
        <p class="neon-button2" style="text-align:center;">End of Line</p>
    </div>
    <div class="row">
        <p class="neon-button2" style="text-align:center;">Los credenciales introducidos son incorrectos, porfavor, redirigase a la p&aacute;gina de inicio de sesi&oacute;n e int&eacute;ntelo de nuevo</p>
        <a href="/login" class="neon-button">Iniciar sesi&oacute;n</a>
    </div>
</endofline:layoutEOL>