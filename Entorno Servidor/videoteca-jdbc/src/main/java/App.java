
import java.sql.Connection;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        
        //Database.insertarPelicula("Viaje a Marte", "Ciencia Ficcion", 102, 8.2);
        //Database.insertarPelicula("El bosque azul", "Animacion", 88, 7.5);
        //Database.insertarPelicula("Una tarde de verano", "COmedia", 95, 6.8);
        //Database.insertarPelicula("Mision Europa", "Ciencia Ficcion", 102, 8.2);
        //Database.insertarPelicula("Robots en casa", "Ciencia Ficcion", 92, 7.1);
        //Database.insertarPelicula("El último planeta", "Ciencia Ficcion", 110, 8.2);
        //Database.insertarPelicula("La ciudad dormida", "Misterio", 105, 8.6);
        
        Database.listarPeliculas();
        
        Database.buscarPeliculas("Comedia", 110, 8);
        
        Database.buscarPeliculasTitulo("planeta");
        
        Database.listarPeliculasGenero();
    }
}
