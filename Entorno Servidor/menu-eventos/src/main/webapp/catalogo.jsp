<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Catalogo de menus</title>
    </head>
    <body>

        <h1>Menus para tu evento</h1>

        <ul>
            <c:forEach var="menu" items="${menus}">
                <li>Nombre: <c:out value="${menu.nombre}"></c:out></li>

                    <li>Precio por persona: <c:out value="${menu.precioPersona}"></c:out></li>
                <li>Disponibilidad: <c:out value="${menu.disponibleCena}"></c:out></li>

                    <li><c:choose>
                        <c:when test="${menu.disponibleCena == true}" >
                            Comida y cena
                            
                        </c:when>
                        <c:otherwise>
                            Solo comida
                        </c:otherwise>
                    </c:choose>
                </li>
                <br>

            </c:forEach>
        </ul>

        <p><a href="index.html">Preparar pedido</a></p>

    </body>
</html>
