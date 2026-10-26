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
// Indica que el servlet se ejecutará al acceder a la ruta /equipos
@WebServlet("/equipos")
public class CatalogoEquiposServlet extends HttpServlet {

    // Sobrescribe doGet para gestionar las peticiones HTTP GET
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Crea una lista para almacenar todos los equipos disponibles
        List<Equipo> equipos = new ArrayList<>();

        // Añade un ordenador con una potencia de 200 vatios
        equipos.add(new Equipo("ordenador", "Ordenador de sobremesa", 200));

        // Añade un monitor con una potencia de 40 vatios
        equipos.add(new Equipo("monitor", "Monitor", 40));

        // Añade un proyector con una potencia de 300 vatios
        equipos.add(new Equipo("proyector", "Proyector", 300));

        // Crea una lista vacía donde se guardarán los equipos filtrados
        List<Equipo> equiposFiltados = new ArrayList<>();

        // Recoge el parámetro potenciaMaxima enviado en la URL o el formulario
        String potenciaMaxima = request.getParameter("potenciaMaxima");

        // Comprueba si no se ha indicado una potencia máxima
        if (potenciaMaxima == null || potenciaMaxima.equals("")) {

            // Si no hay filtro, guarda la lista completa de equipos en la petición
            request.setAttribute("equipos", equipos);

        } else {

            // Convierte el parámetro a entero y comprueba el intervalo indicado
            if (Integer.parseInt(potenciaMaxima) >= 1 || Integer.parseInt(potenciaMaxima) <= 1000) {

                // Recorre todos los equipos de la lista original
                for (Equipo equipo : equipos) {

                    // Comprueba si la potencia del equipo no supera el máximo indicado
                    if (equipo.getPotenciaVatios() <= Integer.parseInt(potenciaMaxima)) {

                        // Añade el equipo a la lista filtrada si cumple la condición
                        equiposFiltados.add(equipo);

                        // Guarda la lista filtrada en la petición para utilizarla en la JSP
                        request.setAttribute("equipos", equiposFiltados);
                    }
                }
            }
        }

        // Transfiere la petición a equipos.jsp para mostrar los equipos
        request.getRequestDispatcher("/equipos.jsp").forward(request, response);

        // Finaliza la ejecución del método doGet
        return;
    }

}
