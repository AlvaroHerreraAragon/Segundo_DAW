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
            <c:out value="${detalleProducto.nombre}" />
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

        <c:if test="${bebidaTexto eq 'Sin bebida'}">
            <p>Este pedido no incluye bebida</p>
        </c:if>

        <c:if test="${paraLlevar and recordatorioCubiertos}">
            <p>Antes de coger la fruta, comprueba que llevas cubiertos</p>
        </c:if>

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

        <c:if test="${empty detalleProducto.acompanamientos}">
            <p>Este producto no incluye acompañamientos</p>
        </c:if>

        <c:if test="${not empty detalleProducto.acompanamientos}">
            <ul>
                <c:forEach items="${detalleProducto.acompanamientos}"
                           var="acompanamiento">
                    <li>
                        <c:out value="${acompanamiento}" />
                    </li>
                </c:forEach>
            </ul>
        </c:if>

        <table border="1">
            <thead>
                <tr>
                    <th>Acompañamiento</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${detalleProducto.acompanamientos}"
                           var="acompanamiento">
                    <tr>
                        <td>
                            <c:out value="${acompanamiento}" />

                            <c:if test="${acompanamiento eq 'Aceite'}">
                                <p>Añádelo a tu gusto</p>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <p>
            <a href="index.html">Realizar otro pedido</a>
        </p>

    </body>
</html>
