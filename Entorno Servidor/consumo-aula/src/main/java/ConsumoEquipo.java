
public class ConsumoEquipo {

    private String nombre;
    private int cantidad;
    private int potenciaVatios;
    private int horasDiarias;
    private double consumoKwh;

    public ConsumoEquipo(String nombre, int cantidad, int potenciaVatios,
            int horasDiarias, double consumoKwh) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.potenciaVatios = potenciaVatios;
        this.horasDiarias = horasDiarias;
        this.consumoKwh = consumoKwh;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getPotenciaVatios() {
        return potenciaVatios;
    }

    public int getHorasDiarias() {
        return horasDiarias;
    }

    public double getConsumoKwh() {
        return consumoKwh;
    }
}
