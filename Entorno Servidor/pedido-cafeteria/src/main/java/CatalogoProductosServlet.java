
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/productos")
public class CatalogoProductosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        List<Producto> productos = new ArrayList<>();
        Producto tostada = new Producto("Tostada", true);
        tostada.getAcompanamientos().add("Aceite de oliva");
        tostada.getAcompanamientos().add("Tomate");
        productos.add(tostada);

        Producto bocadillo = new Producto("Bocadillo", false);
        bocadillo.getAcompanamientos().add("Patatas");
        productos.add(bocadillo);

        Producto fruta = new Producto("Fruta", false);
        productos.add(fruta);
        

        request.setAttribute("productos", productos);

        request.getRequestDispatcher("/productos.jsp")
                .forward(request, response);
        return;
    }
}
