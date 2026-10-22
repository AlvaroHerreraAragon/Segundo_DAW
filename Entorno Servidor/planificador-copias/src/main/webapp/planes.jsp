<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Planes de copia</title>
    </head>
    <body>

        <h1>Planes de copia</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>Plan</th>
                    <th>Capacidad</th>
                    <th>Velocidad</th>
                    <th>Precio Mensual</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${planes}" var="plan">
                    <tr>
                        <td>
                            <c:out value="${plan.nombre}"/>
                        </td>
                        <td>
                            <c:out value="${plan.capacidadGb}"/>
                        </td>
                        <td>
                            <c:out value="${plan.velocidadGbMin}"/>
                        </td>
                        <td>
                            <c:out value="${plan.precioMensual}"/>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <p><a href="index.html">Configurar una copia</a></p>

    </body>
</html>
