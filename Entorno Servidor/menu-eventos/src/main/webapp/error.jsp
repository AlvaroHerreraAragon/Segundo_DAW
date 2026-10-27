
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>Se ha producido un error</h1>

        <%-- Muestra el mensaje de error enviado por el servlet --%>
        <p>${mensajeError}</p>

        <%-- Permite volver a la página del catálogo --%>
        <a href="index.html">
            Volver al catálogo
        </a>
    </body>
</html>
