<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="eol" tagdir="/WEB-INF/tags" %>

<eol:layoutEOL pageName="statistics">
    <h2>Estadisticas
    </h2>
    <table id="StatisticsTable" class="table table-striped">
        <thead>
            <tr>
                <th style="width: 25%;">NumberOfGames</th>
                <th style="width: 25%;">NumberOfPlayers</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${statistics}" var="statistic">
                <tr>
                    <td>
                        <c:out value="${statistic.numGames}" />
                    </td>
                    <td>
                        <c:out value="${statistic.numPlayers}" />
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</eol:layoutEOL>