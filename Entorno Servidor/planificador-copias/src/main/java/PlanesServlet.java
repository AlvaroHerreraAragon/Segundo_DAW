/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

/**
 *
 * @author daw2
 */
@WebServlet("/planes")
public class PlanesServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Plan> planes = new ArrayList<>();
        planes.add(new Plan("basico", "Basico", 250, 2, 8.0));
        planes.add(new Plan("profesional", "Profesional", 1000, 4, 18.0));
        planes.add(new Plan("empresa", "Empresa", 3000, 8, 35.0));
        
        request.setAttribute("planes", planes);

        request.getRequestDispatcher("/planes.jsp").forward(request, response);
        
        return;
    }
    
}
