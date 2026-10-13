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
        <p>Edad: <c:out value="${edadTexto}"/></p>
        <p>Taller: <c:out value="${tallerTexto}"/></p>
        <p>Turno: <c:out value="${turnoTexto}"/></p>
        <c:if test="${turnoTexto eq 'Tarde'}">
            <p>Tu taller es por la tarde</p>
        </c:if>
        <c:if test="${requiereAutorizacion and turnoTexto eq 'Mañana'}">
            <p>
                Recuerda entregar la autorización familiar.
                <br>
                Revisa la hora de llegada con tu familia.
            </p>
        </c:if>
        <h2>Materiales que debes traer</h2>
        <table border="1px">
            <thead><tr><th>Material</th></tr></thead>
            <tbody>
                <c:forEach items="${materiales}" var="material">
                    <tr><td><c:out value="${material}" />
                            <c:if test="${material eq 'Botella de agua'}">
                                <p>
                                    Llenala antes de salir
                                </p>
                            </c:if>
                        </td></tr>
                    </c:forEach>
            </tbody>
        </table>
        <c:if test="${tallerTexto eq 'Fotografia'}">
            <p>
                Observa la luz antes de hacer la foto
            </p>
        </c:if>

        <p>Taller del objeto:
            <c:out value="${detalleTaller.nombre}" />
        </p>

        <a href="index.html">Volver</a>
    </body>
</html>
