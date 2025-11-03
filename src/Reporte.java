import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class Reporte {
    private String ReporteId,tipoReporte;
    private Date fechaGeneracion;
    private Map<String, Object> datos;

    public Reporte(String reporteId, String tipoReporte, Date fechaGeneracion, Map<String, Object> datos) {
        ReporteId = reporteId;
        this.tipoReporte = tipoReporte;
        this.fechaGeneracion = fechaGeneracion;
        this.datos = datos;
    }

    public String getReporteId() {
        return ReporteId;
    }

    public void setReporteId(String reporteId) {
        ReporteId = reporteId;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Map<String, Object> getDatos() {
        return datos;
    }

    public void setDatos(Map<String, Object> datos) {
        this.datos = datos;
    }
}
