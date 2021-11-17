<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="endofline" tagdir="/WEB-INF/tags" %> 

<endofline:layoutEOL pageName="login">
    <form:form modelAttribute="usuario" class="form-horizontal" id="login-form">
        <div class="form-group has-feedback">
            <legend style="text-align: center;">Inicio de sesi&oacute;n</legend>
            <endofline:inputField label="Nombre de Usuario" name="username"/>
            <label for="password" class="col-sm-2 control-label">Contrase&ntilde;a</label>
            <br>
            <form:password class="form-control" path="password"/>           
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Iniciar sesi&oacute;n</button>
            </div>
        </div>
    </form:form>
</endofline:layoutEOL> 
