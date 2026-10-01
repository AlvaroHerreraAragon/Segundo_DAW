
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Asocia esta clase a la ruta /saludo
@WebServlet("/saludo")
public class SaludoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        // Lee el campo cuyo name es nombre
        String nombre = request.getParameter("nombre");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(
                "<!doctype html><html lang=\"es\"><meta charset=\"UTF-8\">"
                + "<h1>Hola, " + nombre + "</h1></html>");
    }
}
