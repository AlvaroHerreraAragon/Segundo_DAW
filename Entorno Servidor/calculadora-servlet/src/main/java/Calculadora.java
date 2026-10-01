
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// Asocia esta clase a la ruta /saludo
@WebServlet("/calcular")
public class Calculadora extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");

        String producto = request.getParameter("producto");
        String precioTexto = request.getParameter("precio");
        String cantidadTexto = request.getParameter("cantidad");

        try {
            double precio = Double.parseDouble(precioTexto);
            int cantidad = Integer.parseInt(cantidadTexto);

            double total = precio * cantidad;

            response.setContentType("text/html;charset=UTF-8");

            response.getWriter().println(
                    "<!doctype html>"
                    + "<html lang=\"es\">"
                    + "<head><meta charset=\"UTF-8\"><title>Resultado</title></head>"
                    + "<body>"
                    + "<h1>Resultado</h1>"
                    + "<p>Producto: " + producto + "</p>"
                    + "<p>Precio: " + precio + " €</p>"
                    + "<p>Cantidad: " + cantidad + "</p>"
                    + "<p>Importe total: " + total + " €</p>"
                    + "</body>"
                    + "</html>"
            );

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "El precio o la cantidad no son válidos");
        }
    }
}

