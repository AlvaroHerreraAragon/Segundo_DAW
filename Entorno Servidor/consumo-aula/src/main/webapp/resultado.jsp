<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Informe de consumo del aula</title>
    </head>

    <body>
        <h1>Informe de consumo del aula</h1>

        <div class="resultado">
            <h2>Datos del aula</h2>

            <p>
                <strong>Aula:</strong>
                <c:out value="${aula}"/>
            </p>

            <p>
                <strong>Días de uso:</strong>
                <c:out value="${dias}"/>
            </p>

            <p>
                <strong>Presupuesto indicado:</strong>
                <c:out value="${presupuesto}"/> €
            </p>
        </div>

        <h2>Consumo por equipo</h2>

        <table border="1">
            <thead>
                <tr>
                    <th>Equipo</th>
                    <th>Cantidad</th>
                    <th>Potencia (W)</th>
                    <th>Horas diarias</th>
                    <th>Consumo (kWh)</th>
                    <th>Aviso</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="consumo" items="${consumos}">
                    <tr>
                        <td>
                            <c:out value="${consumo.nombre}"/>
                        </td>

                        <td>
                            <c:out value="${consumo.cantidad}"/>
                        </td>

                        <td>
                            <c:out value="${consumo.potenciaVatios}"/>
                        </td>

                        <td>
                            <c:out value="${consumo.horasDiarias}"/>
                        </td>

                        <td>
                            <c:out value="${consumo.consumoKwh}"/>
                        </td>

                        <td>
                            <c:if test="${consumo.consumoKwh > 100}">
                                <span class="aviso">Consumo elevado</span>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="resultado">
            <h2>Resumen del consumo</h2>

            <p>
                <strong>Consumo total:</strong>
                <c:out value="${consumoTotal}"/> kWh
            </p>

            <p>
                <strong>Coste de la tarifa A:</strong>
                <c:out value="${tarifaA}"/> €
            </p>

            <p>
                <strong>Coste de la tarifa B:</strong>
                <c:out value="${tarifaB}"/> €
            </p>

            <p>
                <strong>Recomendación:</strong>
                <c:out value="${recomendacion}"/>
            </p>

            <p>
                <strong>Ahorro entre tarifas:</strong>
                <c:out value="${ahorro}"/> €
            </p>
        </div>

        <div class="resultado">
            <h2>Estado del presupuesto</h2>

            <p>
                <strong>
                    <c:out value="${estadoPresupuesto}"/>
                </strong>
            </p>

            <c:choose>
                <c:when test="${estadoPresupuesto == 'Dentro del presupuesto'}">
                    <p>
                        Importe que sobra:
                        <c:out value="${diferenciaImporte}"/> €
                    </p>
                </c:when>

                <c:when test="${estadoPresupuesto == 'Presupuesto superado'}">
                    <p>
                        Importe que falta:
                        <c:out value="${diferenciaImporte}"/> €
                    </p>
                </c:when>

                <c:when test="${estadoPresupuesto == 'Presupuesto exacto'}">
                    <p>El coste coincide exactamente con el presupuesto.</p>
                </c:when>
            </c:choose>
        </div>

        <div class="enlaces">
            <a href="${pageContext.request.contextPath}/simulador">
                Realizar otra simulación
            </a>
                <br>

            <a href="${pageContext.request.contextPath}/equipos">
                Consultar equipos
            </a>
        </div>
    </body>
</html>
