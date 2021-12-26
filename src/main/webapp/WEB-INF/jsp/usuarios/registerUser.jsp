<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="endofline" tagdir="/WEB-INF/tags" %>

<endofline:layoutEOL pageName="register">

        <div class="row" style="text-align: center!important;">
            <p style="font-size:50px; font-style: italic; font-weight: bold;">REGISTRO</p>
        </div>    
        <form:form modelAttribute="usuario" class="form-horizontal" id="register-form">
            <div class="form-group has-feedback" style="text-align: center;">
                <div class ="row">
                        <p style="font-size: 20px; margin-bottom: 0px; margin-top: 5px;">Nombre de usuario</p>
                    <endofline:inputFieldLogin name="username" style="margin-bottom: 4%;margin-top: 3%;width: 250px; height: 35px; border-radius: 25px; text-align: center;"/>
                </div>
                <div class="row">
                    
                    <p style="font-size: 20px; text-align: center;margin-bottom: 0px; margin-top: 5px;">Contrase&ntilde;a</p>
                    <!--label for="password" class="col-sm-2 control-label" style="font-size: 20px; margin-left: 16%;"></label-->
                   
                    <form:password name= "password" class="form-control" path="password" style="margin-bottom: 4%;margin-top: 3%;width: 250px; height: 35px; border-radius: 25px; text-align: center;"/>
                
                </div>
                <div class="row">
                    <p style="font-size: 20px; margin-bottom: 0px; margin-top: 5px;" >Repetir contrase&ntilde;a</p>
                    <!--label for="PasswordRepeat" class="col-sm-2 control-label" style="font-size: 20px; margin-left: 16%;"></label-->
                   
                    <input id="passwordRepeat" name="passwordRepeat" placeholder="securePassword" type="password"style="margin-bottom: 4%;margin-top: 3%;width: 250px; height: 35px; border-radius: 25px; text-align: center;"/>
                </div>
                <div class="row">
                    <p style="font-size: 20px; text-align: center; margin-bottom: 0px; margin-top: 5px;">Email</p>
                    <endofline:inputFieldLogin name="email" style="margin-bottom: 4%;margin-top: 3%;width: 250px; height: 35px; border-radius: 25px; text-align: center;"/>
                </div>            
            </div>
    
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10" style=" text-align: center;">
                <button class="neon-button" type="submit" style="background-color: transparent; font-size: 20px;">Registrarse</button>
            </div>
        </div>
    </form:form>

    
</endofline:layoutEOL> 