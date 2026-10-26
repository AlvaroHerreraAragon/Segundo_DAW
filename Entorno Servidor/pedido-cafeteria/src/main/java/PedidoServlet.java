
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
// Indica que este servlet se ejecutará al acceder a la ruta /inscripcion
@WebServlet("/inscripcion")
public class PedidoServlet extends HttpServlet {

    // Sobrescribe doPost para procesar los datos enviados por el formulario
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        // Establece la codificación UTF-8 para recibir correctamente tildes y ñ
        request.setCharacterEncoding("UTF-8");

        // Recoge los valores enviados por el formulario mediante sus atributos name
        String nombre = request.getParameter("nombre");
        String producto = request.getParameter("producto");
        String bebida = request.getParameter("bebida");
        String franja = request.getParameter("franja");
        String servicio = request.getParameter("servicio");
        String cubiertos = request.getParameter("cubiertos");

        // Inicializa la variable de error a null, indicando que todavía no hay errores
        String mensajeError = null;

        // Comprueba que se haya enviado el nombre
        if (nombre == null) {
            mensajeError = "Introduce un nombre válido.";
        } else {
            // Elimina los espacios en blanco del principio y del final del nombre
            nombre = nombre.trim();

            // Comprueba que el nombre tenga entre 2 y 40 caracteres
            // y que solo contenga letras y espacios, empezando por una letra
            if (nombre.length() < 2 || nombre.length() > 40
                    || !nombre.matches("^[\\p{L}][\\p{L} ]*$")) {
                mensajeError = "Introduce un nombre válido.";
            }
        }

        // Solo valida el producto si no se ha encontrado un error anterior
        if (mensajeError == null) {
            // Comprueba que el producto recibido sea uno de los permitidos
            if (!"tostada".equals(producto)
                    && !"bocadillo".equals(producto)
                    && !"fruta".equals(producto)) {
                mensajeError = "Selecciona un producto válido.";
            }
        }

        // Comprueba que la bebida seleccionada sea válida
        if (mensajeError == null) {
            if (!"ninguna".equals(bebida)
                    && !"agua".equals(bebida)
                    && !"zumo".equals(bebida)
                    && !"cafe".equals(bebida)) {
                mensajeError = "Selecciona una bebida válida.";
            }
        }

        // Comprueba que la franja horaria sea desayuno o merienda
        if (mensajeError == null) {
            if (!"desayuno".equals(franja)
                    && !"merienda".equals(franja)) {
                mensajeError = "Selecciona una franja válida.";
            }
        }

        // Comprueba que el servicio sea en mesa o para llevar
        if (mensajeError == null) {
            if (!"mesa".equals(servicio)
                    && !"llevar".equals(servicio)) {
                mensajeError = "Selecciona un tipo de servicio válido.";
            }
        }

        // Si se ha enviado el campo de cubiertos, comprueba que su valor sea "si"
        // Si es null, se interpreta como que no se han solicitado cubiertos
        if (mensajeError == null) {
            if (cubiertos != null && !"si".equals(cubiertos)) {
                mensajeError = "La opción de cubiertos no es válida.";
            }
        }

        // Comprueba que la tostada no se solicite para la merienda
        if (mensajeError == null) {
            if ("tostada".equals(producto)
                    && "merienda".equals(franja)) {
                mensajeError = "La tostada solo está disponible para el desayuno.";
            }
        }

        // Comprueba que el café no se solicite para la merienda
        if (mensajeError == null) {
            if ("cafe".equals(bebida)
                    && "merienda".equals(franja)) {
                mensajeError = "El café solo está disponible para el desayuno.";
            }
        }

        // Si existe algún error, prepara la respuesta de error
        if (mensajeError != null) {

            // Guarda el mensaje para que error.jsp pueda mostrarlo
            request.setAttribute("mensajeError", mensajeError);

            // Establece el código HTTP 400: solicitud incorrecta
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            // Envía la petición a la página que muestra el error
            request.getRequestDispatcher("/error.jsp").forward(request, response);

            // Finaliza el método para evitar que se siga procesando el pedido
            return;
        }

        // Declara una variable para guardar el nombre legible del producto
        String productoTexto;

        // Convierte el código recibido del formulario en un nombre para mostrar
        switch (producto) {
            case "tostada":
                productoTexto = "Tostada";
                break;

            case "bocadillo":
                productoTexto = "Bocadillo";
                break;

            // Si no es tostada ni bocadillo, corresponde a fruta
            default:
                productoTexto = "Fruta";
                break;
        }

        // Declara una variable para guardar el nombre legible de la bebida
        String bebidaTexto;

        // Convierte el código de la bebida en un texto para mostrar
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

            // Si no coincide con los casos anteriores, corresponde al café
            default:
                bebidaTexto = "Cafe";
                break;
        }

        // Declara una variable para guardar el nombre legible de la franja
        String franjaTexto;

        // Convierte el código de la franja en un texto para mostrar
        switch (franja) {
            case "desayuno":
                franjaTexto = "Desayuno";
                break;

            case "merienda":
                franjaTexto = "Merienda";
                break;

            // Valor por defecto por si no coincide con ningún caso
            default:
                franjaTexto = "";
                break;
        }

        // Declara una variable para guardar el nombre legible del servicio
        String servicioTexto;

        // Convierte el código del servicio en un texto para mostrar
        switch (servicio) {
            case "mesa":
                servicioTexto = "En mesa";
                break;

            case "llevar":
                servicioTexto = "Para llevar";
                break;

            // Valor por defecto por si no coincide con ningún caso
            default:
                servicioTexto = "";
                break;
        }

        // Crea una lista vacía para almacenar los acompañamientos del producto
        List<String> acompanamientos = new ArrayList<>();

        // Añade a la lista los acompañamientos correspondientes al producto elegido
        switch (producto) {
            case "tostada":
                // La tostada incluye aceite y tomate
                acompanamientos.add("Aceite");
                acompanamientos.add("Tomate");
                break;

            case "bocadillo":
                // El bocadillo incluye patatas
                acompanamientos.add("Patatas");
                break;

            case "fruta":
                // La fruta no tiene acompañamientos adicionales
                break;
        }

        // Guarda true si el pedido es para llevar
        boolean paraLlevar = "llevar".equals(servicio);

        // Guarda true si el usuario ha solicitado cubiertos
        boolean incluirCubiertos = "si".equals(cubiertos);

        // Activa un recordatorio si se pide fruta sin solicitar cubiertos
        boolean recordatorioCubiertos = "fruta".equals(producto) && !incluirCubiertos;

        // Indica si el producto está disponible únicamente para el desayuno
        boolean soloDesayuno = productoTexto.equals("Tostada");

        // Crea un objeto Producto con el nombre y la disponibilidad horaria
        Producto detalleProducto = new Producto(productoTexto, soloDesayuno);

        // Copia los acompañamientos de la lista a la lista interna del producto
        detalleProducto.getAcompanamientos().addAll(acompanamientos);

        // Guarda el nombre del cliente en la petición para utilizarlo en la JSP
        request.setAttribute("nombre", nombre);

        // Guarda el nombre del producto en formato legible
        request.setAttribute("productoTexto", productoTexto);

        // Guarda el nombre de la bebida en formato legible
        request.setAttribute("bebidaTexto", bebidaTexto);

        // Guarda la franja horaria en formato legible
        request.setAttribute("franjaTexto", franjaTexto);

        // Guarda el tipo de servicio en formato legible
        request.setAttribute("servicioTexto", servicioTexto);

        // Guarda el valor original del campo de cubiertos
        request.setAttribute("cubiertos", cubiertos);

        // Guarda si el pedido es para llevar
        request.setAttribute("paraLlevar", paraLlevar);

        // Guarda si se han solicitado cubiertos
        request.setAttribute("incluirCubiertos", incluirCubiertos);

        // Guarda si debe mostrarse el recordatorio de cubiertos
        request.setAttribute("recordatorioCubiertos", recordatorioCubiertos);

        // Guarda la lista de acompañamientos para que la JSP pueda recorrerla
        request.setAttribute("acompanamientos", acompanamientos);

        // Guarda el objeto Producto completo para utilizar sus propiedades en la JSP
        request.setAttribute("detalleProducto", detalleProducto);

        // Transfiere la petición a resumen.jsp para mostrar el resumen del pedido
        request.getRequestDispatcher("/resumen.jsp").forward(request, response);

    }
}
