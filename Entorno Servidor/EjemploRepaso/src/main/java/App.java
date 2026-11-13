
import java.sql.Connection;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alvar
 */
public class App {
    
    public static void main(String[] args) throws SQLException {
        try (Connection con = Database.conectar()) {
            System.out.println("Conexión correcta");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Database.crearTabla();
        
        
    }
    
    
}
