
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        List<Taller> talleres = new ArrayList<>();
        talleres.add(new Taller("Robótica", "Tarde", 10, 90));
        talleres.add(new Taller("Fotografía", "Mañana", 3, 60));
        talleres.add(new Taller("Teatro", "Tarde", 0, 120));
        talleres.add(new Taller("Ceramica", "Mañana", 6, 120));

        request.setAttribute("talleres", talleres);
        request.getRequestDispatcher("/catalogo.jsp")
                .forward(request, response);
        return;
    }
}
