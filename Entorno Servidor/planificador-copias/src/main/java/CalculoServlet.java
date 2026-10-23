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
@WebServlet("/calcular")
public class CalculoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String cliente = request.getParameter("cliente");
        String plan = request.getParameter("plan");
        int datosGb = Integer.parseInt(request.getParameter("datosGb"));
        int copiasDiarias = Integer.parseInt(request.getParameter("copiasDiarias"));
        int dias = Integer.parseInt(request.getParameter("dias"));
        String condiciones = request.getParameter("condiciones");
        boolean cifrado = "si".equals(request.getParameter("cifrado"));

        Plan planSeleccionado = null;
        switch (plan) {
            case "basico":
                planSeleccionado = new Plan("basico", "Basico", 250, 2, 8);
                break;
            case "profesional":
                planSeleccionado = new Plan("profesional", "Profesional", 1000, 4, 18);
                break;
            case "empresa":
                planSeleccionado = new Plan("empresa", "Empresa", 250, 2, 8);
                break;
            default:
                throw new AssertionError();
        }

        if (cliente != null && !cliente.trim().equals("")) {
            System.out.println(cliente);
        } else {
            System.out.println("Indica el nombre del cliente");
        }

        if (!condiciones.equals("aceptadas")) {
            System.out.println("Debes aceptar las condiciones");
        }

        if ((datosGb < 1 || datosGb > 500)
                || (copiasDiarias < 1 || copiasDiarias > 3)
                || (dias < 1 || dias > 30)) {
            System.out.println("Los valores estan fuera de los limites");
        }

        double almacenamientoGb = datosGb * copiasDiarias * dias;
        if (almacenamientoGb <= planSeleccionado.getCapacidadGb()) {
            System.out.println("El plan no tiene capacidad suficiente");
        }

        double datosDiarios = datosGb * copiasDiarias;
        double minutosDiarios = datosDiarios / planSeleccionado.getVelocidadGbMin();

        double totalMensual = planSeleccionado.getPrecioMensual();
        if (cifrado) {
            totalMensual = totalMensual + 3;
        }

        ResultadoCopia resultado = new ResultadoCopia(planSeleccionado, almacenamientoGb, 
                minutosDiarios, totalMensual);
        request.setAttribute("plan", planSeleccionado.getNombre());
        request.setAttribute("resultado", resultado);
        request.setAttribute("cliente", cliente);
        
        request.getRequestDispatcher("/resultado.jsp").forward(request, response);
    }

}
