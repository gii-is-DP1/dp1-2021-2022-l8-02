<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>
<%@ attribute name="pageName" required="false" %>
<%@ attribute name="customScript" required="false" fragment="true"%>

<!doctype html>
<html>
<eol:htmlHeaderEOL/>

<body>

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

		<jsp:doBody/>

		</div>
	</div>
	<eol:footer/>
	<jsp:invoke fragment="customScript" />

</body>

</html>
