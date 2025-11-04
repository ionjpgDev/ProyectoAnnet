import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ItemVenta {
    private String itemId;
    private Libro libro;
    private int cantidad;
    private double precioUnitario, subtotal;

    public ItemVenta(Libro libro, int cantidad) {
        this.itemId = "ITM-"+UUID.randomUUID().toString().substring(0,8).toUpperCase();
        this.libro = libro;
        this.cantidad = cantidad;
        this.precioUnitario = libro.getprecio();
        this.subtotal = cantidad*libro.getprecio();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = this.cantidad * this.precioUnitario; // Recalcular subtotal
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.subtotal = this.cantidad * this.precioUnitario; // Recalcular subtotal
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void obtenerInfoCompleta() {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│           INFORMACIÓN DEL ITEM VENTA            │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│ %-15s: %-30s │%n", "ID ITEM", itemId);
        System.out.printf("│ %-15s: %-30s │%n", "ID Libro", libro.getLibroId());
        System.out.printf("│ %-15s: %-30s │%n", "Título", libro.getTitulo());
        System.out.printf("│ %-15s: %-29.2fBs. │%n", "Precio Unitario", precioUnitario);
        System.out.printf("│ %-15s: %-30d │%n", "Cantidad", cantidad);
        System.out.printf("│ %-15s: %-29.2fBs. │%n", "Subtotal", subtotal);
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    @Override
    public String toString() {
        return "ItemVenta{" +
                "itemId='" + itemId + '\'' +
                ", libro=" + libro.getTitulo() +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + subtotal +
                '}';
    }

    // MÉTODOS ESTÁTICOS SIMILARES A LIBRO

    public static ItemVenta crearItemVenta(List<Libro> librosDisponibles) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== CREAR ITEM DE VENTA ===");

            // Mostrar libros disponibles
            System.out.println("LIBROS DISPONIBLES:");
            for (int i = 0; i < librosDisponibles.size(); i++) {
                Libro libro = librosDisponibles.get(i);
                System.out.printf("%d. %s - %s (Stock: %d, Precio: %.2fBs.)%n",
                        i + 1, libro.getTitulo(), libro.getAutor(),
                        libro.getStock(), libro.getprecio());
            }

            // Seleccionar libro
            System.out.print("SELECCIONE EL NÚMERO DEL LIBRO: ");
            int seleccionLibro = sc.nextInt();
            sc.nextLine();

            if (seleccionLibro < 1 || seleccionLibro > librosDisponibles.size()) {
                System.out.println("Selección inválida");
                return null;
            }

            Libro libroSeleccionado = librosDisponibles.get(seleccionLibro - 1);

            // Ingresar cantidad
            System.out.print("INGRESE LA CANTIDAD: ");
            int cantidad = sc.nextInt();
            sc.nextLine();

            // Verificar stock
            if (cantidad > libroSeleccionado.getStock()) {
                System.out.println("Stock insuficiente. Stock disponible: " + libroSeleccionado.getStock());
                return null;
            }

            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor a 0");
                return null;
            }

            // Crear item de venta
            ItemVenta nuevoItem = new ItemVenta(libroSeleccionado, cantidad);
            System.out.println("✅ ITEM DE VENTA CREADO CORRECTAMENTE");
            nuevoItem.obtenerInfoCompleta();

            return nuevoItem;

        } catch (Exception ex) {
            System.out.println("Error al crear item de venta: " + ex.getMessage());
            return null;
        }
    }

    public static void actualizarItemVenta(ItemVenta item) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== ACTUALIZAR ITEM DE VENTA ===");
            item.obtenerInfoCompleta();

            System.out.print("¿DESEA CAMBIAR LA CANTIDAD? (y/n): ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                System.out.print("NUEVA CANTIDAD: ");
                int nuevaCantidad = sc.nextInt();
                sc.nextLine();

                // Verificar stock del libro
                if (nuevaCantidad > item.getLibro().getStock()) {
                    System.out.println("Stock insuficiente. Stock disponible: " + item.getLibro().getStock());
                    return;
                }

                if (nuevaCantidad <= 0) {
                    System.out.println("La cantidad debe ser mayor a 0");
                    return;
                }

                item.setCantidad(nuevaCantidad);
                System.out.println("CANTIDAD ACTUALIZADA");
            }

            System.out.print("¿DESEA CAMBIAR EL PRECIO UNITARIO? (y/n): ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                System.out.print("NUEVO PRECIO UNITARIO: ");
                double nuevoPrecio = sc.nextDouble();
                sc.nextLine();

                if (nuevoPrecio < 0) {
                    System.out.println("El precio no puede ser negativo");
                    return;
                }

                item.setPrecioUnitario(nuevoPrecio);
                System.out.println("PRECIO UNITARIO ACTUALIZADO");
            }

            System.out.println("✅ ITEM DE VENTA ACTUALIZADO CORRECTAMENTE");
            item.obtenerInfoCompleta();

        } catch (Exception ex) {
            System.out.println("Error al actualizar item de venta: " + ex.getMessage());
        }
    }

    public static boolean eliminarItemVenta(List<ItemVenta> items, String itemId) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== ELIMINAR ITEM DE VENTA ===");

            // Buscar el item
            ItemVenta itemAEliminar = null;
            for (ItemVenta item : items) {
                if (item.getItemId().equals(itemId)) {
                    itemAEliminar = item;
                    break;
                }
            }

            if (itemAEliminar == null) {
                System.out.println("No se encontró el item con ID: " + itemId);
                return false;
            }

            // Mostrar información
            itemAEliminar.obtenerInfoCompleta();

            // Confirmación
            System.out.print("¿ESTÁ SEGURO DE ELIMINAR ESTE ITEM? (y/n): ");
            String confirmacion = sc.nextLine();

            if (confirmacion.equalsIgnoreCase("y")) {
                items.remove(itemAEliminar);
                System.out.println("✅ ITEM ELIMINADO CORRECTAMENTE");
                return true;
            } else {
                System.out.println("ELIMINACIÓN CANCELADA");
                return false;
            }

        } catch (Exception ex) {
            System.out.println("Error al eliminar item: " + ex.getMessage());
            return false;
        }
    }

    public static void mostrarTodosLosItems(List<ItemVenta> items) {
        if (items == null || items.isEmpty()) {
            System.out.println("No hay items de venta registrados");
            return;
        }

        System.out.println("=== LISTA DE ITEMS DE VENTA ===");
        double totalGeneral = 0;

        for (int i = 0; i < items.size(); i++) {
            ItemVenta item = items.get(i);
            System.out.printf("%d. %s - %d x %.2fBs. = %.2fBs.%n",
                    i + 1, item.getLibro().getTitulo(), item.getCantidad(),
                    item.getPrecioUnitario(), item.getSubtotal());
            totalGeneral += item.getSubtotal();
        }

        System.out.println("────────────────────────────────────");
        System.out.printf("TOTAL GENERAL: %.2fBs.%n", totalGeneral);
    }
}