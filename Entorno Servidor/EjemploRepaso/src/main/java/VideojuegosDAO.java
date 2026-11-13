
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author alvar
 */
public class VideojuegosDAO {

    public static int insertarVideojuego(Videojuego videojuego) throws SQLException {

        String sql = "INSERT INTO videojuego(titulo, plataforma, genero, horas, valoracion) VALUES (?, ?, ?, ?, ?)";

        if (videojuego == null) {
            throw new IllegalArgumentException("El videojuego no puede ser null");
        }

        if (videojuego.getTitulo() == null || videojuego.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El titulo no puede ser null o no puede tener solo espacios");
        }

        if (videojuego.getPlataforma() == null || videojuego.getPlataforma().isBlank()) {
            throw new IllegalArgumentException("La plataforma no puede ser null o no puede tener solo espacios");
        }

        if (videojuego.getGenero() == null || videojuego.getGenero().isBlank()) {
            throw new IllegalArgumentException("El genero no puede ser null o no puede tener solo espacios");
        }

        if (videojuego.getHoras() <= 0) {
            throw new IllegalArgumentException("Las horas no pueden ser menores o iguales a 0");
        }

        if (videojuego.getValoracion() < 0 || videojuego.getValoracion() > 10) {
            throw new IllegalArgumentException("Las valoraciones deben estar entre 0 y 10");
        }

        try (Connection con = Database.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, videojuego.getTitulo());
            ps.setString(2, videojuego.getPlataforma());
            ps.setString(3, videojuego.getGenero());
            ps.setDouble(4, videojuego.getHoras());
            ps.setDouble(5, videojuego.getValoracion());
            return ps.executeUpdate();
        }

    }

    public static List<Videojuego> listarVideojuegos() throws SQLException {

        List<Videojuego> videojuegos = new ArrayList<>();
        String sql = "SELECT * FROM videojuego";

        try (Connection con = Database.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            int total = 0;
            while (rs.next()) {
                Videojuego videojuego = new Videojuego(rs.getInt("id"), rs.getString("titulo"), rs.getString("plataforma"),
                        rs.getString("genero"), rs.getDouble("horas"), rs.getDouble("valoracion"));

                videojuegos.add(videojuego);
                total++;
            }
            System.out.println("Total de videojuegos:" + total);
        }
        return videojuegos;
    }

    public static Videojuego buscarVideojuego(int id) throws SQLException {

        String sql = "SELECT * FROM videojuego WHERE id = ?";

        try (Connection con = Database.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Videojuego videojuego = new Videojuego(rs.getInt("id"), rs.getString("titulo"), rs.getString("plataforma"),
                        rs.getString("genero"), rs.getDouble("horas"), rs.getDouble("valoracion"));
                return videojuego;
            } else {
                System.out.println("No se ha encontrado ningun videojuego");
                return null;
            }
        }
    }

    public static int actualizarVideojuego(Videojuego videojuego) throws SQLException {

        String sql = "UPDATE videojuego SET titulo = ?, plataforma = ?, genero = ?, horas = ?, valoracion = ? WHERE id = ?";

        if (videojuego == null) {
            throw new IllegalArgumentException("El videojuego no puede ser null");
        }

        if (videojuego.getTitulo() == null || videojuego.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El titulo no puede ser null o no puede tener solo espacios");
        }

        if (videojuego.getPlataforma() == null || videojuego.getPlataforma().isBlank()) {
            throw new IllegalArgumentException("La plataforma no puede ser null o no puede tener solo espacios");
        }

        if (videojuego.getGenero() == null || videojuego.getGenero().isBlank()) {
            throw new IllegalArgumentException("El genero no puede ser null o no puede tener solo espacios");
        }

        if (videojuego.getHoras() <= 0) {
            throw new IllegalArgumentException("Las horas no pueden ser menores o iguales a 0");
        }

        if (videojuego.getValoracion() < 0 || videojuego.getValoracion() > 10) {
            throw new IllegalArgumentException("Las valoraciones deben estar entre 0 y 10");
        }

        try (Connection con = Database.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, videojuego.getTitulo());
            ps.setString(2, videojuego.getPlataforma());
            ps.setString(3, videojuego.getGenero());
            ps.setDouble(4, videojuego.getHoras());
            ps.setDouble(5, videojuego.getValoracion());
            ps.setInt(6, videojuego.getId());
            return ps.executeUpdate();
        }
    }

    public static int eliminarVideojuego(int id) throws SQLException {

        String sql = "DELETE from videojuego WHERE id = ?";

        try (Connection con = Database.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    public static void buscarVideojuegos(String genero, double horasMaximas, double valoracionMinima) throws SQLException {

        String sql = "SELECT * FROM videojuego where genero = ? AND horas <= ? AND valoracion >= ? order by valoracion desc, horas asc;";

        try (Connection con = Database.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, genero);
            ps.setDouble(2, horasMaximas);
            ps.setDouble(3, valoracionMinima);
            ResultSet rs = ps.executeQuery();

            int totalVideojuegos = 0;
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("titulo"));
                System.out.println("Plataforma: " + rs.getString("plataforma"));
                System.out.println("Genero: " + rs.getString("genero"));
                System.out.println("Horas: " + rs.getDouble("horas"));
                System.out.println("Valoracion: " + rs.getDouble("valoracion"));
                totalVideojuegos++;
            }

            if (totalVideojuegos > 0) {
                System.out.println("Total: " + totalVideojuegos + " videojuegos");
            } else {
                System.out.println("No se encontraron videojuegos con esos datos");
            }
        }
    }

    public static void resumenPorGenero() throws SQLException {

        String sql = "SELECT genero, COUNT(*) AS numero_videojuegos, "
                + "AVG(horas) AS media_horas, "
                + "AVG(valoracion) AS media_valoracion "
                + "FROM videojuego "
                + "GROUP BY genero "
                + "HAVING COUNT(*) >= 2 "
                + "ORDER BY media_valoracion DESC";

        try (Connection con = Database.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            int totalGeneros = 0;

            while (rs.next()) {
                System.out.println("Género: " + rs.getString("genero"));
                System.out.println("Número de videojuegos: " + rs.getInt("numero_videojuegos"));
                System.out.println("Media de horas: " + rs.getDouble("media_horas"));
                System.out.println("Media de valoración: " + rs.getDouble("media_valoracion"));

                System.out.println("---------------------------");

                totalGeneros++;
            }

            if (totalGeneros > 0) {
                System.out.println("Total de géneros: " + totalGeneros);
            } else {
                System.out.println("No se encontraron géneros con al menos 2 videojuegos");
            }
        }
    }

}
