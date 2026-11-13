
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

        try {
            // Comprobar la conexion
            Database.conectar().close();
            System.out.println("Conexion establecida correctamente.");

            // Crear la tabla
            Database.crearTabla();

            // Insertar videojuegos
            Videojuego v1 = new Videojuego(0, "Minecraft", "PC", "Sandbox", 120.0, 9.5);
            Videojuego v2 = new Videojuego(0, "Terraria", "PC", "Sandbox", 80.0, 9.0);
            Videojuego v3 = new Videojuego(0, "FIFA 25", "PS5", "Deportes", 50.0, 8.0);
            Videojuego v4 = new Videojuego(0, "NBA 2K25", "PS5", "Deportes", 70.0, 8.5);
            Videojuego v5 = new Videojuego(0, "God of War", "PS5", "Aventura", 35.0, 9.8);

            System.out.println("\n--- INSERTAR VIDEOJUEGOS ---");
            System.out.println("Insertados: " + VideojuegosDAO.insertarVideojuego(v1));
            System.out.println("Insertados: " + VideojuegosDAO.insertarVideojuego(v2));
            System.out.println("Insertados: " + VideojuegosDAO.insertarVideojuego(v3));
            System.out.println("Insertados: " + VideojuegosDAO.insertarVideojuego(v4));
            System.out.println("Insertados: " + VideojuegosDAO.insertarVideojuego(v5));

            // Listar videojuegos
            System.out.println("\n--- LISTAR VIDEOJUEGOS ---");
            for (Videojuego videojuego : VideojuegosDAO.listarVideojuegos()) {
                System.out.println(videojuego);
            }

            // Buscar videojuego por ID
            System.out.println("\n--- BUSCAR VIDEOJUEGO ---");
            Videojuego encontrado = VideojuegosDAO.buscarVideojuego(1);

            if (encontrado != null) {
                System.out.println(encontrado);
            }

            // Actualizar videojuego
            System.out.println("\n--- ACTUALIZAR VIDEOJUEGO ---");
            if (encontrado != null) {
                encontrado.setTitulo("Minecraft Actualizado");
                encontrado.setValoracion(10.0);

                System.out.println("Registros actualizados: "
                        + VideojuegosDAO.actualizarVideojuego(encontrado));
            }

            // Eliminar videojuego
            System.out.println("\n--- ELIMINAR VIDEOJUEGO ---");
            System.out.println("Registros eliminados: "
                    + VideojuegosDAO.eliminarVideojuego(5));

            // Consulta avanzada
            System.out.println("\n--- BUSCAR VIDEOJUEGOS POR GENERO ---");
            VideojuegosDAO.buscarVideojuegos("Sandbox", 150, 8.0);

            // Resumen por genero
            System.out.println("\n--- RESUMEN POR GENERO ---");
            VideojuegosDAO.resumenPorGenero();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
