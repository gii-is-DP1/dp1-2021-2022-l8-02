<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<%@ attribute name="board" required="false" rtexprvalue="true" type="org.springframework.samples.petclinic.endofline.board.Board" description="Board to be rendered" %>

<canvas id="board" width="200px" height="200px"></canvas>
<img id="red-start" src="/resources/images/cards/red_start.png" style="display:none">

<script>
    var canvas = document.getElementById("board");
    var ctx = canvas.getContext("2d");

    var width = canvas.width;
    var height = canvas.height;
    var size = Math.sqrt(${board.tiles.size()});

    var x_step = width/size;
    var y_step = height/size;
    
    
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

    var image = document.getElementById("red-start");
    ctx.drawImage(image, 2*x_step, 3*y_step, x_step, y_step);

    <c:forEach items="${board.tiles}" var="tile">
        <eol:tile tile="${tile}"></eol:tile>
    </c:forEach>

</script>
