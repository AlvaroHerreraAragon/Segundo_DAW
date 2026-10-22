
/**
 * Clase proporcionada en el examen.
 */
public class Plan {

    private final String codigo;
    private final String nombre;
    private final int capacidadGb;
    private final int velocidadGbMin;
    private final double precioMensual;

    public Plan(String codigo, String nombre, int capacidadGb,
            int velocidadGbMin, double precioMensual) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidadGb = capacidadGb;
        this.velocidadGbMin = velocidadGbMin;
        this.precioMensual = precioMensual;

    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidadGb() {
        return capacidadGb;
    }

    public int getVelocidadGbMin() {
        return velocidadGbMin;
    }

    public double getPrecioMensual() {
        return precioMensual;
    }

}
