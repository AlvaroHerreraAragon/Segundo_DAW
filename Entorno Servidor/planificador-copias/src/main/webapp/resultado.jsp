<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Resultado</title>
    </head>
    <body>

        <h1>Configuracion de copia aceptada</h1>

        <p>Nombre del cliente: <c:out value="${cliente}"/></p>
        <br>
        <p>Nombre del plan: <c:out value="${plan}"/></p>
        <br>
        <p>Almacenamiento necesario <c:out value="${resultado.almacenamientoGb}"/> Gb</p>
        <br>
        <p>Tiempo diario: <c:out value="${resultado.minutosDiarios}"/> min</p>
        <br>
        <p>Precio mensual: <c:out value="${resultado.totalMensual}"/> euros</p>
        <br>
        <c:if test="${resultado.minutosDiarios > 60}">
            Reserva una ventana de copia larga
        </c:if>

        <p><a href="index.html">Realizar otro calculo</a></p>
        <br>
        <p><a href="planes">Consultar Planes</a></p>

    </body>
</html>
