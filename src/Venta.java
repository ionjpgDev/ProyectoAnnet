import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Venta {
    private String ventaId,estado,tipoPago;
    private Date fechaVenta;
    private Cliente cliente;
    private List<ItemVenta> items;
    private double total;

    public Venta(String ventaId, String tipoPago, Cliente cliente, List<ItemVenta> items) {
        this.ventaId = ventaId;
        this.estado = "PENDIENTE";
        this.tipoPago = tipoPago;
        this.fechaVenta = new Date();
        this.cliente = cliente;
        this.items = items;
        this.total = items.stream().mapToDouble(e->e.getSubtotal()).sum();
    }

    public String getVentaId() {
        return ventaId;
    }

    public void setVentaId(String ventaId) {
        this.ventaId = ventaId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenta> getItems() {
        return items;
    }

    public void setItems(List<ItemVenta> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public void mostrarInformacion(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'a las' HH:mm");

        System.out.println("┌─────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                        INFORMACIÓN DE VENTA                         │");
        System.out.println("├─────────────────────────────────────────────────────────────────────┤");
        System.out.printf ("│ %-15s: %-45s │%n", "ID Venta", ventaId);
        System.out.printf ("│ %-15s: %-45s │%n", "Estado", estado);
        System.out.printf ("│ %-15s: %-45s │%n", "Tipo Pago", tipoPago);
        System.out.printf ("│ %-15s: %-45s │%n", "Fecha", sdf.format(fechaVenta));
        System.out.printf ("│ %-15s: %-45s │%n", "Cliente", cliente.getNombre());
        System.out.println("├─────────────────────────────────────────────────────────────────────┤");
        System.out.println("│                             DETALLE                                 │");
        System.out.println("├─────────────────────────────────────────────────────────────────────┤");

        // Mostrar items de la venta
        if (items != null && !items.isEmpty()) {
            System.out.println("│ Producto                                     Cant.    Precio │");
            System.out.println("├─────────────────────────────────────────────────────────────────────┤");

            for (ItemVenta item : items) {
                String descripcion = item.getLibro() != null ? item.getLibro().getLibroId()+" - "+item.getLibro().getTitulo()  : "Producto";
                if (descripcion.length() > 35) {
                    descripcion = descripcion.substring(0, 32) + "...";
                }

                System.out.printf("│ %-40s %8d $%9.2f │\n",
                        descripcion,
                        item.getCantidad(),
                        item.getLibro().getprecio());
            }
        } else {
            System.out.println("│ No hay items en esta venta                                  │");
        }

        System.out.println("├─────────────────────────────────────────────────────────────────────┤");
        System.out.printf ("│ %-15s: $%-43.2f │%n", "TOTAL", total);
        System.out.println("└─────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public String toString() {
        return "Venta{" +
                "ventaId='" + ventaId + '\'' +
                ", estado='" + estado + '\'' +
                ", tipoPago='" + tipoPago + '\'' +
                ", fechaVenta=" + fechaVenta +
                ", cliente=" + cliente +
                ", items=" + items +
                ", total=" + total +
                '}';
    }
}
