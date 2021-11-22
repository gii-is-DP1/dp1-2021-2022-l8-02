<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="endofline" tagdir="/WEB-INF/tags" %>

<endofline:layoutEOL pageName="register">
    <form:form modelAttribute="usuario" class="form-horizontal" id="register-form">
        <div class="form-group has-feedback">
            <legend style="text-align: center;font-size: 100px; margin-top: 5%;">Registro</legend>
            <div class ="row">
            <div style="margin-top: 10%; margin-left: 30%;">
                <h2 style="font-size: 30px;">Nombre de usuario</h2>
            <endofline:inputFieldLogin name="username" style="margin-bottom: 4%;margin-top: 3%;width: 250px; height: 35px; border-radius: 25px; text-align: center;"/>
            <h2 style="font-size: 30px; text-align: center;">Contrase&ntilde;a</h2>
            <label for="password" class="col-sm-2 control-label" style="font-size: 30px; margin-left: 16%;"></label>
            <br>
            <form:password class="form-control" path="password" style="margin-top: 3%;width: 250px; height: 35px;border-radius: 25px;text-align: center;"/>
            <br>
            <h2 style="font-size: 30px;">Repetir contrase&ntilde;a</h2>
            <label for="PasswordRepeat" class="col-sm-2 control-label" style="font-size: 30px; margin-left: 16%;"></label>
            <br>
            <input id="PasswordRepeat" name="passwordRepeat" placeholder="securePassword" type="password" style="width: 250px; height: 35px;border-radius: 25px;text-align: center;"/>
            <h2 style="font-size: 30px; text-align: center;">Email</h2>
            <endofline:inputFieldLogin name="email" style="margin-bottom: 4%;margin-top: 3%;width: 250px; height: 35px; border-radius: 25px; text-align: center;"/>
            </div>            
        </div>
    </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10" style="margin-left: 37%; margin-top: 3.5%;">
                <button class="btn btn-default" type="submit" style="width: 150px; height: 30px; border-radius: 25px;">Registrarse</button>
            </div>
        </div>
    </form:form>
</endofline:layoutEOL> 