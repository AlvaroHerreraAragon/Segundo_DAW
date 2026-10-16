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
@WebServlet("/equipos")
public class CatalogoEquiposServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        List<Equipo> equipos = new ArrayList<>();
        equipos.add(new Equipo("ordenador", "Ordenador de sobremesa", 200));
        equipos.add(new Equipo("monitor", "Monitor", 40));
        equipos.add(new Equipo("proyector", "Proyector", 300));

        List<Equipo> equiposFiltados = new ArrayList<>();
        
        String potenciaMaxima = request.getParameter("potenciaMaxima");
        
        if(potenciaMaxima == null || potenciaMaxima.equals("")) {
            request.setAttribute("equipos", equipos);
        } else {
            if(Integer.parseInt(potenciaMaxima) >= 1 || Integer.parseInt(potenciaMaxima) <= 1000) {
                for (Equipo equipo : equipos) {
                    if(equipo.getPotenciaVatios() <= Integer.parseInt(potenciaMaxima)) {
                        equiposFiltados.add(equipo);
                        request.setAttribute("equipos", equiposFiltados);
                    }
                }
            }
        }
        
        request.getRequestDispatcher("/equipos.jsp").forward(request, response);

        return;
    }

}
