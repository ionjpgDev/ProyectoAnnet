import java.text.SimpleDateFormat;
import java.util.*;

public class Reporte {
    private String reporteId, tipoReporte;
    private Date fechaGeneracion;
    private Map<String, Object> datos;

    public Reporte(String tipoReporte, Map<String, Object> datos) {
        this.reporteId = "REP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.tipoReporte = tipoReporte;
        this.fechaGeneracion = new Date();
        this.datos = datos;
    }

    public String getReporteId() { return reporteId; }
    public String getTipoReporte() { return tipoReporte; }
    public Date getFechaGeneracion() { return fechaGeneracion; }
    public Map<String, Object> getDatos() { return datos; }

    public void mostrarReporte() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'a las' HH:mm");

        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│                   REPORTE                       │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│ %-15s: %-30s │%n", "ID Reporte", reporteId);
        System.out.printf("│ %-15s: %-30s │%n", "Tipo", tipoReporte);
        System.out.printf("│ %-15s: %-30s │%n", "Generado", sdf.format(fechaGeneracion));
        System.out.println("├─────────────────────────────────────────────────┤");

        if (datos != null && !datos.isEmpty()) {
            for (Map.Entry<String, Object> entry : datos.entrySet()) {
                System.out.printf("│ %-15s: %-30s │%n", entry.getKey(), entry.getValue());
            }
        } else {
            System.out.println("│        No hay datos en el reporte           │");
        }

        System.out.println("└─────────────────────────────────────────────────┘");
    }


    public static Reporte generarReporteVentas(List<Venta> ventas) {
        Map<String, Object> datos = new LinkedHashMap<>();

        double totalVentas = ventas.stream()
                .mapToDouble(Venta::getTotal)
                .sum();

        long ventasCompletadas = ventas.stream()
                .filter(v -> "COMPLETADA".equals(v.getEstado()))
                .count();

        datos.put("Total Ventas", String.format("%.2f Bs.", totalVentas));
        datos.put("Cantidad Ventas", ventas.size());
        datos.put("Ventas Completadas", ventasCompletadas);
        datos.put("Ventas Pendientes", ventas.size() - ventasCompletadas);

        return new Reporte("VENTAS", datos);
    }

    public static Reporte generarReporteInventario(Inventario inventario) {
        Map<String, Object> datos = new LinkedHashMap<>();

        datos.put("Total Libros", inventario.getLibros().size());
        datos.put("Stock Total", inventario.getLibros().values().stream()
                .mapToInt(Libro::getStock).sum());
        datos.put("Valor Total", String.format("%.2f Bs.",
                inventario.getLibros().values().stream()
                        .mapToDouble(l -> l.getprecio() * l.getStock()).sum()));
        datos.put("Stock Mínimo", inventario.getStockMinimo());

        return new Reporte("INVENTARIO", datos);
    }

    public static Reporte generarReporteClientes(List<Cliente> clientes) {
        Map<String, Object> datos = new LinkedHashMap<>();

        long clientesFrecuentes = clientes.stream()
                .filter(Cliente::isEsFrecuente)
                .count();

        datos.put("Total Clientes", clientes.size());
        datos.put("Clientes Frecuentes", clientesFrecuentes);
        datos.put("Clientes Regulares", clientes.size() - clientesFrecuentes);

        return new Reporte("CLIENTES", datos);
    }

    public static Reporte generarReporteTransacciones(List<Transaccion> transacciones) {
        Map<String, Object> datos = new LinkedHashMap<>();

        double balance = transacciones.stream()
                .mapToDouble(Transaccion::getMonto)
                .sum();

        datos.put("Total Transacciones", transacciones.size());
        datos.put("Balance Total", String.format("%.2f Bs.", balance));
        datos.put("Ingresos", String.format("%.2f Bs.",
                transacciones.stream().filter(t -> t.getMonto() > 0)
                        .mapToDouble(Transaccion::getMonto).sum()));
        datos.put("Egresos", String.format("%.2f Bs.",
                transacciones.stream().filter(t -> t.getMonto() < 0)
                        .mapToDouble(Transaccion::getMonto).sum()));

        return new Reporte("TRANSACCIONES", datos);
    }

    public static void mostrarMenuReportes(List<Venta> ventas, Inventario inventario,
                                           List<Cliente> clientes, List<Transaccion> transacciones) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== GENERAR REPORTES ===");
            System.out.println("1. Reporte de Ventas");
            System.out.println("2. Reporte de Inventario");
            System.out.println("3. Reporte de Clientes");
            System.out.println("4. Reporte de Transacciones");
            System.out.println("5. Reporte General");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    Reporte reporteVentas = generarReporteVentas(ventas);
                    reporteVentas.mostrarReporte();
                    break;
                case 2:
                    Reporte reporteInventario = generarReporteInventario(inventario);
                    reporteInventario.mostrarReporte();
                    break;
                case 3:
                    Reporte reporteClientes = generarReporteClientes(clientes);
                    reporteClientes.mostrarReporte();
                    break;
                case 4:
                    Reporte reporteTransacciones = generarReporteTransacciones(transacciones);
                    reporteTransacciones.mostrarReporte();
                    break;
                case 5:
                    generarReporteGeneral(ventas, inventario, clientes, transacciones);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    public static void generarReporteGeneral(List<Venta> ventas, Inventario inventario,
                                             List<Cliente> clientes, List<Transaccion> transacciones) {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│               REPORTE GENERAL                   │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│ %-20s: %-25s │%n", "Fecha", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│ %-20s: %-25d │%n", "Total Ventas", ventas.size());
        System.out.printf("│ %-20s: %-25d │%n", "Total Clientes", clientes.size());
        System.out.printf("│ %-20s: %-25d │%n", "Libros en Inventario", inventario.getLibros().size());
        System.out.printf("│ %-20s: %-25d │%n", "Total Transacciones", transacciones.size());
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    @Override
    public String toString() {
        return String.format("Reporte[%s - %s - %s]",
                reporteId, tipoReporte, new SimpleDateFormat("dd/MM/yyyy").format(fechaGeneracion));
    }
}