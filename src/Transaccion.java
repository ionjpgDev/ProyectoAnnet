import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Transaccion {
    private String transaccionId, tipo, descripcion, usuario;
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

    // Constructor simplificado
    public Transaccion(String tipo, String descripcion, String usuario, double monto) {
        this.transaccionId = "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.fecha = new Date();
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

    public void obtenerInfoCompleta() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'a las' HH:mm:ss");

        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│             INFORMACIÓN TRANSACCIÓN             │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│ %-15s: %-30s │%n", "ID Transacción", transaccionId);
        System.out.printf("│ %-15s: %-30s │%n", "Tipo", tipo);
        System.out.printf("│ %-15s: %-30s │%n", "Descripción", descripcion);
        System.out.printf("│ %-15s: %-30s │%n", "Usuario", usuario);
        System.out.printf("│ %-15s: %-30s │%n", "Fecha", sdf.format(fecha));
        System.out.printf("│ %-15s: %-29.2fBs. │%n", "Monto", monto);
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    public String getTipoFormateado() {
        switch (tipo.toUpperCase()) {
            case "VENTA": return "💰 VENTA";
            case "COMPRA": return "🛒 COMPRA";
            case "DEVOLUCION": return "↩️ DEVOLUCIÓN";
            case "AJUSTE": return "⚙️ AJUSTE";
            case "GASTO": return "💸 GASTO";
            default: return tipo;
        }
    }

    // MÉTODOS ESTÁTICOS

    public static Transaccion registrarTransaccion() {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== REGISTRAR NUEVA TRANSACCIÓN ===");

            // Seleccionar tipo
            System.out.println("TIPOS DE TRANSACCIÓN:");
            System.out.println("1. VENTA");
            System.out.println("2. COMPRA");
            System.out.println("3. DEVOLUCIÓN");
            System.out.println("4. AJUSTE");
            System.out.println("5. GASTO");
            System.out.print("SELECCIONE EL TIPO: ");

            String tipo;
            int opcionTipo = sc.nextInt();
            sc.nextLine();

            switch (opcionTipo) {
                case 1: tipo = "VENTA"; break;
                case 2: tipo = "COMPRA"; break;
                case 3: tipo = "DEVOLUCION"; break;
                case 4: tipo = "AJUSTE"; break;
                case 5: tipo = "GASTO"; break;
                default:
                    System.out.println("Opción inválida, se usará AJUSTE");
                    tipo = "AJUSTE";
            }

            // Descripción
            System.out.print("DESCRIPCIÓN: ");
            String descripcion = sc.nextLine();

            // Usuario
            System.out.print("USUARIO: ");
            String usuario = sc.nextLine();

            // Monto
            System.out.print("MONTO: ");
            double monto = sc.nextDouble();
            sc.nextLine();

            Transaccion nuevaTransaccion = new Transaccion(tipo, descripcion, usuario, monto);
            System.out.println("✅ TRANSACCIÓN REGISTRADA CORRECTAMENTE");
            nuevaTransaccion.obtenerInfoCompleta();

            return nuevaTransaccion;

        } catch (Exception ex) {
            System.out.println("Error al registrar transacción: " + ex.getMessage());
            return null;
        }
    }

    public static Transaccion crearTransaccionVenta(Venta venta, String usuario) {
        String descripcion = "Venta #" + venta.getVentaId() + " - Cliente: " + venta.getCliente().getNombre();
        Transaccion transaccion = new Transaccion("VENTA", descripcion, usuario, venta.getTotal());
        System.out.println("Transacción de venta creada automáticamente");
        return transaccion;
    }

    public static Transaccion crearTransaccionCompra(String proveedor, String usuario, double monto, String detalles) {
        String descripcion = "Compra a " + proveedor + " - " + detalles;
        Transaccion transaccion = new Transaccion("COMPRA", descripcion, usuario, monto);
        System.out.println("Transacción de compra creada automáticamente");
        return transaccion;
    }

    public static Transaccion crearTransaccionDevolucion(Venta venta, String usuario, String motivo) {
        String descripcion = "Devolución venta #" + venta.getVentaId() + " - " + motivo;
        Transaccion transaccion = new Transaccion("DEVOLUCION", descripcion, usuario, -venta.getTotal());
        System.out.println("Transacción de devolución creada automáticamente");
        return transaccion;
    }

    public static void mostrarTodasLasTransacciones(List<Transaccion> transacciones) {
        if (transacciones == null || transacciones.isEmpty()) {
            System.out.println("No hay transacciones registradas");
            return;
        }

        System.out.println("=== HISTORIAL DE TRANSACCIONES ===");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        double balance = 0;

        for (Transaccion transaccion : transacciones) {
            String signo = transaccion.getMonto() >= 0 ? "+" : "";
            System.out.printf("%s | %s | %s | %s%.2fBs. | %s%n",
                    transaccion.getTransaccionId(),
                    sdf.format(transaccion.getFecha()),
                    transaccion.getTipoFormateado(),
                    signo, transaccion.getMonto(),
                    transaccion.getDescripcion());
            balance += transaccion.getMonto();
        }

        System.out.println("─────────────────────────────────────────────────────────────────────");
        System.out.printf("BALANCE TOTAL: %.2fBs.%n", balance);
        System.out.printf("TOTAL DE TRANSACCIONES: %d%n", transacciones.size());
    }

    public static void mostrarTransaccionesPorTipo(List<Transaccion> transacciones, String tipo) {
        List<Transaccion> transaccionesFiltradas = transacciones.stream()
                .filter(t -> t.getTipo().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());

        if (transaccionesFiltradas.isEmpty()) {
            System.out.println("No hay transacciones de tipo: " + tipo);
            return;
        }

        System.out.println("=== TRANSACCIONES DE TIPO: " + tipo.toUpperCase() + " ===");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        double totalTipo = 0;

        for (Transaccion transaccion : transaccionesFiltradas) {
            System.out.printf("%s | %s | %s | %.2fBs. | %s%n",
                    transaccion.getTransaccionId(),
                    sdf.format(transaccion.getFecha()),
                    transaccion.getUsuario(),
                    transaccion.getMonto(),
                    transaccion.getDescripcion());
            totalTipo += transaccion.getMonto();
        }

        System.out.println("────────────────────────────────────────────────────");
        System.out.printf("TOTAL %s: %.2fBs.%n", tipo.toUpperCase(), totalTipo);
        System.out.printf("CANTIDAD: %d%n", transaccionesFiltradas.size());
    }

    public static void mostrarTransaccionesPorUsuario(List<Transaccion> transacciones, String usuario) {
        List<Transaccion> transaccionesUsuario = transacciones.stream()
                .filter(t -> t.getUsuario().equalsIgnoreCase(usuario))
                .collect(Collectors.toList());

        if (transaccionesUsuario.isEmpty()) {
            System.out.println("No hay transacciones del usuario: " + usuario);
            return;
        }

        System.out.println("=== TRANSACCIONES DEL USUARIO: " + usuario.toUpperCase() + " ===");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        double totalUsuario = 0;

        for (Transaccion transaccion : transaccionesUsuario) {
            System.out.printf("%s | %s | %s | %.2fBs. | %s%n",
                    transaccion.getTransaccionId(),
                    sdf.format(transaccion.getFecha()),
                    transaccion.getTipo(),
                    transaccion.getMonto(),
                    transaccion.getDescripcion());
            totalUsuario += transaccion.getMonto();
        }

        System.out.println("────────────────────────────────────────────────────");
        System.out.printf("TOTAL: %.2fBs.%n", totalUsuario);
        System.out.printf("CANTIDAD: %d%n", transaccionesUsuario.size());
    }

    public static void mostrarTransaccionesPorFecha(List<Transaccion> transacciones, Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Transaccion> transaccionesFecha = transacciones.stream()
                .filter(t -> {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    return formatter.format(t.getFecha()).equals(formatter.format(fecha));
                })
                .collect(Collectors.toList());

        if (transaccionesFecha.isEmpty()) {
            System.out.println("No hay transacciones en la fecha: " + sdf.format(fecha));
            return;
        }

        System.out.println("=== TRANSACCIONES DEL " + sdf.format(fecha).toUpperCase() + " ===");
        double totalFecha = 0;

        for (Transaccion transaccion : transaccionesFecha) {
            SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
            System.out.printf("%s | %s | %s | %s | %.2fBs. | %s%n",
                    transaccion.getTransaccionId(),
                    sdfHora.format(transaccion.getFecha()),
                    transaccion.getTipo(),
                    transaccion.getUsuario(),
                    transaccion.getMonto(),
                    transaccion.getDescripcion());
            totalFecha += transaccion.getMonto();
        }

        System.out.println("────────────────────────────────────────────────────");
        System.out.printf("TOTAL DEL DÍA: %.2fBs.%n", totalFecha);
        System.out.printf("CANTIDAD: %d%n", transaccionesFecha.size());
    }

    public static void mostrarEstadisticasTransacciones(List<Transaccion> transacciones) {
        if (transacciones.isEmpty()) {
            System.out.println("No hay transacciones para mostrar estadísticas");
            return;
        }

        System.out.println("=== ESTADÍSTICAS DE TRANSACCIONES ===");

        // Totales por tipo
        Map<String, Double> totalesPorTipo = transacciones.stream()
                .collect(Collectors.groupingBy(
                        Transaccion::getTipo,
                        Collectors.summingDouble(Transaccion::getMonto)
                ));

        Map<String, Long> cantidadPorTipo = transacciones.stream()
                .collect(Collectors.groupingBy(
                        Transaccion::getTipo,
                        Collectors.counting()
                ));

        double balanceTotal = transacciones.stream()
                .mapToDouble(Transaccion::getMonto)
                .sum();

        System.out.printf("BALANCE TOTAL: %.2fBs.%n", balanceTotal);
        System.out.printf("TOTAL TRANSACCIONES: %d%n", transacciones.size());
        System.out.println("────────────────────────────────────");

        totalesPorTipo.forEach((tipo, total) -> {
            System.out.printf("%s: %.2fBs. (%d transacciones)%n",
                    tipo, total, cantidadPorTipo.get(tipo));
        });

        // Transacción más grande
        Optional<Transaccion> transaccionMasGrande = transacciones.stream()
                .max(Comparator.comparingDouble(Transaccion::getMonto));
        transaccionMasGrande.ifPresent(t ->
                System.out.printf("Transacción más grande: %s (%.2fBs.)%n",
                        t.getDescripcion(), t.getMonto()));

        // Promedio de transacciones
        double promedio = transacciones.stream()
                .mapToDouble(Transaccion::getMonto)
                .average()
                .orElse(0.0);
        System.out.printf("Promedio por transacción: %.2fBs.%n", promedio);
    }

    public static Transaccion buscarTransaccionPorId(List<Transaccion> transacciones, String transaccionId) {
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getTransaccionId().equalsIgnoreCase(transaccionId)) {
                return transaccion;
            }
        }
        System.out.println("No se encontró transacción con ID: " + transaccionId);
        return null;
    }

    public static void generarReporteDiario(List<Transaccion> transacciones, Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Transaccion> transaccionesDia = transacciones.stream()
                .filter(t -> {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    return formatter.format(t.getFecha()).equals(formatter.format(fecha));
                })
                .collect(Collectors.toList());

        System.out.println("=== REPORTE DIARIO - " + sdf.format(fecha).toUpperCase() + " ===");

        if (transaccionesDia.isEmpty()) {
            System.out.println("No hay transacciones este día");
            return;
        }

        // Agrupar por tipo
        Map<String, Double> resumenPorTipo = transaccionesDia.stream()
                .collect(Collectors.groupingBy(
                        Transaccion::getTipo,
                        Collectors.summingDouble(Transaccion::getMonto)
                ));

        double totalDia = transaccionesDia.stream()
                .mapToDouble(Transaccion::getMonto)
                .sum();

        System.out.println("RESUMEN POR TIPO:");
        resumenPorTipo.forEach((tipo, total) -> {
            System.out.printf("  %s: %.2fBs.%n", tipo, total);
        });

        System.out.println("────────────────────────────────────");
        System.out.printf("TOTAL DEL DÍA: %.2fBs.%n", totalDia);
        System.out.printf("CANTIDAD DE TRANSACCIONES: %d%n", transaccionesDia.size());

        // Detalle de transacciones
        System.out.println("\nDETALLE DE TRANSACCIONES:");
        transaccionesDia.forEach(t -> {
            SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
            System.out.printf("  [%s] %s - %s - %.2fBs.%n",
                    sdfHora.format(t.getFecha()),
                    t.getTipo(),
                    t.getDescripcion(),
                    t.getMonto());
        });
    }

    public static void gestionarTransacciones(List<Transaccion> transacciones) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== GESTIÓN DE TRANSACCIONES ===");
            System.out.println("1. Registrar nueva transacción");
            System.out.println("2. Mostrar todas las transacciones");
            System.out.println("3. Mostrar transacciones por tipo");
            System.out.println("4. Mostrar transacciones por usuario");
            System.out.println("5. Mostrar transacciones por fecha");
            System.out.println("6. Buscar transacción por ID");
            System.out.println("7. Mostrar estadísticas");
            System.out.println("8. Generar reporte diario");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        Transaccion nueva = registrarTransaccion();
                        if (nueva != null) {
                            transacciones.add(nueva);
                        }
                        break;
                    case 2:
                        mostrarTodasLasTransacciones(transacciones);
                        break;
                    case 3:
                        System.out.print("Ingrese tipo (VENTA/COMPRA/DEVOLUCION/AJUSTE/GASTO): ");
                        String tipo = sc.nextLine();
                        mostrarTransaccionesPorTipo(transacciones, tipo);
                        break;
                    case 4:
                        System.out.print("Ingrese usuario: ");
                        String usuario = sc.nextLine();
                        mostrarTransaccionesPorUsuario(transacciones, usuario);
                        break;
                    case 5:
                        System.out.print("Ingrese fecha (dd/MM/yyyy): ");
                        String fechaStr = sc.nextLine();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date fecha = sdf.parse(fechaStr);
                        mostrarTransaccionesPorFecha(transacciones, fecha);
                        break;
                    case 6:
                        System.out.print("Ingrese ID de transacción: ");
                        String id = sc.nextLine();
                        Transaccion encontrada = buscarTransaccionPorId(transacciones, id);
                        if (encontrada != null) {
                            encontrada.obtenerInfoCompleta();
                        }
                        break;
                    case 7:
                        mostrarEstadisticasTransacciones(transacciones);
                        break;
                    case 8:
                        System.out.print("Ingrese fecha para reporte (dd/MM/yyyy): ");
                        String fechaReporteStr = sc.nextLine();
                        SimpleDateFormat sdfReporte = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaReporte = sdfReporte.parse(fechaReporteStr);
                        generarReporteDiario(transacciones, fechaReporte);
                        break;
                    case 0:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return String.format("Transaccion[%s | %s | %s | %.2fBs.]",
                transaccionId, sdf.format(fecha), tipo, monto);
    }
}