
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author daw2
 */
@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Menu> menus = CatalogoMenus.obtenerMenus();
        
        request.setAttribute("menus", menus);
        
        request.getRequestDispatcher("/catalogo.jsp").forward(request, response);
    }

}
