<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="es">
    <head><meta charset="UTF-8"><title>Talleres</title></head>
    <body>
        <h1>Catálogo de talleres</h1>
        <ul>
            <c:forEach items="${talleres}" var="taller">

                <c:out value="${taller.nombre}" />
                — <c:out value="${taller.turno}" />
                — <c:out value="${taller.plazas}" /> plazas
                — <c:if test="${taller.plazas eq 0}">
                    <strong>Completo</strong>
                </c:if>
                <c:if test="${empty talleres}">
                    <p>No hay talleres disponibles.</p>
                </c:if>
                <c:if test="${not empty talleres}">
                    <ul>
                        <%-- Aquí va el c:forEach del paso 11. --%>
                    </ul>
                </c:if>

            </c:forEach>
        </ul>        
        <a href="index.html">Ir a la inscripción</a>
    </body>
</html>
