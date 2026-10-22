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
@WebServlet("/simulador")
public class SimuladorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/index.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String aula = request.getParameter("aula");

        if (aula != null) {
            aula = aula.trim();
        }

        if (aula == null || aula.length() < 2 || aula.length() > 40) {
            //Aqui va el error
        }

        int dias;
        int presupuesto;
        int cantidadOrdenadores;
        int horasOrdenadores;
        int cantidadMonitores;
        int horasMonitores;
        int cantidadProyectores;
        int horasProyectores;

        try {
            dias = Integer.parseInt(request.getParameter("dias"));
            presupuesto = Integer.parseInt(request.getParameter("presupuesto"));

            cantidadOrdenadores = Integer.parseInt(request.getParameter("cantidadOrdenadores"));
            horasOrdenadores = Integer.parseInt(request.getParameter("horasOrdenadores"));

            cantidadMonitores = Integer.parseInt(request.getParameter("cantidadMonitores"));
            horasMonitores = Integer.parseInt(request.getParameter("horasMonitores"));

            cantidadProyectores = Integer.parseInt(request.getParameter("cantidadProyectores"));
            horasProyectores = Integer.parseInt(request.getParameter("horasProyectores"));

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos los campos deben ser enteros");
            return;
        }

        if (dias < 1 || dias > 31
                || presupuesto < 1 || presupuesto > 1000
                || cantidadOrdenadores < 0 || cantidadOrdenadores > 30
                || horasOrdenadores < 0 || horasOrdenadores > 12
                || cantidadMonitores < 0 || cantidadMonitores > 30
                || horasMonitores < 0 || horasMonitores > 12
                || cantidadProyectores < 0 || cantidadProyectores > 2
                || horasProyectores < 0 || horasProyectores > 12) {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Hay valores fuera de los límites permitidos"
            );
            return;
        }

        if ((cantidadOrdenadores == 0 && horasOrdenadores != 0)
                || (cantidadOrdenadores > 0
                && (horasOrdenadores < 1 || horasOrdenadores > 12))
                || (cantidadMonitores == 0 && horasMonitores != 0)
                || (cantidadMonitores > 0
                && (horasMonitores < 1 || horasMonitores > 12))
                || (cantidadProyectores == 0 && horasProyectores != 0)
                || (cantidadProyectores > 0
                && (horasProyectores < 1 || horasProyectores > 12))) {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Las horas deben ser 0 si no hay equipos y de 1 a 12 si los hay"
            );
            return;
        }

        if (cantidadOrdenadores + cantidadMonitores
                + cantidadProyectores == 0) {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Debe haber al menos un equipo en uso"
            );
            return;
        }

        double consumoOrdenadores = (cantidadOrdenadores * 200 * horasOrdenadores * dias) / 1000.0;
        double consumoMonitores = (cantidadMonitores * 40 * horasMonitores * dias) / 1000.0;
        double consumoProyectores = (cantidadProyectores * 300 * horasProyectores * dias) / 1000.0;

        double consumoTotal = consumoOrdenadores + consumoProyectores + consumoMonitores;

        double tarifaA = 5 + (consumoTotal * 0.20);
        double tarifaB = 0.0;

        if (consumoTotal <= 100) {
            tarifaB = 8 + (consumoTotal * 0.15);
        } else {
            tarifaB = 8 + (100 * 0.15) + ((consumoTotal - 100) * 0.25);
        }

        String recomendacion;
        double diferencia = Math.abs(tarifaA - tarifaB);

        if (diferencia < 0.000001) {
            recomendacion = "Ambas tarifas cuestan lo mismo";
        } else if (tarifaA < tarifaB) {
            recomendacion = "Recomendacion de la Tarifa A";
        } else {
            recomendacion = "Recomendacion de la Tarifa B";
        }

        double ahorro = Math.max(tarifaA, tarifaB) - Math.min(tarifaA, tarifaB);

        double costeMinimo = Math.min(tarifaA, tarifaB);

        double diferenciaPresupuesto = Math.abs(costeMinimo - presupuesto);

        String estadoPresupuesto;
        double diferenciaImporte;

        if (diferenciaPresupuesto < 0.000001) {
            estadoPresupuesto = "Presupuesto exacto";
            diferenciaImporte = 0;
        } else if (costeMinimo < presupuesto) {
            estadoPresupuesto = "Dentro del presupuesto";
            diferenciaImporte = presupuesto - costeMinimo;
        } else {
            estadoPresupuesto = "Presupuesto superado";
            diferenciaImporte = costeMinimo - presupuesto;
        }

        List<ConsumoEquipo> consumos = new ArrayList<>();

        if (cantidadOrdenadores > 0) {
            consumos.add(new ConsumoEquipo("Ordenador de Sobremesa", cantidadOrdenadores, 200, horasOrdenadores, consumoOrdenadores));
        }

        if (cantidadMonitores > 0) {
            consumos.add(new ConsumoEquipo("Monitor", cantidadMonitores, 40, horasMonitores, consumoMonitores));
        }

        if (cantidadProyectores > 0) {
            consumos.add(new ConsumoEquipo("Proyector", cantidadProyectores, 300, horasProyectores, consumoProyectores));
        }

        request.setAttribute("consumos", consumos);
        request.setAttribute("aula", aula);
        request.setAttribute("dias", dias);
        request.setAttribute("presupuesto", presupuesto);
        request.setAttribute("consumoTotal", consumoTotal);
        request.setAttribute("tarifaA", tarifaA);
        request.setAttribute("tarifaB", tarifaB);
        request.setAttribute("recomendacion", recomendacion);
        request.setAttribute("ahorro", ahorro);
        request.setAttribute("estadoPresupuesto", estadoPresupuesto);
        request.setAttribute("diferenciaImporte", diferenciaImporte);

        request.getRequestDispatcher("/resultado.jsp").forward(request, response);
        return;
    }
}
