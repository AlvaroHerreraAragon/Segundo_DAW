
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author daw2
 */
@WebServlet("/inscripcion")
public class InscripcionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        // Lectura
        String nombre = request.getParameter("nombre");
        String edad = request.getParameter("edad");
        String taller = request.getParameter("taller");
        String turno = request.getParameter("turno");
        String normas = request.getParameter("normas");

        // Validacion Nombre
        if (nombre == null) {
            //mostrarError(response, "Introduce un nombre válido");
            return;
        }

        nombre = nombre.trim();

        if (nombre.length() < 2 || nombre.length() > 40) {
            //mostrarError(response, "Introduce un nombre válido");
            return;
        }

        if (!nombre.matches("^[\\p{L}][\\p{L} ]*$")) {
            //mostrarError(response, "Introduce un nombre válido");
            return;
        }

        // Validacion Franja de edad
        if (!"rango1".equals(edad)
                && !"rango2".equals(edad)
                && !"rango3".equals(edad)) {

            //mostrarError(response, "Selecciona una franja de edad válida");
            return;
        }

        // Validacion Taller
        if (!"fotografia".equals(taller)
                && !"robotica".equals(taller)
                && !"teatro".equals(taller)) {

            //mostrarError(response, "Selecciona un taller válido");
            return;
        }

        // Validacion turno
        if (!"manana".equals(turno)
                && !"tarde".equals(turno)) {

            //mostrarError(response, "Selecciona un turno válido");
            return;
        }

        // Validacion normas
        if (!"aceptadas".equals(normas)) {
            //mostrarError(response, "Debes aceptar las normas");
            return;
        }

        // Restriccion robotica
        if ("robotica".equals(taller)
                && !"rango2".equals(edad)
                && !"rango3".equals(edad)) {

            //mostrarError(response, "Robótica exige tener al menos 16 años");
            return;
        }

        // Restriccion teatro
        if ("teatro".equals(taller)
                && "manana".equals(turno)) {

            //mostrarError(response, "El taller de teatro solo está por la tarde");
            return;
        }

        // Solicitud aceptada
        response.setContentType("text/html;charset=UTF-8");

        String edadTexto = "";

        switch (edad) {
            case "rango1":
                edadTexto = "De 12 a 13 años";
                break;

            case "rango2":
                edadTexto = "De 14 a 15 años";
                break;

            case "rango3":
                edadTexto = "De 16 a 17 años";
                break;
        }

        String tallerTexto = "";

        switch (taller) {
            case "fotografia":
                tallerTexto = "Fotografía";
                break;

            case "robotica":
                tallerTexto = "Robótica";
                break;

            case "teatro":
                tallerTexto = "Teatro";
                break;
        }

        String turnoTexto = "";

        switch (turno) {
            case "manana":
                turnoTexto = "Mañana";
                break;

            case "tarde":
                turnoTexto = "Tarde";
                break;
        }

        request.setAttribute("nombre", nombre);
        request.setAttribute("edad", edadTexto);
        request.setAttribute("taller", tallerTexto);
        request.setAttribute("turno", turnoTexto);
        
        request.getRequestDispatcher("/resumen.jsp").forward(request, response);

        return;

        /*response.getWriter().println(
                "<!DOCTYPE html>"
                + "<html lang=\"es\">"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Solicitud aceptada</title>"
                + "</head>"
                + "<body>"
                + "<h1>Solicitud aceptada</h1>"
                + "<p>"
                + nombre + ", "
                + edadTexto + ", "
                + tallerTexto + ", "
                + turnoTexto + "."
                + "</p>"
                + "<p>"
                + "Cumples las condiciones para participar en el taller elegido"
                + "</p>"
                + "<a href=\"index.html\">Realizar otra solicitud</a>"
                + "</body>"
                + "</html>"
        );
    }

    // Metodo para mostrar los errores
    private void mostrarError(HttpServletResponse response, String mensaje)
            throws IOException {

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().println(
                "<!DOCTYPE html>"
                + "<html lang=\"es\">"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Solicitud no válida</title>"
                + "</head>"
                + "<body>"
                + "<h1>Solicitud no válida</h1>"
                + "<p>" + mensaje + "</p>"
                + "<a href=\"index.html\">Volver al formulario</a>"
                + "</body>"
                + "</html>"
        );*/
    }
}
