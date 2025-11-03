import java.util.Date;

public class Cliente {
    private String clienteId, nombre,email,telefono,direccion;
    private int puntosAcumulado;
    private Date fechaRegistro;
    private boolean esFrecuente;


    public Cliente(String clienteId, String nombre, String email, String telefono, String direccion, int puntosAcumulado, Date fechaRegistro, boolean esFrecuente) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.puntosAcumulado = puntosAcumulado;
        this.fechaRegistro = fechaRegistro;
        this.esFrecuente = esFrecuente;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getPuntosAcumulado() {
        return puntosAcumulado;
    }

    public void setPuntosAcumulado(int puntosAcumulado) {
        this.puntosAcumulado = puntosAcumulado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isEsFrecuente() {
        return esFrecuente;
    }

    public void setEsFrecuente(boolean esFrecuente) {
        this.esFrecuente = esFrecuente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "clienteId='" + clienteId + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", puntosAcumulado=" + puntosAcumulado +
                ", fechaRegistro=" + fechaRegistro +
                ", esFrecuente=" + esFrecuente +
                '}';
    }
}
