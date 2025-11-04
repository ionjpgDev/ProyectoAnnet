import java.util.*;
import java.util.stream.Collectors;

public class Inventario {
    private Map<String, Libro> libros;
    private int stockMinimo;

    public Inventario(Map<String, Libro> libros, int stockMinimo) {
        this.libros = libros;
        this.stockMinimo = stockMinimo;
    }

    // Constructor para inventario vacío
    public Inventario() {
        this.libros = new HashMap<>();
        this.stockMinimo = 5; // Stock mínimo por defecto
    }

    public Map<String, Libro> getLibros() {
        return libros;
    }

    public void setLibros(Map<String, Libro> libros) {
        this.libros = libros;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    // MÉTODOS DE GESTIÓN DE INVENTARIO

    public void agregarLibro(Libro libro) {
        libros.put(libro.getLibroId(), libro);
        System.out.println("Libro agregado al inventario: " + libro.getTitulo());
    }

    public boolean eliminarLibro(String libroId) {
        Libro libroEliminado = libros.remove(libroId);
        if (libroEliminado != null) {
            System.out.println("Libro eliminado del inventario: " + libroEliminado.getTitulo());
            return true;
        } else {
            System.out.println("No se encontró el libro con ID: " + libroId);
            return false;
        }
    }

    public Libro buscarLibroPorId(String libroId) {
        return libros.get(libroId);
    }

    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        return libros.values().stream()
                .filter(libro -> libro.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Libro> buscarLibrosPorAutor(String autor) {
        return libros.values().stream()
                .filter(libro -> libro.getAutor().toLowerCase().contains(autor.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Libro> buscarLibrosPorCategoria(String categoria) {
        return libros.values().stream()
                .filter(libro -> libro.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    public void actualizarStock(String libroId, int nuevaCantidad) {
        Libro libro = libros.get(libroId);
        if (libro != null) {
            libro.setStock(nuevaCantidad);
            System.out.println("Stock actualizado para: " + libro.getTitulo() + " - Nuevo stock: " + nuevaCantidad);
        } else {
            System.out.println("No se encontró el libro con ID: " + libroId);
        }
    }

    public void agregarStock(String libroId, int cantidad) {
        Libro libro = libros.get(libroId);
        if (libro != null) {
            libro.agregarStock(cantidad);
            System.out.println("Stock agregado para: " + libro.getTitulo() + " - Stock actual: " + libro.getStock());
        } else {
            System.out.println("No se encontró el libro con ID: " + libroId);
        }
    }

    public boolean reducirStock(String libroId, int cantidad) {
        Libro libro = libros.get(libroId);
        if (libro != null) {
            if (libro.getStock() >= cantidad) {
                libro.reducirStock(cantidad);
                System.out.println("Stock reducido para: " + libro.getTitulo() + " - Stock actual: " + libro.getStock());
                return true;
            } else {
                System.out.println("Stock insuficiente para: " + libro.getTitulo() + " - Stock disponible: " + libro.getStock());
                return false;
            }
        } else {
            System.out.println("No se encontró el libro con ID: " + libroId);
            return false;
        }
    }

    public List<Libro> obtenerLibrosBajoStock() {
        return libros.values().stream()
                .filter(libro -> libro.getStock() <= stockMinimo)
                .collect(Collectors.toList());
    }

    public List<Libro> obtenerLibrosAgotados() {
        return libros.values().stream()
                .filter(libro -> libro.getStock() == 0)
                .collect(Collectors.toList());
    }

    public int obtenerCantidadTotalLibros() {
        return libros.size();
    }

    public int obtenerStockTotal() {
        return libros.values().stream()
                .mapToInt(Libro::getStock)
                .sum();
    }

    public double obtenerValorTotalInventario() {
        return libros.values().stream()
                .mapToDouble(libro -> libro.getprecio() * libro.getStock())
                .sum();
    }

    public void mostrarInventarioCompleto() {
        System.out.println("=== INVENTARIO COMPLETO ===");
        System.out.println("Total de libros: " + obtenerCantidadTotalLibros());
        System.out.println("Stock total: " + obtenerStockTotal());
        System.out.printf("Valor total del inventario: %.2fBs.%n", obtenerValorTotalInventario());
        System.out.println("Stock mínimo configurado: " + stockMinimo);
        System.out.println("─────────────────────────────────────────────────────────────────────");

        if (libros.isEmpty()) {
            System.out.println("El inventario está vacío");
            return;
        }

        libros.values().forEach(libro -> {
            System.out.printf("- %s | %s | Stock: %d | Precio: %.2fBs.%n",
                    libro.getLibroId(), libro.getTitulo(),
                    libro.getStock(), libro.getprecio());
        });
    }

    public void mostrarLibrosBajoStock() {
        List<Libro> librosBajoStock = obtenerLibrosBajoStock();

        if (librosBajoStock.isEmpty()) {
            System.out.println("No hay libros con stock bajo");
            return;
        }

        System.out.println("=== LIBROS CON STOCK BAJO (≤ " + stockMinimo + ") ===");
        librosBajoStock.forEach(libro -> {
            System.out.printf("- %s | %s | Stock: %d (Mínimo: %d)%n",
                    libro.getLibroId(), libro.getTitulo(),
                    libro.getStock(), stockMinimo);
        });
    }

    public void mostrarLibrosAgotados() {
        List<Libro> librosAgotados = obtenerLibrosAgotados();

        if (librosAgotados.isEmpty()) {
            System.out.println("No hay libros agotados");
            return;
        }

        System.out.println("=== LIBROS AGOTADOS ===");
        librosAgotados.forEach(libro -> {
            System.out.printf("- %s | %s | Precio: %.2fBs.%n",
                    libro.getLibroId(), libro.getTitulo(), libro.getprecio());
        });
    }

    public void mostrarEstadisticas() {
        System.out.println("=== ESTADÍSTICAS DEL INVENTARIO ===");
        System.out.println("Total de libros diferentes: " + obtenerCantidadTotalLibros());
        System.out.println("Stock total en unidades: " + obtenerStockTotal());
        System.out.printf("Valor total del inventario: %.2fBs.%n", obtenerValorTotalInventario());

        List<Libro> librosBajoStock = obtenerLibrosBajoStock();
        List<Libro> librosAgotados = obtenerLibrosAgotados();

        System.out.println("Libros con stock bajo: " + librosBajoStock.size());
        System.out.println("Libros agotados: " + librosAgotados.size());

        // Libro más caro
        Optional<Libro> libroMasCaro = libros.values().stream()
                .max(Comparator.comparingDouble(Libro::getprecio));
        libroMasCaro.ifPresent(libro ->
                System.out.printf("Libro más caro: %s (%.2fBs.)%n", libro.getTitulo(), libro.getprecio()));

        // Libro con mayor stock
        Optional<Libro> libroMayorStock = libros.values().stream()
                .max(Comparator.comparingInt(Libro::getStock));
        libroMayorStock.ifPresent(libro ->
                System.out.printf("Libro con mayor stock: %s (%d unidades)%n", libro.getTitulo(), libro.getStock()));
    }

    // MÉTODOS ESTÁTICOS

    public static void gestionarInventario(Inventario inventario) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== GESTIÓN DE INVENTARIO ===");
            System.out.println("1. Mostrar inventario completo");
            System.out.println("2. Buscar libro por ID");
            System.out.println("3. Buscar libros por título");
            System.out.println("4. Buscar libros por autor");
            System.out.println("5. Buscar libros por categoría");
            System.out.println("6. Mostrar libros con stock bajo");
            System.out.println("7. Mostrar libros agotados");
            System.out.println("8. Mostrar estadísticas");
            System.out.println("9. Agregar stock a libro");
            System.out.println("10. Configurar stock mínimo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        inventario.mostrarInventarioCompleto();
                        break;
                    case 2:
                        System.out.print("Ingrese ID del libro: ");
                        String id = sc.nextLine();
                        Libro libro = inventario.buscarLibroPorId(id);
                        if (libro != null) {
                            libro.obtenerInfoCompleta();
                        } else {
                            System.out.println("Libro no encontrado");
                        }
                        break;
                    case 3:
                        System.out.print("Ingrese título a buscar: ");
                        String titulo = sc.nextLine();
                        List<Libro> librosTitulo = inventario.buscarLibrosPorTitulo(titulo);
                        mostrarResultadosBusqueda(librosTitulo, "por título: " + titulo);
                        break;
                    case 4:
                        System.out.print("Ingrese autor a buscar: ");
                        String autor = sc.nextLine();
                        List<Libro> librosAutor = inventario.buscarLibrosPorAutor(autor);
                        mostrarResultadosBusqueda(librosAutor, "por autor: " + autor);
                        break;
                    case 5:
                        System.out.print("Ingrese categoría a buscar: ");
                        String categoria = sc.nextLine();
                        List<Libro> librosCategoria = inventario.buscarLibrosPorCategoria(categoria);
                        mostrarResultadosBusqueda(librosCategoria, "por categoría: " + categoria);
                        break;
                    case 6:
                        inventario.mostrarLibrosBajoStock();
                        break;
                    case 7:
                        inventario.mostrarLibrosAgotados();
                        break;
                    case 8:
                        inventario.mostrarEstadisticas();
                        break;
                    case 9:
                        System.out.print("Ingrese ID del libro: ");
                        String libroId = sc.nextLine();
                        System.out.print("Ingrese cantidad a agregar: ");
                        int cantidad = sc.nextInt();
                        sc.nextLine();
                        inventario.agregarStock(libroId, cantidad);
                        break;
                    case 10:
                        System.out.print("Ingrese nuevo stock mínimo: ");
                        int nuevoMinimo = sc.nextInt();
                        sc.nextLine();
                        inventario.setStockMinimo(nuevoMinimo);
                        System.out.println("Stock mínimo configurado a: " + nuevoMinimo);
                        break;
                    case 0:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine(); // Limpiar buffer
            }
        }
    }

    private static void mostrarResultadosBusqueda(List<Libro> libros, String criterio) {
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros " + criterio);
            return;
        }

        System.out.println("=== RESULTADOS DE BÚSQUEDA " + criterio.toUpperCase() + " ===");
        libros.forEach(libro -> {
            System.out.printf("- %s | %s | %s | Stock: %d | Precio: %.2fBs.%n",
                    libro.getLibroId(), libro.getTitulo(), libro.getAutor(),
                    libro.getStock(), libro.getprecio());
        });
        System.out.println("Total encontrado: " + libros.size() + " libros");
    }

    public static void inicializarInventarioDemo(Inventario inventario) {
        // Libros de demostración
        Libro[] librosDemo = {
                new Libro("Cien Años de Soledad", "Gabriel García Márquez", "Sudamericana", "978-8437604947", "Realismo Mágico", 1967),
                new Libro("1984", "George Orwell", "Debolsillo", "978-8499890944", "Ciencia Ficción", 1949),
                new Libro("El Principito", "Antoine de Saint-Exupéry", "Salamandra", "978-8498381490", "Infantil", 1943),
                new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "Editorial Castalia", "978-8470394837", "Clásico", 1605),
                new Libro("Crimen y Castigo", "Fiódor Dostoievski", "Alianza Editorial", "978-8420674202", "Clásico", 1866)
        };

        // Configurar precios y stock
        librosDemo[0].setprecio(25.50);
        librosDemo[0].setStock(8);

        librosDemo[1].setprecio(18.75);
        librosDemo[1].setStock(3); // Stock bajo

        librosDemo[2].setprecio(12.99);
        librosDemo[2].setStock(15);

        librosDemo[3].setprecio(35.00);
        librosDemo[3].setStock(0); // Agotado

        librosDemo[4].setprecio(22.50);
        librosDemo[4].setStock(6);

        // Agregar al inventario
        for (Libro libro : librosDemo) {
            inventario.agregarLibro(libro);
        }

        System.out.println("Inventario de demostración inicializado con " + librosDemo.length + " libros");
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "totalLibros=" + obtenerCantidadTotalLibros() +
                ", stockTotal=" + obtenerStockTotal() +
                ", valorTotal=" + obtenerValorTotalInventario() +
                ", stockMinimo=" + stockMinimo +
                '}';
    }
}