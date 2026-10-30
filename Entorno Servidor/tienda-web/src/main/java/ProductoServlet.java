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
@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            var productos = Database.obtenerProductos();
            System.out.println("Productos recuperados!" + productos.size());
            request.setAttribute("productos", productos);
            request.getRequestDispatcher("/productos.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            log("Error al consultar productos", e);
            response.sendError(500, "No se pudieron cargar los productos");
        }
    }

}
