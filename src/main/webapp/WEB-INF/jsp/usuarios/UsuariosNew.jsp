<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="usuarios">
    <h2>
        <c:if test="${usuarios['new']}">Crear o editar usuario</c:if>
    </h2>
    <form:form modelAttribute="usuario" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Username" name="username"/>
            <petclinic:inputField label="Password" name="password"/>
            <petclinic:inputField label="Email" name="email"/>            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
               
            <button class="btn btn-default" type="submit">Anadir Usuario</button>
                   
            </div>
        </div>
    </form:form>
</petclinic:layout> 