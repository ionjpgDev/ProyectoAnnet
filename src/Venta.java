import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Venta {
    private String ventaId, estado, tipoPago;
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
        this.total = items.stream().mapToDouble(ItemVenta::getSubtotal).sum();
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
        this.total = items.stream().mapToDouble(ItemVenta::getSubtotal).sum();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void mostrarInformacion() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'a las' HH:mm");

        System.out.println("┌─────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                        INFORMACIÓN DE VENTA                         │");
        System.out.println("├─────────────────────────────────────────────────────────────────────┤");
        System.out.printf("│ %-15s: %-45s │%n", "ID Venta", ventaId);
        System.out.printf("│ %-15s: %-45s │%n", "Estado", estado);
        System.out.printf("│ %-15s: %-45s │%n", "Tipo Pago", tipoPago);
        System.out.printf("│ %-15s: %-45s │%n", "Fecha", sdf.format(fechaVenta));
        System.out.printf("│ %-15s: %-45s │%n", "Cliente", cliente.getNombre());
        System.out.println("├─────────────────────────────────────────────────────────────────────┤");
        System.out.println("│                             DETALLE                                 │");
        System.out.println("├─────────────────────────────────────────────────────────────────────┤");

        // Mostrar items de la venta
        if (items != null && !items.isEmpty()) {
            System.out.println("│ Producto                                     Cant.    Precio     │");
            System.out.println("├─────────────────────────────────────────────────────────────────────┤");

            for (ItemVenta item : items) {
                String descripcion = item.getLibro() != null ?
                        item.getLibro().getTitulo() : "Producto";
                if (descripcion.length() > 35) {
                    descripcion = descripcion.substring(0, 32) + "...";
                }

                System.out.printf("│ %-35s %6d $%9.2f │%n",
                        descripcion,
                        item.getCantidad(),
                        item.getPrecioUnitario());
            }
        } else {
            System.out.println("│ No hay items en esta venta                                  │");
        }

        System.out.println("├─────────────────────────────────────────────────────────────────────┤");
        System.out.printf("│ %-15s: $%-43.2f │%n", "TOTAL", total);
        System.out.println("└─────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Venta{" +
                "ventaId='" + ventaId + '\'' +
                ", estado='" + estado + '\'' +
                ", tipoPago='" + tipoPago + '\'' +
                ", fechaVenta=" + sdf.format(fechaVenta) +
                ", cliente=" + cliente.getNombre() +
                ", items=" + items.size() +
                ", total=" + total +
                '}';
    }

    // MÉTODOS ESTÁTICOS
    public static boolean cancelarVenta(Venta venta) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== CANCELAR VENTA ===");
            venta.mostrarInformacion();

            System.out.print("¿ESTÁ SEGURO DE CANCELAR ESTA VENTA? (y/n): ");
            String confirmacion = sc.nextLine();

            if (confirmacion.equalsIgnoreCase("y")) {
                // Restaurar stock de los libros
                for (ItemVenta item : venta.getItems()) {
                    item.getLibro().agregarStock(item.getCantidad());
                }

                venta.setEstado("CANCELADA");
                System.out.println("✅ VENTA CANCELADA CORRECTAMENTE");
                return true;
            } else {
                System.out.println("CANCELACIÓN NO REALIZADA");
                return false;
            }

        } catch (Exception ex) {
            System.out.println("Error al cancelar venta: " + ex.getMessage());
            return false;
        }
    }
    public static Venta crearVenta(List<Cliente> clientes, List<Libro> librosDisponibles) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== CREAR NUEVA VENTA ===");

            // Verificar si hay clientes
            if (clientes.isEmpty()) {
                System.out.println("No hay clientes registrados. Debe registrar un cliente primero.");
                return null;
            }

            // Verificar si hay libros disponibles
            if (librosDisponibles.isEmpty()) {
                System.out.println("No hay libros disponibles en el inventario.");
                return null;
            }

            // Seleccionar cliente
            System.out.println("SELECCIONE UN CLIENTE:");
            for (int i = 0; i < clientes.size(); i++) {
                Cliente cliente = clientes.get(i);
                System.out.printf("%d. %s - %s%n", i + 1, cliente.getNombre(), cliente.getEmail());
            }

            System.out.print("SELECCIONE EL NÚMERO DEL CLIENTE: ");
            int seleccionCliente = sc.nextInt();
            sc.nextLine();

            if (seleccionCliente < 1 || seleccionCliente > clientes.size()) {
                System.out.println("Selección de cliente inválida");
                return null;
            }

            Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);

            // Crear items de venta
            List<ItemVenta> itemsVenta = new ArrayList<>();
            boolean agregarMasItems = true;

            while (agregarMasItems) {
                ItemVenta item = ItemVenta.crearItemVenta(librosDisponibles);
                if (item != null) {
                    itemsVenta.add(item);
                    // Reducir stock del libro automáticamente
                    item.getLibro().reducirStock(item.getCantidad());
                }

                if (itemsVenta.isEmpty()) {
                    System.out.print("¿DESEA AGREGAR UN ITEM? (y/n): ");
                } else {
                    System.out.print("¿DESEA AGREGAR OTRO ITEM? (y/n): ");
                }
                agregarMasItems = sc.nextLine().equalsIgnoreCase("y");
            }

            if (itemsVenta.isEmpty()) {
                System.out.println("No se puede crear una venta sin items");
                return null;
            }

            // Seleccionar tipo de pago
            System.out.println("SELECCIONE TIPO DE PAGO:");
            System.out.println("1. EFECTIVO");
            System.out.println("2. TARJETA CRÉDITO");
            System.out.println("3. TARJETA DÉBITO");
            System.out.println("4. TRANSFERENCIA");
            System.out.print("SELECCIONE UNA OPCIÓN: ");

            String tipoPago;
            int opcionPago = sc.nextInt();
            sc.nextLine();

            switch (opcionPago) {
                case 1: tipoPago = "EFECTIVO"; break;
                case 2: tipoPago = "TARJETA CRÉDITO"; break;
                case 3: tipoPago = "TARJETA DÉBITO"; break;
                case 4: tipoPago = "TRANSFERENCIA"; break;
                default:
                    System.out.println("Opción inválida, se usará EFECTIVO");
                    tipoPago = "EFECTIVO";
            }

            // Generar ID de venta
            String ventaId = "VTA-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            // Crear venta
            Venta nuevaVenta = new Venta(ventaId, tipoPago, clienteSeleccionado, itemsVenta);

            // Cambiar estado a COMPLETADA
            nuevaVenta.setEstado("COMPLETADA");

            // Agregar puntos al cliente (1 punto por cada 10 Bs. de compra)
            int puntosGanados = (int) (nuevaVenta.getTotal() / 10);
            clienteSeleccionado.agregarPuntos(puntosGanados);

            System.out.println("✅ VENTA CREADA CORRECTAMENTE");
            System.out.println("💰 Total: " + nuevaVenta.getTotal() + "Bs.");
            System.out.println("🎯 Puntos ganados por el cliente: " + puntosGanados);
            nuevaVenta.mostrarInformacion();

            return nuevaVenta;

        } catch (Exception ex) {
            System.out.println("Error al crear venta: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public static void actualizarVenta(Venta venta, List<Libro> librosDisponibles) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== ACTUALIZAR VENTA ===");
            venta.mostrarInformacion();

            System.out.print("¿DESEA CAMBIAR EL ESTADO DE LA VENTA? (y/n): ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                System.out.println("ESTADOS DISPONIBLES:");
                System.out.println("1. PENDIENTE");
                System.out.println("2. COMPLETADA");
                System.out.println("3. CANCELADA");
                System.out.println("4. ENTREGADA");
                System.out.print("SELECCIONE EL NUEVO ESTADO: ");

                int opcionEstado = sc.nextInt();
                sc.nextLine();

                switch (opcionEstado) {
                    case 1: venta.setEstado("PENDIENTE"); break;
                    case 2: venta.setEstado("COMPLETADA"); break;
                    case 3:
                        venta.setEstado("CANCELADA");
                        // Restaurar stock si se cancela
                        for (ItemVenta item : venta.getItems()) {
                            item.getLibro().agregarStock(item.getCantidad());
                        }
                        break;
                    case 4: venta.setEstado("ENTREGADA"); break;
                    default: System.out.println("Opción inválida, se mantiene estado actual");
                }
                System.out.println("Estado actualizado a: " + venta.getEstado());
            }

            System.out.print("¿DESEA AGREGAR MÁS ITEMS A LA VENTA? (y/n): ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                boolean agregarMasItems = true;
                while (agregarMasItems) {
                    ItemVenta item = ItemVenta.crearItemVenta(librosDisponibles);
                    if (item != null) {
                        venta.getItems().add(item);
                        venta.setTotal(venta.getItems().stream().mapToDouble(ItemVenta::getSubtotal).sum());
                        // Reducir stock del libro
                        item.getLibro().reducirStock(item.getCantidad());
                    }

                    System.out.print("¿DESEA AGREGAR OTRO ITEM? (y/n): ");
                    agregarMasItems = sc.nextLine().equalsIgnoreCase("y");
                }
            }

            System.out.println("✅ VENTA ACTUALIZADA CORRECTAMENTE");
            venta.mostrarInformacion();

        } catch (Exception ex) {
            System.out.println("Error al actualizar venta: " + ex.getMessage());
        }
    }

    public static boolean eliminarVenta(List<Venta> ventas, String ventaId) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== ELIMINAR VENTA ===");

            // Buscar la venta
            Venta ventaAEliminar = null;
            for (Venta venta : ventas) {
                if (venta.getVentaId().equals(ventaId)) {
                    ventaAEliminar = venta;
                    break;
                }
            }

            if (ventaAEliminar == null) {
                System.out.println("No se encontró la venta con ID: " + ventaId);
                return false;
            }

            // Mostrar información
            ventaAEliminar.mostrarInformacion();

            // Confirmación
            System.out.print("¿ESTÁ SEGURO DE ELIMINAR ESTA VENTA? (y/n): ");
            String confirmacion = sc.nextLine();

            if (confirmacion.equalsIgnoreCase("y")) {
                // Restaurar stock de los libros antes de eliminar
                for (ItemVenta item : ventaAEliminar.getItems()) {
                    item.getLibro().agregarStock(item.getCantidad());
                }
                ventas.remove(ventaAEliminar);
                System.out.println("✅ VENTA ELIMINADA CORRECTAMENTE");
                return true;
            } else {
                System.out.println("ELIMINACIÓN CANCELADA");
                return false;
            }

        } catch (Exception ex) {
            System.out.println("Error al eliminar venta: " + ex.getMessage());
            return false;
        }
    }

    public static void mostrarTodasLasVentas(List<Venta> ventas) {
        if (ventas == null || ventas.isEmpty()) {
            System.out.println("No hay ventas registradas");
            return;
        }

        System.out.println("=== LISTA DE VENTAS ===");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        double totalGeneral = 0;

        for (int i = 0; i < ventas.size(); i++) {
            Venta venta = ventas.get(i);
            System.out.printf("%d. %s - %s - %s - %.2fBs. - %s%n",
                    i + 1, venta.getVentaId(), sdf.format(venta.getFechaVenta()),
                    venta.getCliente().getNombre(), venta.getTotal(), venta.getEstado());
            totalGeneral += venta.getTotal();
        }

        System.out.println("────────────────────────────────────────────────────");
        System.out.printf("TOTAL GENERAL: %.2fBs.%n", totalGeneral);
        System.out.printf("CANTIDAD DE VENTAS: %d%n", ventas.size());
    }

    public static Venta buscarVentaPorId(List<Venta> ventas, String ventaId) {
        for (Venta venta : ventas) {
            if (venta.getVentaId().equalsIgnoreCase(ventaId)) {
                return venta;
            }
        }
        System.out.println("No se encontró venta con ID: " + ventaId);
        return null;
    }

    public static void mostrarVentasPorCliente(List<Venta> ventas, String nombreCliente) {
        List<Venta> ventasCliente = new ArrayList<>();

        for (Venta venta : ventas) {
            if (venta.getCliente().getNombre().toLowerCase().contains(nombreCliente.toLowerCase())) {
                ventasCliente.add(venta);
            }
        }

        if (ventasCliente.isEmpty()) {
            System.out.println("No se encontraron ventas para el cliente: " + nombreCliente);
            return;
        }

        System.out.println("=== VENTAS DEL CLIENTE: " + nombreCliente.toUpperCase() + " ===");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        double totalCliente = 0;

        for (Venta venta : ventasCliente) {
            System.out.printf("- %s - %s - %.2fBs. - %s%n",
                    venta.getVentaId(), sdf.format(venta.getFechaVenta()),
                    venta.getTotal(), venta.getEstado());
            totalCliente += venta.getTotal();
        }

        System.out.println("────────────────────────────────────────────────────");
        System.out.printf("TOTAL DEL CLIENTE: %.2fBs.%n", totalCliente);
        System.out.printf("CANTIDAD DE VENTAS: %d%n", ventasCliente.size());
    }

    public static void mostrarVentasPorEstado(List<Venta> ventas, String estado) {
        List<Venta> ventasEstado = new ArrayList<>();

        for (Venta venta : ventas) {
            if (venta.getEstado().equalsIgnoreCase(estado)) {
                ventasEstado.add(venta);
            }
        }

        if (ventasEstado.isEmpty()) {
            System.out.println("No se encontraron ventas con estado: " + estado);
            return;
        }

        System.out.println("=== VENTAS CON ESTADO: " + estado.toUpperCase() + " ===");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        double totalEstado = 0;

        for (Venta venta : ventasEstado) {
            System.out.printf("- %s - %s - %s - %.2fBs.%n",
                    venta.getVentaId(), sdf.format(venta.getFechaVenta()),
                    venta.getCliente().getNombre(), venta.getTotal());
            totalEstado += venta.getTotal();
        }

        System.out.println("────────────────────────────────────────────────────");
        System.out.printf("TOTAL: %.2fBs.%n", totalEstado);
        System.out.printf("CANTIDAD: %d%n", ventasEstado.size());
    }
}