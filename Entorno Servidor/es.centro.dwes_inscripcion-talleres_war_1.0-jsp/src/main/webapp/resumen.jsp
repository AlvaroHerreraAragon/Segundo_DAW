<%-- 
    Document   : resumen
    Created on : 2 oct 2026, 14:22:25
    Author     : daw2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!doctype html>
<html lang="es">
    <head><meta charset="UTF-8"><title>Pedido</title></head>
    <body>
        <h1>Solicitud aprobadisima</h1>
        <p>Nombre: <c:out value="${nombre}"/></p>
        <p>Edad: <c:out value="${edad}"/></p>
        <p>Taller: <c:out value="${taller}"/></p>
        <p>Turno: <c:out value="${turno}"/></p>
        <c:if test="${turno eq 'Tarde'}">
            <p>Tu taller es por la tarde</p>
        </c:if>
        <c:if test="${requiereAutorizacion}">
            <p>
                Recuerda entregar la autorización familiar.
            </p>
        </c:if>
        <h2>Materiales que debes traer</h2>
        <ul>
            <c:forEach items="${materiales}" var="material">
                <li><c:out value="${material}" /></li>
            </c:forEach>
        </ul>
        <a href="index.html">Volver</a>
    </body>
</html>
