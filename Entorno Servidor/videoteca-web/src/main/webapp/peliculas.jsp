
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Listado de películas</title>
    </head>

    <body>

        <c:choose>

            <%-- Formulario para editar una película --%>
            <c:when test="${not empty pelicula}">

                <h1>Editar película</h1>

                <form action="${pageContext.request.contextPath}/peliculas" method="POST">

                    <input type="hidden" name="id" value="${pelicula.id}">

                    <label>Título:</label>
                    <input type="text" name="nombre" value="${pelicula.nombre}">

                    <br><br>

                    <label>Duración:</label>
                    <input type="number" step="any" name="duracion" value="${pelicula.duracion}">

                    <br><br>

                    <input type="submit" value="Guardar cambios">

                </form>

                <br>

                <a href="${pageContext.request.contextPath}/peliculas">Cancelar</a>

            </c:when>

            <%-- Listado de películas --%>
            <c:otherwise>

                <h1>Listado de películas</h1>

                <table border="1" cellpadding="8">

                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Duración</th>
                        <th>Editar</th>
                        <th>Borrar</th>
                    </tr>

                    <c:choose>
                        <c:when test="${empty peliculas}">
                            <tr>
                                <td colspan="5">No hay películas.</td>
                            </tr>
                        </c:when>

                        <c:otherwise>
                            <c:forEach var="pelicula" items="${peliculas}">
                                <tr>
                                    <td><c:out value="${pelicula.id}"/></td>
                                    <td><c:out value="${pelicula.nombre}"/></td>
                                    <td><c:out value="${pelicula.duracion}"/></td>

                                    <td>
                                        <a href="${pageContext.request.contextPath}/peliculas?editar=${pelicula.id}">
                                            Editar
                                        </a>
                                    </td>

                                    <td>
                                        <form action="${pageContext.request.contextPath}/peliculas" method="POST"
                                              onsubmit="return confirm('¿Estás seguro de que deseas eliminar esta película?');">

                                            <input type="hidden" name="id" value="${pelicula.id}">
                                            <input type="hidden" name="accion" value="eliminar">

                                            <button type="submit">Eliminar</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>

                </table>

                <br>

                <p>Numero total de peliculas: ${peliculas.size()}</p>

            </c:otherwise>

        </c:choose>

    </body>
</html>
