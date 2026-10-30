/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author daw2
 */
public class Database {

    private static final String URL = "jdbc:sqlite:videoteca.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void crearTabla() {
        String sql = """
        CREATE TABLE IF NOT EXISTS pelicula (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre TEXT NOT NULL,
            genero TEXT NOT NULL,
            duracion NUMERIC NOT NULL CHECK(duracion >= 0),
            valoracion DECIMAL NOT NULL CHECK(valoracion BETWEEN 0 AND 10)
        )
        """;

        try (Connection con = conectar(); var st = con.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo crear la tabla", e);
        }

    }

    public static int insertarPelicula(
            String nombre, String genero, double duracion, double valoracion) throws SQLException {
        if (nombre == null || nombre.isBlank()
                || genero == null || genero.isBlank()
                || duracion < 0 || valoracion < 0 || valoracion > 10) {
            throw new IllegalArgumentException("Nombre, Genero, duracion o valoracion no validos");
        }
        String sql = "INSERT INTO pelicula(nombre, genero, duracion, valoracion) VALUES (?, ?, ?, ?)";
        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, genero);
            ps.setDouble(3, duracion);
            ps.setDouble(4, valoracion);
            return ps.executeUpdate();
        }
    }

    public static void listarPeliculas() throws SQLException {
        String sql = "SELECT id, nombre, genero, duracion, valoracion FROM pelicula ORDER BY id";
        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            System.out.println("LISTADO DE PELICULAS\n--------------------");
            int total = 0;
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Genero: " + rs.getString("genero"));
                System.out.println("Duracion: " + rs.getString("duracion") + " min");
                System.out.println("Valoracion: " + rs.getString("valoracion"));
                total++;
            }

            System.out.println("Total: " + total + " peliculas");
        }
    }

    public static void buscarPeliculas(String genero, double duracion, double valoracion) throws SQLException {
        String sql = "SELECT id, nombre, genero, duracion, valoracion FROM pelicula "
                + "WHERE genero = ? AND duracion <= ? AND valoracion >= ?"
                + "ORDER BY valoracion DESC, duracion ASC";
        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, genero);
            ps.setDouble(2, duracion);
            ps.setDouble(3, valoracion);
            ResultSet rs = ps.executeQuery();

            int totalPeliculas = 0;
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Genero: " + rs.getString("genero"));
                System.out.println("Duracion: " + rs.getDouble("duracion") + " min");
                System.out.println("Valoracion: " + rs.getDouble("valoracion"));
                totalPeliculas++;
            }

            if (totalPeliculas > 0) {
                System.out.println("Total: " + totalPeliculas + " peliculas");
            } else {
                System.out.println("No se encontraron peliculas con esos datos");
            }

        }
    }

    public static void buscarPeliculasTitulo(String texto) throws SQLException {
        String sql = "SELECT nombre FROM pelicula WHERE nombre LIKE ?"
                + "ORDER BY nombre ASC";

        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("Texto invalido o vacio");
        }

        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + texto + "%");
            ResultSet rs = ps.executeQuery();

            int totalPeliculasTitulo = 0;
            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString("nombre"));
                totalPeliculasTitulo++;
            }

            if (totalPeliculasTitulo > 0) {
                System.out.println("Total: " + totalPeliculasTitulo + " peliculas");
            } else {
                System.out.println("No se encontraron peliculas con esos datos");
            }
        }
    }
    
    public static void listarPeliculasGenero() throws SQLException {
        String sql = "SELECT genero, AVG(duracion) AS duracion_media"
                + ", AVG(valoracion) AS valoracion_media, COUNT(*) as total_peliculas FROM pelicula"
                + " GROUP BY genero ORDER BY valoracion_media DESC";
        
        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println("Genero: " + rs.getString("genero"));
                System.out.println("Duracion Media: " + rs.getDouble("duracion_media") + " min");
                System.out.println("Valoracion Media: " + rs.getDouble("valoracion_media"));
                System.out.println("Total Peliculas: " + rs.getInt("total_peliculas"));
            }
        }
    }

}
