<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<%@ attribute name="board" required="false" rtexprvalue="true" type="org.springframework.samples.endofline.board.Board" description="Board to be rendered" %>

<canvas id="board" width="500px" height="500px"></canvas>
<c:forEach items="${colors}" var="color">
    <c:forEach items="${cardTypes}" var="cardType">
        <img id="${color}_${cardType.name}" src="/resources/images/cards/${color}_${cardType.name}.png" style="display:none">
    </c:forEach>
</c:forEach>

<script>

    function loadBoard() {

        var canvas = document.getElementById("board");
        var ctx = canvas.getContext("2d");

        var width = canvas.width;
        var height = canvas.height;
        var size = Math.sqrt(${board.tiles.size()});

        var x_step = width/size;
        var y_step = height/size;

        canvas.addEventListener("click", function(event) {
            var x = Math.floor((event.pageX - canvas.offsetLeft)/x_step);
            var y = Math.floor((event.pageY - canvas.offsetTop)/y_step);
            $('<form action="/games/currentGame" method="POST"><input name="x" value="' + x + '"><input name="y" value="' + y + '"><input name="cardId" value="' + $(".active-card")[0].id + '"><input type="hidden" name="${_csrf.getParameterName()}" value="${_csrf.getToken()}"></form>').appendTo('body').submit();
        });
        
        
        // dibujando el tablero
        ctx.beginPath();
        for(var i=0; i<size+1; i++) {
            ctx.moveTo(x_step*i, 0);
            ctx.lineTo(x_step*i, height);
            ctx.moveTo(0, y_step*i);
            ctx.lineTo(width, y_step*i);
        }
        ctx.stroke();
        ctx.closePath();

        <c:forEach items="${board.tiles}" var="tile">
            <eol:tile currentTile="${tile}"></eol:tile>
        </c:forEach>
    
    }

    window.addEventListener("load", loadBoard);

</script>
