<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Tabla de equipos</title>
    </head>
    <body>

        <h1>Tabla de equipos</h1>

        <c:if test="${not empty equipos}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Nombre</th>
                        <th>Potencia(W)</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="equipo" items="${equipos}">
                        <tr>
                            <td>
                                <c:out value="${equipo.codigo}"/>
                            </td>
                            <td>
                                <c:out value="${equipo.nombre}"/>
                            </td>
                            <td>
                                <c:out value="${equipo.potenciaVatios}"/>
                                <c:if test="${equipo.potenciaVatios ge 250}">
                                    Potencia alta
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            <form action="equipos" method="GET">
                <label>Potencia maxima</label>
                <input type="text" id="potenciaMaxima" name="potenciaMaxima">
                <input type="submit" value="Filtrar">
            </form>
            
        </c:if>
        <c:if test="${empty equipos}">
            No hay equipos disponibles
        </c:if>

        <p><a href="index.html">Volver al inicio</a></p>

    </body>
</html>
