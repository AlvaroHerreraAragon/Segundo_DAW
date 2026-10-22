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
       
        
    }

}
