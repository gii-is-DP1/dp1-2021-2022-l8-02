<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<%@ attribute name="pageName" required="true" rtexprvalue="true"
              description="Text for the title of the page" %>

<div class="container-fluid">
    <div class="row">
        <h2 class="text-center">${pageName}</h2>
    </div>
</div>
