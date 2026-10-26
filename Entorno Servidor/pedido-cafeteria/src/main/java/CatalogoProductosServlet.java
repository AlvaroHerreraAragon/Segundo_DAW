
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

// Indica que el servlet se ejecutará al acceder a la ruta /productos
@WebServlet("/productos")
public class CatalogoProductosServlet extends HttpServlet {

    // Sobrescribe el método doGet para procesar peticiones HTTP GET
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Crea una lista vacía para almacenar los productos del catálogo
        List<Producto> productos = new ArrayList<>();

        // Crea un producto llamado Tostada
        // El valor true indica que solo está disponible para el desayuno
        Producto tostada = new Producto("Tostada", true);

        // Añade aceite de oliva a la lista de acompañamientos de la tostada
        tostada.getAcompanamientos().add("Aceite de oliva");

        // Añade tomate a la lista de acompañamientos de la tostada
        tostada.getAcompanamientos().add("Tomate");

        // Añade la tostada, con sus acompañamientos, al catálogo
        productos.add(tostada);

        // Crea un producto llamado Bocadillo
        // El valor false indica que no es exclusivo del desayuno
        Producto bocadillo = new Producto("Bocadillo", false);

        // Añade patatas como acompañamiento del bocadillo
        bocadillo.getAcompanamientos().add("Patatas");

        // Añade el bocadillo al catálogo
        productos.add(bocadillo);

        // Crea un producto llamado Fruta
        // El valor false indica que no es exclusivo del desayuno
        Producto fruta = new Producto("Fruta", false);

        // Añade la fruta al catálogo
        // Este producto no recibe ningún acompañamiento
        productos.add(fruta);

        // Guarda la lista de productos en la petición con el atributo "productos"
        // La página JSP podrá recuperar la lista para mostrar el catálogo
        request.setAttribute("productos", productos);

        // Transfiere el procesamiento a productos.jsp
        // La JSP recibe la misma petición y puede acceder a sus atributos
        request.getRequestDispatcher("/productos.jsp")
                .forward(request, response);

        // Finaliza la ejecución del método doGet
        return;
    }
}
