
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author daw2
 */
@WebServlet("/reserva")
public class ReservaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        String actividad = request.getParameter("actividad");
        String categoria = request.getParameter("categoria");
        String turno = request.getParameter("turno");
        String participantes = request.getParameter("participantes");
        String material = request.getParameter("material");
        String condiciones = request.getParameter("condiciones");

        // Validacion actividad
        if (!"escalada".equals(actividad)
                && !"tirolina".equals(actividad)
                && !"kayak".equals(actividad)) {

            //mostrarError(response, "Selecciona una actividad válida");
            return;
        }

        // Validacion categoria
        if (!"junior".equals(categoria)
                && !"general".equals(categoria)) {

            //mostrarError(response, "Selecciona una categoria válida");
            return;
        }

        //Validacion turno
        if (!"manana".equals(turno)
                && !"tarde".equals(turno)) {

            //mostrarError(response, "Selecciona un turno");
        }

        // Validacion participantes
        if (!"1".equals(participantes)
                && !"2".equals(participantes)
                && !"3".equals(participantes)
                && !"4".equals(participantes)
                && !"5".equals(participantes)
                && !"6".equals(participantes)) {

            //mostrarError(response, "Selecciona un turno válido");
            return;
        }

        //Validacion condiciones
        if (!"aceptadas".equals(condiciones)) {

            //mostrarError(response, "Debes aceptar las condiciones");
        }
        
        //Validacion material
        if ("si".equals(material)
                || !"si".equals(material)) {
            //mostrarError(response, "Debes aceptar las condiciones");
        }

        // Restriccion reserva1
        if ("junior".equals(categoria)
                && "tirolina".equals(actividad)) {

            //mostrarError(response, "La categoria junior no puede reservar Tirolina");
            return;
        }

        // Restriccion reserva2
        if ("kayak".equals(actividad)
                && !"tarde".equals(turno)) {

            //mostrarError(response, "Kayak solo esta disponible por la mañana");
            return;
        }
        
        int importeBase = 0;
        
        if("escalada".equals(actividad)
                && "1".equals(actividad)) {
            
            importeBase = 18 * 1;
        }
        
        if("escalada".equals(actividad)
                && "2".equals(actividad)) {
            
            importeBase = 18 * 2;
        }
        
        if("escalada".equals(actividad)
                && "3".equals(actividad)) {
            
            importeBase = 18 * 3;
        }
        
        if("escalada".equals(actividad)
                && "4".equals(actividad)) {
            
            importeBase = 18 * 4;
        }
        
        if("escalada".equals(actividad)
                && "5".equals(actividad)) {
            
            importeBase = 18 * 5;
        }
        
        if("escalada".equals(actividad)
                && "6".equals(actividad)) {
            
            importeBase = 18 * 6;
        }
        
        if("tirolina".equals(actividad)
                && "1".equals(actividad)) {
            
            importeBase = 24 * 1;
        }
        
        if("tirolina".equals(actividad)
                && "2".equals(actividad)) {
            
            importeBase = 24 * 2;
        }
        
        if("tirolina".equals(actividad)
                && "3".equals(actividad)) {
            
            importeBase = 24 * 3;
        }
        
        if("tirolina".equals(actividad)
                && "4".equals(actividad)) {
            
            importeBase = 24 * 4;
        }
        
        if("tirolina".equals(actividad)
                && "5".equals(actividad)) {
            
            importeBase = 24 * 5;
        }
        
        if("tirolina".equals(actividad)
                && "6".equals(actividad)) {
            
            importeBase = 24 * 6;
        }
        
        if("kayak".equals(actividad)
                && "1".equals(actividad)) {
            
            importeBase = 20 * 1;
        }
        
        if("kayak".equals(actividad)
                && "2".equals(actividad)) {
            
            importeBase = 20 * 2;
        }
        
        if("kayak".equals(actividad)
                && "3".equals(actividad)) {
            
            importeBase = 20 * 3;
        }
        
        if("kayak".equals(actividad)
                && "4".equals(actividad)) {
            
            importeBase = 20 * 4;
        }
        
        if("kayak".equals(actividad)
                && "5".equals(actividad)) {
            
            importeBase = 20 * 5;
        }
        
        if("kayak".equals(actividad)
                && "6".equals(actividad)) {
            
            importeBase = 20 * 6;
        }
        response.setContentType("text/html;charset=UTF-8");

            response.getWriter().println(
                    "<!doctype html>"
                    + "<html lang=\"es\">"
                    + "<head><meta charset=\"UTF-8\"><title>Resultado</title></head>"
                    + "<body>"
                    + "<h1>Resultado</h1>"
                    + "<p>Actividad: " + actividad + "</p>"
                    + "</body>"
                    + "</html>"
            );
        
    }

}
