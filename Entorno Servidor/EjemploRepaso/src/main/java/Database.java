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
 * @author alvar
 */
public class Database {

    private static final String URL = "jdbc:sqlite:videojuegos.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void crearTabla() {
        String sql = """
        CREATE TABLE IF NOT EXISTS videojuego (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            titulo TEXT NOT NULL,
            plataforma TEXT NOT NULL,
            genero TEXT NOT NULL,
            horas NUMERIC NOT NULL CHECK(horas > 0),
            valoracion DECIMAL NOT NULL CHECK(valoracion BETWEEN 0 AND 10)
        )
        """;

        try (Connection con = conectar(); var st = con.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo crear la tabla", e);
        }
    }
}
