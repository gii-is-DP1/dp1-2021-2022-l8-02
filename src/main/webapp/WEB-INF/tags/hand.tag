<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<%@ attribute name="cards" required="true" rtexprvalue="true" type="java.util.List" description="Cards to be rendered" %>

<c:forEach items="${cards}" var="card">
    <img id="${card.id}" class="handCard" src="/resources/images/cards/${card.getCardName()}.png">
</c:forEach>
<script>
    function load() {
        $(".handCard").click(function(){
            $(".handCard").removeClass("active-card");
            $(this).addClass("active-card");
            // console.log($(this)[0].id);
            // $('<form action="form2.html"></form>').appendTo('body').submit();
        });
    }
    window.onload = load;
</script>