<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Pedido rechazado.</title>
    </head>
    <body>
        <h1>Pedido rechazado.</h1>

        <p>
            <!-- Aquí se pinta automáticamente el texto que le mandamos desde el Servlet -->
            <c:out value="${mensajeError}" />
        </p>

        <a href="index.html">Volver al formulario</a>
    </body>
</html>