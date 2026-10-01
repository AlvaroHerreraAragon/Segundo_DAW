
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
            HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");

        //Lectura
        String nombre = request.getParameter("nombre");
        String edad = request.getParameter("edad");
        String taller = request.getParameter("taller");
        String turno = request.getParameter("turno");
        String normas = request.getParameter("normas");

        //Validacion Nombre
        if (nombre == null) {
            response.getWriter().println("Introduce un nombre válido");
            return;
        }

        nombre = nombre.trim();

        if (nombre.length() < 2 || nombre.length() > 40) {
            response.getWriter().println("Introduce un nombre válido");
            return;
        }

        if (!nombre.matches("^[\\p{L}][\\p{L} ]*$")) {
            response.getWriter().println("Introduce un nombre válido");
            return;
        }

        //Validacion Franja de edad
        if (!"rango1".equals(edad)
                && !"rango2".equals(edad)
                && !"rango3".equals(edad)) {
            return;
        }

        //Validacion Taller
        if (!"fotografia".equals(taller)
                && !"robotica".equals(taller)
                && !"teatro".equals(taller)) {
            return;
        }

        //Validacion turno
        if (!"manana".equals(turno)
                && !"tarde".equals(turno)) {
            return;
        }

        //Validacion normas
        if (!"aceptadas".equals(normas)) {
            return;
        }

        //Restriccion robotica
        if ("robotica".equals(taller)
                && !"rango2".equals(edad)
                && !"rango3".equals(edad)) {
            return;
        }

        //Restriccion teatro
        if ("teatro".equals(taller)
                && "manana".equals(turno)) {
            return;
        }

    }
}
