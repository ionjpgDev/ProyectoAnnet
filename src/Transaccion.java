import java.util.Date;

public class Transaccion {
    private String transaccionId,tipo,descripcion,usuario;
    private Date fecha;
    private double monto;

    public Transaccion(String transaccionId, String tipo, String descripcion, String usuario, Date fecha, double monto) {
        this.transaccionId = transaccionId;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.fecha = fecha;
        this.monto = monto;
    }

    public String getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(String transaccionId) {
        this.transaccionId = transaccionId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "transaccionId='" + transaccionId + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", usuario='" + usuario + '\'' +
                ", fecha=" + fecha +
                ", monto=" + monto +
                '}';
    }
}
