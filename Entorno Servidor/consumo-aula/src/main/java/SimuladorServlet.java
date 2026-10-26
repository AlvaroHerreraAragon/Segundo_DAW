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
// Indica que el servlet se ejecutará al acceder a la ruta /simulador
@WebServlet("/simulador")
public class SimuladorServlet extends HttpServlet {

    // Gestiona las peticiones GET para mostrar el formulario inicial
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Envía la petición a index.html para mostrar el formulario
        request.getRequestDispatcher("/index.html").forward(request, response);
    }

    // Gestiona las peticiones POST que contienen los datos del formulario
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Establece UTF-8 para recibir correctamente tildes y ñ
        request.setCharacterEncoding("UTF-8");

        // Recoge el nombre del aula enviado desde el formulario
        String aula = request.getParameter("aula");

        // Si se ha recibido el nombre, elimina los espacios exteriores
        if (aula != null) {
            aula = aula.trim();
        }

        // Comprueba que el nombre del aula exista y tenga entre 2 y 40 caracteres
        if (aula == null || aula.length() < 2 || aula.length() > 40) {

            // Envía el mensaje de error a la página de errores
            enviarError(request, response,
                    "El nombre del aula debe tener entre 2 y 40 caracteres.");
            return;
        }

        // Declara las variables que almacenarán los datos numéricos
        int dias;
        int presupuesto;
        int cantidadOrdenadores;
        int horasOrdenadores;
        int cantidadMonitores;
        int horasMonitores;
        int cantidadProyectores;
        int horasProyectores;

        // Intenta convertir los parámetros recibidos en números enteros
        try {

            // Recoge los días de uso y el presupuesto
            dias = Integer.parseInt(request.getParameter("dias"));
            presupuesto = Integer.parseInt(request.getParameter("presupuesto"));

            // Recoge la cantidad de ordenadores y sus horas diarias de uso
            cantidadOrdenadores = Integer.parseInt(
                    request.getParameter("cantidadOrdenadores"));
            horasOrdenadores = Integer.parseInt(
                    request.getParameter("horasOrdenadores"));

            // Recoge la cantidad de monitores y sus horas diarias de uso
            cantidadMonitores = Integer.parseInt(
                    request.getParameter("cantidadMonitores"));
            horasMonitores = Integer.parseInt(
                    request.getParameter("horasMonitores"));

            // Recoge la cantidad de proyectores y sus horas diarias de uso
            cantidadProyectores = Integer.parseInt(
                    request.getParameter("cantidadProyectores"));
            horasProyectores = Integer.parseInt(
                    request.getParameter("horasProyectores"));

        } catch (NumberFormatException e) {

            // Si algún campo no contiene un entero válido, muestra un error
            enviarError(request, response,
                    "Todos los campos numéricos deben ser enteros.");
            return;
        }

        // Comprueba que todos los valores estén dentro de los límites permitidos
        if (dias < 1 || dias > 31
                || presupuesto < 1 || presupuesto > 1000
                || cantidadOrdenadores < 0 || cantidadOrdenadores > 30
                || horasOrdenadores < 0 || horasOrdenadores > 12
                || cantidadMonitores < 0 || cantidadMonitores > 30
                || horasMonitores < 0 || horasMonitores > 12
                || cantidadProyectores < 0 || cantidadProyectores > 2
                || horasProyectores < 0 || horasProyectores > 12) {

            // Si algún valor está fuera de los límites, muestra un error
            enviarError(request, response,
                    "Hay valores fuera de los límites permitidos.");
            return;
        }

        // Comprueba que las horas sean coherentes con la cantidad de equipos
        // Si no hay equipos, las horas deben ser 0
        // Si hay equipos, las horas deben estar entre 1 y 12
        if ((cantidadOrdenadores == 0 && horasOrdenadores != 0)
                || (cantidadOrdenadores > 0
                && (horasOrdenadores < 1 || horasOrdenadores > 12))
                || (cantidadMonitores == 0 && horasMonitores != 0)
                || (cantidadMonitores > 0
                && (horasMonitores < 1 || horasMonitores > 12))
                || (cantidadProyectores == 0 && horasProyectores != 0)
                || (cantidadProyectores > 0
                && (horasProyectores < 1 || horasProyectores > 12))) {

            // Muestra un error si la cantidad de equipos y sus horas no concuerdan
            enviarError(request, response,
                    "Las horas deben ser 0 si no hay equipos y de 1 a 12 si los hay.");
            return;
        }

        // Comprueba que haya al menos un equipo en uso
        if (cantidadOrdenadores + cantidadMonitores
                + cantidadProyectores == 0) {

            // Si todas las cantidades son cero, no se puede realizar el cálculo
            enviarError(request, response,
                    "Debe haber al menos un equipo en uso.");
            return;
        }

        // Calcula el consumo de los ordenadores en kWh
        // Cantidad × potencia en vatios × horas diarias × días / 1000
        double consumoOrdenadores = (cantidadOrdenadores * 200
                * horasOrdenadores * dias) / 1000.0;

        // Calcula el consumo de los monitores en kWh
        double consumoMonitores = (cantidadMonitores * 40
                * horasMonitores * dias) / 1000.0;

        // Calcula el consumo de los proyectores en kWh
        double consumoProyectores = (cantidadProyectores * 300
                * horasProyectores * dias) / 1000.0;

        // Suma los consumos de todos los tipos de equipos
        double consumoTotal = consumoOrdenadores
                + consumoProyectores + consumoMonitores;

        // Calcula el coste de la tarifa A:
        // 5 euros fijos más 0,20 euros por cada kWh consumido
        double tarifaA = 5 + (consumoTotal * 0.20);

        // Inicializa el coste de la tarifa B
        double tarifaB = 0.0;

        // Calcula la tarifa B según el consumo total
        if (consumoTotal <= 100) {

            // Si el consumo no supera los 100 kWh, aplica 0,15 euros por kWh
            // y añade 8 euros de coste fijo
            tarifaB = 8 + (consumoTotal * 0.15);

        } else {

            // Los primeros 100 kWh cuestan 0,15 euros por kWh
            // El consumo que supera los 100 kWh cuesta 0,25 euros por kWh
            tarifaB = 8 + (100 * 0.15) + ((consumoTotal - 100) * 0.25);
        }

        // Declara la variable que almacenará la recomendación de tarifa
        String recomendacion;

        // Calcula la diferencia absoluta entre los costes de ambas tarifas
        double diferencia = Math.abs(tarifaA - tarifaB);

        // Comprueba si las tarifas tienen prácticamente el mismo coste
        // Se utiliza una tolerancia para evitar problemas de precisión decimal
        if (diferencia < 0.000001) {

            recomendacion = "Ambas tarifas cuestan lo mismo";

        } else if (tarifaA < tarifaB) {

            // Recomienda la tarifa A si es más barata
            recomendacion = "Recomendacion de la Tarifa A";

        } else {

            // Si la tarifa B es más barata, recomienda la tarifa B
            recomendacion = "Recomendacion de la Tarifa B";
        }

        // Calcula el ahorro como la diferencia entre la tarifa más cara y la barata
        double ahorro = Math.max(tarifaA, tarifaB)
                - Math.min(tarifaA, tarifaB);

        // Obtiene el coste de la tarifa más económica
        double costeMinimo = Math.min(tarifaA, tarifaB);

        // Calcula la diferencia absoluta entre el coste mínimo y el presupuesto
        double diferenciaPresupuesto = Math.abs(costeMinimo - presupuesto);

        // Declara las variables que describirán el estado del presupuesto
        String estadoPresupuesto;
        double diferenciaImporte;

        // Comprueba si el coste coincide prácticamente con el presupuesto
        if (diferenciaPresupuesto < 0.000001) {

            estadoPresupuesto = "Presupuesto exacto";
            diferenciaImporte = 0;

        } else if (costeMinimo < presupuesto) {

            // Si el coste es inferior al presupuesto, calcula el dinero sobrante
            estadoPresupuesto = "Dentro del presupuesto";
            diferenciaImporte = presupuesto - costeMinimo;

        } else {

            // Si el coste supera el presupuesto, calcula cuánto falta
            estadoPresupuesto = "Presupuesto superado";
            diferenciaImporte = costeMinimo - presupuesto;
        }

        // Crea una lista para almacenar los consumos individuales de cada tipo de equipo
        List<ConsumoEquipo> consumos = new ArrayList<>();

        // Añade los ordenadores a la lista si se ha utilizado alguno
        if (cantidadOrdenadores > 0) {
            consumos.add(new ConsumoEquipo(
                    "Ordenador de Sobremesa",
                    cantidadOrdenadores,
                    200,
                    horasOrdenadores,
                    consumoOrdenadores));
        }

        // Añade los monitores a la lista si se ha utilizado alguno
        if (cantidadMonitores > 0) {
            consumos.add(new ConsumoEquipo(
                    "Monitor",
                    cantidadMonitores,
                    40,
                    horasMonitores,
                    consumoMonitores));
        }

        // Añade los proyectores a la lista si se ha utilizado alguno
        if (cantidadProyectores > 0) {
            consumos.add(new ConsumoEquipo(
                    "Proyector",
                    cantidadProyectores,
                    300,
                    horasProyectores,
                    consumoProyectores));
        }

        // Guarda la lista de consumos para que resultado.jsp pueda recorrerla
        request.setAttribute("consumos", consumos);

        // Guarda el nombre del aula
        request.setAttribute("aula", aula);

        // Guarda el número de días introducido
        request.setAttribute("dias", dias);

        // Guarda el presupuesto del usuario
        request.setAttribute("presupuesto", presupuesto);

        // Guarda el consumo energético total calculado
        request.setAttribute("consumoTotal", consumoTotal);

        // Guarda el coste de la tarifa A
        request.setAttribute("tarifaA", tarifaA);

        // Guarda el coste de la tarifa B
        request.setAttribute("tarifaB", tarifaB);

        // Guarda el texto con la tarifa recomendada
        request.setAttribute("recomendacion", recomendacion);

        // Guarda el ahorro que supone elegir la tarifa más barata
        request.setAttribute("ahorro", ahorro);

        // Guarda el estado del presupuesto
        request.setAttribute("estadoPresupuesto", estadoPresupuesto);

        // Guarda la diferencia entre el presupuesto y el coste mínimo
        request.setAttribute("diferenciaImporte", diferenciaImporte);

        // Transfiere la petición a resultado.jsp para mostrar los resultados
        request.getRequestDispatcher("/resultado.jsp").forward(request, response);

        // No es necesario añadir return después del forward,
        // porque el método termina inmediatamente después
    }

    // Método privado para gestionar los errores de validación
    private void enviarError(HttpServletRequest request,
            HttpServletResponse response, String mensaje)
            throws ServletException, IOException {

        // Establece el código HTTP 400: solicitud incorrecta
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        // Guarda el mensaje para que error.jsp pueda mostrarlo
        request.setAttribute("mensajeError", mensaje);

        // Transfiere la petición a la página que muestra el error
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}
