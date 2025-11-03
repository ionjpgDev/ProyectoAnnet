import java.util.Date;
import java.util.List;

public class Venta {
    private String ventaId,estado,tipoPago;
    private Date fechaVenta;
    private Cliente cliente;
    private List<ItemVenta> items;
    private double total;

    public Venta(String ventaId, String estado, String tipoPago, Date fechaVenta, Cliente cliente, List<ItemVenta> items, double total) {
        this.ventaId = ventaId;
        this.estado = estado;
        this.tipoPago = tipoPago;
        this.fechaVenta = fechaVenta;
        this.cliente = cliente;
        this.items = items;
        this.total = total;
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
