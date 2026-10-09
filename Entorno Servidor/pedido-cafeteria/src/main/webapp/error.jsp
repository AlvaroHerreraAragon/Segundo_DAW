<%-- 
    Document   : error
    Created on : 6 oct 2026, 10:46:36
    Author     : daw2
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pedido no válido</title>
    </head>
    <body>
        <h1>Pedido no válido</h1>

        <p>
            <c:out value="${mensajeError}" />
        </p>

        <a href="index.html">Volver al formulario</a>
    </body>
</html>

