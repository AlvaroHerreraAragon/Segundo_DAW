

/** Clase proporcionada para el examen. */
public class Menu {
    private final String codigo;
    private final String nombre;
    private final double precioPersona;
    private final boolean disponibleCena;

    public Menu(String codigo, String nombre, double precioPersona, boolean disponibleCena) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioPersona = precioPersona;
        this.disponibleCena = disponibleCena;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecioPersona() { return precioPersona; }
    public boolean isDisponibleCena() { return disponibleCena; }
}
