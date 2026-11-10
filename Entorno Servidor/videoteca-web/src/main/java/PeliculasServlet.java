/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author daw2
 */
@WebServlet("/peliculas")
public class PeliculasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            var peliculas = Database.obtenerPeliculas();
            String editar = request.getParameter("editar");

            if (editar != null) {
                int id = Integer.parseInt(editar);
                Pelicula pelicula = Database.obtenerPeliculaID(id);
                request.setAttribute("pelicula", pelicula);
                request.getRequestDispatcher("peliculas.jsp").forward(request, response);
                return;
            }

            request.setAttribute("peliculas", peliculas);
            request.getRequestDispatcher("peliculas.jsp").forward(request, response);
        } catch (SQLException e) {
            log("Error al consultar peliculas", e);
            response.sendError(500, "No se pudieron cargar las peliculas");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        try {

            String accion = request.getParameter("accion");

            // Comprobamos si queremos eliminar una película
            if ("eliminar".equals(accion)) {

                int id = Integer.parseInt(request.getParameter("id"));

                int resultado = Database.borrarPelicula(id);

                if (resultado == 0) {
                    response.sendError(404, "No existe esa pelicula");
                    return;
                }

                response.sendRedirect("peliculas");
                return;
            }

            // Recogemos los datos de la película
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            double duracion = Double.parseDouble(request.getParameter("duracion"));

            // Comprobamos que los datos sean correctos
            if (nombre == null || nombre.isBlank() || duracion <= 0) {
                response.sendError(400, "El título y la duración no son válidos");
                return;
            }

            // Actualizamos la película
            Database.actualizarPelicula(id, nombre, duracion);

            // Volvemos al listado
            response.sendRedirect("peliculas");

        } catch (SQLException e) {
            log("Error al modificar o eliminar pelicula", e);
            response.sendError(500, "No se pudo modificar o eliminar la pelicula");

        } catch (NumberFormatException e) {
            response.sendError(400, "El ID y la duración deben ser números");
        }
    }

}
