<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Catálogo de productos</title>
    </head>
    <body>

        <h1>Catálogo de productos</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Franja</th>
                    <th>Acompañamientos</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="producto" items="${productos}">
                    <tr>
                        <td>
                            <c:out value="${producto.nombre}"/>
                        </td>

                        <td>
                            <c:if test="${producto.soloDesayuno}">
                                Solo desayuno
                            </c:if>

                            <c:if test="${not producto.soloDesayuno}">
                                Desayuno y merienda
                            </c:if>
                        </td>

                        <td>
                            <c:if test="${empty producto.acompanamientos}">
                                Sin acompañamientos
                            </c:if>

                            <c:if test="${not empty producto.acompanamientos}">
                                <ul>
                                    <c:forEach var="acompanamiento"
                                               items="${producto.acompanamientos}">
                                        <li>
                                            <c:out value="${acompanamiento}"/>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <p><a href="index.html">Volver al inicio</a></p>

    </body>
</html>
