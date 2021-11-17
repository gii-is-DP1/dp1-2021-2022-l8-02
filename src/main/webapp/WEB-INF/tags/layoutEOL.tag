<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="endofline" tagdir="/WEB-INF/tags" %>
<%@ attribute name="pageName" required="true" %>
<%@ attribute name="customScript" required="false" fragment="true"%>

<!doctype html>
<html>
<endofline:htmlHeaderEOL/>

<body>
    <jsp:doBody/>
<div class="container-fluid">
    <div class="container xd-container">
    <c:if test="${not empty message}" >
    <div class="alert alert-${not empty messageType ? messageType : 'info'}" role="alert">
            <c:out value="${message}"></c:out>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button> 
    </div>
    </c:if>

    </div>
</div>



<jsp:invoke fragment="customScript" />

</body>

</html>
