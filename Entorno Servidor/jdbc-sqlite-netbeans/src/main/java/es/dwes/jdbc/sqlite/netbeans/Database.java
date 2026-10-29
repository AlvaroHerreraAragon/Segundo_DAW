/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.dwes.jdbc.sqlite.netbeans;

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

    private static final String URL = "jdbc:sqlite:tienda.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void crearTabla() {
        String sql = """
        CREATE TABLE IF NOT EXISTS producto (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre TEXT NOT NULL,
            precio NUMERIC NOT NULL CHECK(precio >= 0)
        )
        """;

        try (Connection con = conectar(); var st = con.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo crear la tabla", e);
        }

    }

    public static int insertarProducto(
            String nombre, double precio) throws SQLException {
        if (nombre == null || nombre.isBlank()
                || !Double.isFinite(precio) || precio < 0) {
            throw new IllegalArgumentException("Nombre y precio no válidos");
        }
        String sql = "INSERT INTO producto(nombre, precio) VALUES (?, ?)";
        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            return ps.executeUpdate();
        }
    }

    public static void listarProductos() throws SQLException {
        String sql = "SELECT id, nombre, precio FROM producto ORDER BY id";
        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            System.out.println("LISTADO DE PRODUCTOS\n--------------------");
            int total = 0;
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.printf(java.util.Locale.US,
                        "Precio: %.2f%n%n", rs.getDouble("precio"));
                total++;
            }

            System.out.println("Total: " + total);
        }
    }
}
