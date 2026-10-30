/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.dwes.jdbc.sqlite.netbeans;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author daw2
 */
public class App {

    public static void main(String[] args) throws SQLException {
        try (Connection con = Database.conectar()) {
            System.out.println("Conexión correcta");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Database.crearTabla();
        
        //Database.insertarProducto("Teclado", 29.99);

        /*try {
            int filas = Database.insertarProducto("PS5", 580);
            System.out.println("Filas insertadas: " + filas);
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        }*/

        /*try {
            Database.listarProductos();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        
        /*try {
            int filas = Database.actualizarProducto(10, 100);
            System.out.println("Filas actualizadas: " + filas);
            Database.listarProductos();
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        }*/
        
        try{
            int filas = Database.borrarProducto(9);
            Database.listarProductos();
            System.out.println("Filas borradas: " + filas);
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
