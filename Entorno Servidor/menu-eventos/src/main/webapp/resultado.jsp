<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Presupuesto Preparado</title>
    </head>

    <body>
        <p>
            <strong>Aula:</strong>
            <c:out value="${cliente}"/>
        </p>
    </body>
</html>
