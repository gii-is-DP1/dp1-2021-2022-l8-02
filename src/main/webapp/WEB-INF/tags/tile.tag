<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="currentTile" required="true" rtexprvalue="true" type="org.springframework.samples.endofline.board.Tile" description="Tile to be rendered" %>

<c:if test="${not empty currentTile.card}">
    var image = document.getElementById("<%= currentTile.getCard().getCardName() %>");
    ctx.drawImage(image, ${currentTile.x}*x_step, ${currentTile.y}*y_step, x_step, y_step);
</c:if>