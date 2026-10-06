
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

@WebServlet("/pedido")
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
        
        if (nombre == null) {
            //mostrarError(response, "Introduce un nombre válido");
            return;
        }
        
        nombre = nombre.trim();
        
        if (nombre.length() < 2 || nombre.length() > 40) {
            return;
        }
        
        if (!nombre.matches("^[\\p{L}][\\p{L} ]*$")) {
            return;
        }
        
        if (!"tostada".equals(producto)
                && !"bocadillo".equals(producto)
                && !"fruta".equals(producto)) {
            return;
        }
        
        if (!"ninguna".equals(bebida)
                && !"agua".equals(bebida)
                && !"zumo".equals(bebida)
                && !"cafe".equals(bebida)) {
            return;
        }
        
        if (!"desayuno".equals(franja)
                && !"merienda".equals(franja)) {
            return;
        }
    }
}
