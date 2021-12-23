<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="currentTile" required="true" rtexprvalue="true" type="org.springframework.samples.endofline.board.Tile" description="Tile to be rendered" %>

<c:if test="${not empty currentTile.card}">
    var image = document.getElementById("<%= currentTile.getCard().getCardName() %>");
    x = ${currentTile.x} * x_step + x_step/2;
    y = ${currentTile.y} * y_step + y_step/2;
    rotation = 0;
    <c:if test="${not empty currentTile.card.rotation}">
        rotation = <%= currentTile.getCard().getRotation().ordinal() %> * Math.PI/2;
    </c:if>
    ctx.translate(x, y);
    ctx.rotate(rotation);
    ctx.drawImage(image, -x_step / 2, -y_step / 2, x_step, y_step);
    ctx.rotate(-rotation);
    ctx.translate(-x, -y);
</c:if>