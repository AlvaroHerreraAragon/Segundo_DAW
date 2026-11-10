/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daw2
 */
public class Database {

    private static final String RUTA = System.getProperty("user.home") + "\\Desktop\\dwes\\videoteca.db";
    private static final String URL = "jdbc:sqlite:" + RUTA;

    public static Connection conectar() throws SQLException {
        if (!new java.io.File(RUTA).isFile()) {
            throw new SQLException("No existe la base: " + RUTA);
        }
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Falta el driver SQLite", e);
        }
        return DriverManager.getConnection(URL);
    }

    public static List<Pelicula> obtenerPeliculas() throws SQLException {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT id, nombre, duracion FROM pelicula ORDER BY id";
        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Pelicula pelicula = new Pelicula(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("duracion"));
                peliculas.add(pelicula);
            }
        }
        return peliculas;
    }

    public static Pelicula obtenerPeliculaID(int id) throws SQLException {
        String sql = "SELECT id, nombre, duracion FROM pelicula WHERE id = ?";
        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idPelicula = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int duracion = rs.getInt("duracion");

                Pelicula pelicula = new Pelicula(idPelicula, nombre, duracion);
                return pelicula;
            }
        }
        return null;
    }

    public static int actualizarPelicula(int id, String nombre, double duracion)
            throws SQLException {

        if (id <= 0 || nombre == null || nombre.isBlank() || duracion <= 0) {
            throw new IllegalArgumentException("Datos no válidos");
        }

        String sql = "UPDATE pelicula SET nombre = ?, duracion = ? WHERE id = ?";

        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setDouble(2, duracion);
            ps.setInt(3, id);

            return ps.executeUpdate();
        }
    }
    
    public static int borrarPelicula(int id)
            throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("No existe esa pelicula");
        }
        String sql = "DELETE from pelicula WHERE id = ?";
        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }

    }

}
