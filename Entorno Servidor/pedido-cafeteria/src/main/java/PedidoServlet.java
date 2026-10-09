
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author daw2
 */
@WebServlet("/inscripcion")
public class PedidoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String producto = request.getParameter("producto");
        String bebida = request.getParameter("bebida");
        String franja = request.getParameter("franja");
        String servicio = request.getParameter("servicio");
        String cubiertos = request.getParameter("cubiertos");

        String mensajeError = null;

        if (nombre == null) {
            mensajeError = "Introduce un nombre válido.";
        } else {
            nombre = nombre.trim();

            if (nombre.length() < 2 || nombre.length() > 40
                    || !nombre.matches("^[\\p{L}][\\p{L} ]*$")) {
                mensajeError = "Introduce un nombre válido.";
            }
        }

        if (mensajeError == null) {
            if (!"tostada".equals(producto)
                    && !"bocadillo".equals(producto)
                    && !"fruta".equals(producto)) {
                mensajeError = "Selecciona un producto válido.";
            }
        }

        if (mensajeError == null) {
            if (!"ninguna".equals(bebida)
                    && !"agua".equals(bebida)
                    && !"zumo".equals(bebida)
                    && !"cafe".equals(bebida)) {
                mensajeError = "Selecciona una bebida válida.";
            }
        }

        if (mensajeError == null) {
            if (!"desayuno".equals(franja)
                    && !"merienda".equals(franja)) {
                mensajeError = "Selecciona una franja válida.";
            }
        }

        if (mensajeError == null) {
            if (!"mesa".equals(servicio)
                    && !"llevar".equals(servicio)) {
                mensajeError = "Selecciona un tipo de servicio válido.";
            }
        }

        if (mensajeError == null) {
            if (cubiertos != null && !"si".equals(cubiertos)) {
                mensajeError = "La opción de cubiertos no es válida.";
            }
        }

        if (mensajeError == null) {
            if ("tostada".equals(producto)
                    && "merienda".equals(franja)) {
                mensajeError = "La tostada solo está disponible para el desayuno.";
            }
        }

        if (mensajeError == null) {
            if ("cafe".equals(bebida)
                    && "merienda".equals(franja)) {
                mensajeError = "El café solo está disponible para el desayuno.";
            }
        }

        if (mensajeError != null) {
            request.setAttribute("mensajeError", mensajeError);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        String productoTexto;

        switch (producto) {
            case "tostada":
                productoTexto = "Tostada";
                break;
            case "bocadillo":
                productoTexto = "Bocadillo";
                break;
            default:
                productoTexto = "Fruta";
                break;
        }

        String bebidaTexto;

        switch (bebida) {
            case "ninguna":
                bebidaTexto = "Sin bebida";
                break;
            case "agua":
                bebidaTexto = "Agua";
                break;
            case "zumo":
                bebidaTexto = "Zumo";
                break;
            default:
                bebidaTexto = "Cafe";
                break;
        }
        String franjaTexto;

        switch (franja) {
            case "desayuno":
                franjaTexto = "Desayuno";
                break;
            case "merienda":
                franjaTexto = "Merienda";
                break;
            default:
                franjaTexto = "";
                break;
        }

        String servicioTexto;

        switch (servicio) {
            case "mesa":
                servicioTexto = "En mesa";
                break;
            case "llevar":
                servicioTexto = "Para llevar";
                break;
            default:
                servicioTexto = "";
                break;
        }

        List<String> acompanamientos = new ArrayList<>();

        switch (producto) {
            case "tostada":
                acompanamientos.add("Aceite");
                acompanamientos.add("Tomate");
                break;

            case "bocadillo":
                acompanamientos.add("Patatas");
                break;

            case "fruta":
                break;
        }

        boolean paraLlevar = "llevar".equals(servicio);
        boolean incluirCubiertos = "si".equals(cubiertos);
        boolean recordatorioCubiertos = "fruta".equals(producto) && !incluirCubiertos;

        request.setAttribute("nombre", nombre);
        request.setAttribute("productoTexto", productoTexto);
        request.setAttribute("bebidaTexto", bebidaTexto);
        request.setAttribute("franjaTexto", franjaTexto);
        request.setAttribute("servicioTexto", servicioTexto);
        request.setAttribute("cubiertos", cubiertos);
        request.setAttribute("paraLlevar", paraLlevar);
        request.setAttribute("incluirCubiertos", incluirCubiertos);
        request.setAttribute("recordatorioCubiertos", recordatorioCubiertos);
        request.setAttribute("acompanamientos", acompanamientos);

        request.getRequestDispatcher("/resumen.jsp").forward(request, response);

    }
}