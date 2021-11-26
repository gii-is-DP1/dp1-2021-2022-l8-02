<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="endofline" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<endofline:layoutEOL pageName="login">
    <div class="row">
        <p class="neon-button2" style="text-align:center;">End of Line</p>
    </div>
    <form:form modelAttribute="usuario" class="form-horizontal" id="login-form">
        <div class="form-group has-feedback">
            <legend style="text-align: center;font-size: 100px; margin-top: 5%;">Inicio de sesi&oacute;n</legend>
            <div style="margin-top: 20%; margin-left: 30%;">
                <h2 style="font-size: 30px;">Nombre de usuario</h2>
                <endofline:inputFieldLogin name="username" style="margin-bottom: 4%;margin-top: 3%;width: 250px; height: 35px; border-radius: 25px; text-align: center;"/>
                <h2 style="font-size : 30px;">Contrase&ntilde;a</h2>
                <endofline:inputFieldPassword name="password" style="margin-bottom: 4%;margin-top: 3%;width: 250px; height: 35px; border-radius: 25px; text-align: center;"/>
            </div>           
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10" style="margin-left: 37%; margin-top: 3.5%;">
                <button class="neon-button3" type="submit">Iniciar sesi&oacute;n</button>
            </div>
        </div>
    </form:form>
</endofline:layoutEOL> 
