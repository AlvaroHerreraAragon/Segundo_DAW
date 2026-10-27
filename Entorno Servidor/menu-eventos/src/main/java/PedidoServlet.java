
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
@WebServlet("/pedido")
public class PedidoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        String menaje = request.getParameter("menaje");
        Menu menu = CatalogoMenus.buscarPorCodigo(request.getParameter("menu"));
        String cliente = request.getParameter("cliente");
        int personas = Integer.parseInt(
                request.getParameter("personas"));
        String turno = request.getParameter("turno");
        String servicio = request.getParameter("servicio");
        String zona = request.getParameter("zona");
        String aceptadas = request.getParameter("aceptadas");

        if (cliente == null) {
            enviarError(request, response,
                    "Debes introducir un nombre valido");
            return;
        } else {
            cliente = cliente.trim();

            if (cliente.length() < 2 || cliente.length() > 40
                    || !cliente.matches("^[\\p{L}][\\p{L} ]*$")) {
                enviarError(request, response,
                        "Debes introducir un nombre valido");
                return;
            }
        }

        if (personas < 5 || personas > 60) {
            enviarError(request, response,
                    "Numero de personas incorrecto");
            return;
        }

        if (!servicio.equals("entrega") && zona.equals("centro")) {
            enviarError(request, response,
                    "Zona incorrecta");
            return;
        }

        if (!servicio.equals("entrega") && zona.equals("exterior")) {
            enviarError(request, response,
                    "Zona incorrecta");
            return;
        }

        if (!aceptadas.equals("aceptadas")) {
            enviarError(request, response,
                    "Debes aceptar las condiciones");
            return;
        }

        if (menaje != null && !"si".equals(menaje)) {
            menaje = "La opción de cubiertos no es válida.";
        }

        double importeBase = menu.getPrecioPersona() * personas;

        double calculoMenaje = 0;
        if (menaje.equals("si")) {
            calculoMenaje = personas * 1.5;
        }

        double calculoTransporte = 0;
        if (servicio.equals("entrega") && zona.equals("centro")) {
            calculoTransporte = 12;
        }

        if (servicio.equals("entrega") && zona.equals("exterior")) {
            calculoTransporte = 25;
        }
        
        double subtotalSinIva;
        if(personas >= 5 || personas <=19) {
            subtotalSinIva = importeBase - 0 + calculoMenaje + calculoTransporte;
        } else if(personas >= 20 || personas <=39) {
            subtotalSinIva = importeBase - 5% + calculoMenaje + calculoTransporte;
        } else {
            subtotalSinIva = importeBase - 10% + calculoMenaje + calculoTransporte;
        }
        
        
        
        boolean incluirMenaje = "si".equals(menaje);
        
        request.setAttribute("menaje", menaje);
        request.setAttribute("menu", menu);
        request.setAttribute("cliente", cliente);
        request.setAttribute("personas", personas);
        request.setAttribute("turno", turno);
        request.setAttribute("servicio", servicio);
        request.setAttribute("zona", zona);
        request.setAttribute("aceptadas", aceptadas);

        request.getRequestDispatcher("/resultado.jsp").forward(request, response);
    }

    private void enviarError(HttpServletRequest request,
            HttpServletResponse response, String mensaje)
            throws ServletException, IOException {

        // Establece el código HTTP 400: solicitud incorrecta
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        // Guarda el mensaje para que error.jsp pueda mostrarlo
        request.setAttribute("mensajeError", mensaje);

        // Transfiere la petición a la página que muestra el error
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }

}
