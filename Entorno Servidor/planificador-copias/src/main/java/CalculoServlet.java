
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Indica la URL a la que responderá este servlet
@WebServlet("/calcular")
public class CalculoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Permite recibir correctamente caracteres como tildes y ñ
        request.setCharacterEncoding("UTF-8");

        // Recoge los datos enviados desde el formulario
        String cliente = request.getParameter("cliente");
        String plan = request.getParameter("plan");

        // Recoge los valores numéricos del formulario
        int datosGb = Integer.parseInt(request.getParameter("datosGb"));
        int copiasDiarias = Integer.parseInt(request.getParameter("copiasDiarias"));
        int dias = Integer.parseInt(request.getParameter("dias"));

        // Recoge las condiciones y comprueba si se ha solicitado cifrado
        String condiciones = request.getParameter("condiciones");
        boolean cifrado = "si".equals(request.getParameter("cifrado"));

        // Inicializa el plan seleccionado
        Plan planSeleccionado = null;

        // Selecciona el plan según el código recibido del formulario
        switch (plan) {
            case "basico":
                planSeleccionado = new Plan("basico", "Basico", 250, 2, 8);
                break;

            case "profesional":
                planSeleccionado = new Plan("profesional", "Profesional", 1000, 4, 18);
                break;

            case "empresa":
                planSeleccionado = new Plan("empresa", "Empresa", 3000, 8, 35);
                break;

            default:
                // Si el código del plan no es válido, muestra un error
                enviarError(request, response, "El plan seleccionado no es valido");
                return;
        }

        // Comprueba que el nombre del cliente no esté vacío
        if (cliente == null || cliente.trim().isEmpty()) {
            enviarError(request, response, "Debes añadir el nombre del cliente");
            return;
        }

        // Comprueba que se hayan aceptado las condiciones
        if (!"aceptadas".equals(condiciones)) {
            enviarError(request, response, "Debes aceptar las condiciones");
            return;
        }

        // Comprueba que los valores numéricos estén dentro de los límites
        if (datosGb < 1 || datosGb > 500
                || copiasDiarias < 1 || copiasDiarias > 3
                || dias < 1 || dias > 30) {

            enviarError(request, response, "Los valores estan fuera de los limites");
            return;
        }

        // Calcula el almacenamiento total según los datos introducidos
        double almacenamientoGb = (double) datosGb * copiasDiarias * dias;

        // Comprueba que el plan tenga capacidad suficiente
        if (almacenamientoGb > planSeleccionado.getCapacidadGb()) {
            enviarError(request, response, "El plan no tiene capacidad suficiente");
            return;
        }

        // Calcula la cantidad de datos que se copian diariamente
        double datosDiarios = (double) datosGb * copiasDiarias;

        // Calcula los minutos diarios necesarios para realizar las copias
        double minutosDiarios = datosDiarios / planSeleccionado.getVelocidadGbMin();

        // Obtiene el precio mensual del plan
        double totalMensual = planSeleccionado.getPrecioMensual();

        // Añade 3 euros al mes si se ha solicitado cifrado
        if (cifrado) {
            totalMensual = totalMensual + 3;
        }

        // Crea el objeto con los resultados del cálculo
        ResultadoCopia resultado = new ResultadoCopia(
                planSeleccionado,
                almacenamientoGb,
                minutosDiarios,
                totalMensual
        );

        // Guarda los datos que necesitará la página JSP
        request.setAttribute("plan", planSeleccionado.getNombre());
        request.setAttribute("resultado", resultado);
        request.setAttribute("cliente", cliente);

        // Envía la petición a la página que mostrará los resultados
        request.getRequestDispatcher("/resultado.jsp").forward(request, response);
    }

    // Método privado que muestra un error HTTP 400 al usuario
    private void enviarError(HttpServletRequest request,
            HttpServletResponse response, String mensaje)
            throws ServletException, IOException {

        // Establece el código HTTP 400 (solicitud incorrecta)
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        // Guarda el mensaje para mostrarlo en error.jsp
        request.setAttribute("mensajeError", mensaje);

        // Envía la petición a la página encargada de mostrar el error
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}
