

/** Clase proporcionada para el examen. */
public class ResultadoPedido {
    private final Menu menu;
    private final int personas;
    private final double importeBase;
    private final double descuento;
    private final double costeMenaje;
    private final double costeTransporte;
    private final double subtotal;
    private final double iva;
    private final double total;

    public ResultadoPedido(Menu menu, int personas, double importeBase, double descuento, double costeMenaje, double costeTransporte, double subtotal, double iva, double total) {
        this.menu = menu;
        this.personas = personas;
        this.importeBase = importeBase;
        this.descuento = descuento;
        this.costeMenaje = costeMenaje;
        this.costeTransporte = costeTransporte;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
    }

    public Menu getMenu() { return menu; }
    public int getPersonas() { return personas; }
    public double getImporteBase() { return importeBase; }
    public double getDescuento() { return descuento; }
    public double getCosteMenaje() { return costeMenaje; }
    public double getCosteTransporte() { return costeTransporte; }
    public double getSubtotal() { return subtotal; }
    public double getIva() { return iva; }
    public double getTotal() { return total; }
}
