<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html><html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Productos</title>
    </head>
    <body>
        <h1>Listado de productos</h1>
    <fmt:setLocale value="es_ES" />
    <c:if test="${empty productos}">
        <p>No hay productos.</p>
    </c:if>
    <table border="1" cellpadding="8">
        <tr>
            <th>ID</th><th>Nombre</th><th>Precio</th
        </tr>
        <c:forEach var="producto" items="${productos}">
            <tr>
                <td><c:out value="${producto.id}" /></td>
            <td><c:out value="${producto.nombre}" /></td>
            <td><fmt:formatNumber value="${producto.precio}"
                                  minFractionDigits="2" maxFractionDigits="2" /></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
