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
            String titulo, String genero, double duracion, double valoracion) throws SQLException {
        if (titulo == null || titulo.isBlank()
                || genero == null || genero.isBlank()
                || duracion < 0 || valoracion < 0 || valoracion > 10) {
            throw new IllegalArgumentException("Titulo, Genero, duracion o valoracion no validos");
        }
        String sql = "INSERT INTO pelicula(titulo, genero, duracion, valoracion) VALUES (?, ?, ?, ?)";
        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, titulo);
            ps.setString(2, genero);
            ps.setDouble(3, duracion);
            ps.setDouble(4, valoracion);
            return ps.executeUpdate();
        }
    }

}
