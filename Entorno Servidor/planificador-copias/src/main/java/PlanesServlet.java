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
// Indica que este servlet se podrá ejecutar accediendo a la ruta /planes
@WebServlet("/planes")
public class PlanesServlet extends HttpServlet {

    // Sobrescribe el método doGet para gestionar peticiones HTTP GET
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Crea una lista vacía donde se guardarán los planes disponibles
        List<Plan> planes = new ArrayList<>();

        // Añade el plan básico a la lista
        // Parámetros: código, nombre, capacidad en GB, velocidad y precio mensual
        planes.add(new Plan("basico", "Basico", 250, 2, 8.0));

        // Añade el plan profesional a la lista
        planes.add(new Plan("profesional", "Profesional", 1000, 4, 18.0));

        // Añade el plan empresa a la lista
        planes.add(new Plan("empresa", "Empresa", 3000, 8, 35.0));

        // Guarda la lista en la petición con el atributo "planes"
        // La página JSP podrá acceder a ella para mostrar los planes
        request.setAttribute("planes", planes);

        // Realiza un forward a planes.jsp
        // La petición y sus atributos se mantienen al cambiar a la JSP
        request.getRequestDispatcher("/planes.jsp").forward(request, response);

        // Finaliza el método doGet
        return;
    }

}
