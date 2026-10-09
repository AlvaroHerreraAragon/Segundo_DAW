<%-- 
    Document   : resumen
    Created on : 6 oct 2026, 10:46:29
    Author     : daw2
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pedido confirmado</title>
    </head>
    <body>

        <h2>Resumen del pedido</h2>

        <p>
            <strong>Nombre:</strong>
            <c:out value="${nombre}" />
        </p>

        <p>
            <strong>Producto:</strong>
            <c:out value="${productoTexto}" />
        </p>

        <p>
            <strong>Bebida:</strong>
            <c:out value="${bebidaTexto}" />
        </p>

        <p>
            <strong>Franja:</strong>
            <c:out value="${franjaTexto}" />
        </p>

        <p>
            <strong>Servicio:</strong>
            <c:out value="${servicioTexto}" />
        </p>


        <%-- Avisos del pedido --%>

        <c:if test="${paraLlevar}">
            <p>Recoge tu pedido en el mostrador</p>
        </c:if>

        <c:if test="${incluirCubiertos}">
            <p>Se incluirán cubiertos en tu pedido</p>
        </c:if>

        <c:if test="${recordatorioCubiertos}">
            <p>Has pedido fruta sin cubiertos; recuerda traer los tuyos</p>
        </c:if>


        <%-- Acompañamientos --%>

        <h2>Acompañamientos incluidos</h2>

        <c:if test="${empty acompanamientos}">
            <p>Este producto no incluye acompañamientos</p>
        </c:if>

        <c:if test="${not empty acompanamientos}">
            <ul>
                <c:forEach items="${acompanamientos}" var="acompanamiento">
                    <li>
                        <c:out value="${acompanamiento}" />
                    </li>
                </c:forEach>
            </ul>
        </c:if>


        <p>
            <a href="index.html">Realizar otro pedido</a>
        </p>

    </body>
</html>

