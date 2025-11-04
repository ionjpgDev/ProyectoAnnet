import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaLibreria {
    private Inventario inventario;
    private List<Cliente> clientes;
    private List<Venta> ventas;
    private List<Transaccion> transacciones;
    private List<Reporte> reportes;

    public SistemaLibreria() {
        this.inventario = new Inventario();
        this.clientes = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.transacciones = new ArrayList<>();
        this.reportes = new ArrayList<>();
    }

    public void iniciarSistema() {
        Scanner sc = new Scanner(System.in);
        boolean sistemaActivo = true;

        // Inicializar datos de demostración
        inicializarDatosDemo();

        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║           SISTEMA DE GESTIÓN DE LIBRERÍA       ║");
        System.out.println("║                  BIENVENIDO                    ║");
        System.out.println("╚════════════════════════════════════════════════╝");

        while (sistemaActivo) {
            System.out.println("\n════════════════ MENÚ PRINCIPAL ════════════════");
            System.out.println("1. 📚 GESTIÓN DE INVENTARIO");
            System.out.println("2. 💰 GESTIÓN DE VENTAS");
            System.out.println("3. 👥 GESTIÓN DE CLIENTES");
            System.out.println("4. 📊 GESTIÓN DE REPORTES");
            System.out.println("5. 💳 GESTIÓN DE TRANSACCIONES");
            System.out.println("0. 🚪 SALIR DEL SISTEMA");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = sc.nextInt();
                sc.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        menuInventario();
                        break;
                    case 2:
                        menuVentas();
                        break;
                    case 3:
                        menuClientes();
                        break;
                    case 4:
                        menuReportes();
                        break;
                    case 5:
                        menuTransacciones();
                        break;
                    case 0:
                        sistemaActivo = false;
                        System.out.println("¡Gracias por usar el Sistema de Gestión de Librería!");
                        break;
                    default:
                        System.out.println("❌ Opción inválida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: Ingrese un número válido.");
                sc.nextLine(); // Limpiar buffer en caso de error
            }
        }
        sc.close();
    }

    private void menuInventario() {
        Scanner sc = new Scanner(System.in);
        boolean enMenu = true;

        while (enMenu) {
            System.out.println("\n════════════ GESTIÓN DE INVENTARIO ════════════");
            System.out.println("1. 📥 Registrar nuevo libro");
            System.out.println("2. 📋 Mostrar inventario completo");
            System.out.println("3. 🔍 Buscar libro");
            System.out.println("4. 📈 Mostrar libros con stock bajo");
            System.out.println("5. 📉 Mostrar libros agotados");
            System.out.println("6. 📊 Mostrar estadísticas");
            System.out.println("7. ⚙️  Gestión avanzada de inventario");
            System.out.println("0. ↩️  Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    Libro nuevoLibro = Libro.registrarLibro();
                    if (nuevoLibro != null) {
                        inventario.agregarLibro(nuevoLibro);
                    }
                    break;
                case 2:
                    inventario.mostrarInventarioCompleto();
                    break;
                case 3:
                    menuBuscarLibro();
                    break;
                case 4:
                    inventario.mostrarLibrosBajoStock();
                    break;
                case 5:
                    inventario.mostrarLibrosAgotados();
                    break;
                case 6:
                    inventario.mostrarEstadisticas();
                    break;
                case 7:
                    Inventario.gestionarInventario(inventario);
                    break;
                case 0:
                    enMenu = false;
                    break;
                default:
                    System.out.println("❌ Opción inválida.");
            }
        }
    }

    private void menuBuscarLibro() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n🔍 BUSCAR LIBRO");
        System.out.println("1. Por ID");
        System.out.println("2. Por título");
        System.out.println("3. Por autor");
        System.out.println("4. Por categoría");
        System.out.print("Seleccione: ");

        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese ID del libro: ");
                String id = sc.nextLine();
                Libro libro = inventario.buscarLibroPorId(id);
                if (libro != null) {
                    libro.obtenerInfoCompleta();
                } else {
                    System.out.println("❌ Libro no encontrado.");
                }
                break;
            case 2:
                System.out.print("Ingrese título: ");
                String titulo = sc.nextLine();
                List<Libro> resultados = inventario.buscarLibrosPorTitulo(titulo);
                mostrarResultadosBusqueda(resultados);
                break;
            case 3:
                System.out.print("Ingrese autor: ");
                String autor = sc.nextLine();
                resultados = inventario.buscarLibrosPorAutor(autor);
                mostrarResultadosBusqueda(resultados);
                break;
            case 4:
                System.out.print("Ingrese categoría: ");
                String categoria = sc.nextLine();
                resultados = inventario.buscarLibrosPorCategoria(categoria);
                mostrarResultadosBusqueda(resultados);
                break;
            default:
                System.out.println("❌ Opción inválida.");
        }
    }

    private void mostrarResultadosBusqueda(List<Libro> resultados) {
        if (resultados.isEmpty()) {
            System.out.println("❌ No se encontraron libros.");
            return;
        }
        System.out.println("\n📚 LIBROS ENCONTRADOS:");
        resultados.forEach(libro -> {
            System.out.printf("- %s | %s | %s | Stock: %d | Precio: %.2fBs.%n",
                    libro.getLibroId(), libro.getTitulo(), libro.getAutor(),
                    libro.getStock(), libro.getprecio());
        });
    }

    private void menuVentas() {
        Scanner sc = new Scanner(System.in);
        boolean enMenu = true;

        while (enMenu) {
            System.out.println("\n══════════════ GESTIÓN DE VENTAS ══════════════");
            System.out.println("1. 🛒 Crear nueva venta");
            System.out.println("2. 📋 Mostrar todas las ventas");
            System.out.println("3. 🔍 Buscar venta por ID");
            System.out.println("4. 👤 Mostrar ventas por cliente");
            System.out.println("5. 📊 Mostrar ventas por estado");
            System.out.println("6. ✏️ Actualizar venta");
            System.out.println("7. ❌ Cancelar venta");
            System.out.println("0. ↩️ Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    Venta nuevaVenta = Venta.crearVenta(clientes, new ArrayList<>(inventario.getLibros().values()));
                    if (nuevaVenta != null) {
                        ventas.add(nuevaVenta);
                        // Crear transacción automática
                        Transaccion transaccionVenta = Transaccion.crearTransaccionVenta(nuevaVenta, "Sistema");
                        transacciones.add(transaccionVenta);
                    }
                    break;
                case 2:
                    Venta.mostrarTodasLasVentas(ventas);
                    break;
                case 3:
                    System.out.print("Ingrese ID de venta: ");
                    String ventaId = sc.nextLine();
                    Venta ventaEncontrada = Venta.buscarVentaPorId(ventas, ventaId);
                    if (ventaEncontrada != null) {
                        ventaEncontrada.mostrarInformacion();
                    }
                    break;
                case 4:
                    System.out.print("Ingrese nombre del cliente: ");
                    String nombreCliente = sc.nextLine();
                    Venta.mostrarVentasPorCliente(ventas, nombreCliente);
                    break;
                case 5:
                    System.out.print("Ingrese estado (PENDIENTE/COMPLETADA/CANCELADA): ");
                    String estado = sc.nextLine();
                    Venta.mostrarVentasPorEstado(ventas, estado);
                    break;
                case 6:
                    System.out.print("Ingrese ID de venta a actualizar: ");
                    String idActualizar = sc.nextLine();
                    Venta ventaActualizar = Venta.buscarVentaPorId(ventas, idActualizar);
                    if (ventaActualizar != null) {
                        Venta.actualizarVenta(ventaActualizar, new ArrayList<>(inventario.getLibros().values()));
                    }
                    break;
                case 7:
                    System.out.print("Ingrese ID de venta a cancelar: ");
                    String idCancelar = sc.nextLine();
                    Venta ventaCancelar = Venta.buscarVentaPorId(ventas, idCancelar);
                    if (ventaCancelar != null) {
                        Venta.cancelarVenta(ventaCancelar);
                    }
                    break;
                case 0:
                    enMenu = false;
                    break;
                default:
                    System.out.println("❌ Opción inválida.");
            }
        }
    }

    private void menuClientes() {
        Scanner sc = new Scanner(System.in);
        boolean enMenu = true;

        while (enMenu) {
            System.out.println("\n═════════════ GESTIÓN DE CLIENTES ═════════════");
            System.out.println("1. 👤 Registrar nuevo cliente");
            System.out.println("2. 📋 Mostrar todos los clientes");
            System.out.println("3. 🔍 Buscar cliente");
            System.out.println("4. ✏️ Actualizar cliente");
            System.out.println("5. 🎯 Mostrar clientes frecuentes");
            System.out.println("6. ⭐ Agregar puntos a cliente");
            System.out.println("7. 🎁 Canjear puntos de cliente");
            System.out.println("0. ↩️ Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    Cliente nuevoCliente = Cliente.registrarCliente();
                    if (nuevoCliente != null) {
                        clientes.add(nuevoCliente);
                    }
                    break;
                case 2:
                    Cliente.mostrarTodosLosClientes(clientes);
                    break;
                case 3:
                    menuBuscarCliente();
                    break;
                case 4:
                    System.out.print("Ingrese ID del cliente a actualizar: ");
                    String idCliente = sc.nextLine();
                    Cliente clienteActualizar = Cliente.buscarClientePorId(clientes, idCliente);
                    if (clienteActualizar != null) {
                        Cliente.actualizarCliente(clienteActualizar);
                    }
                    break;
                case 5:
                    Cliente.mostrarClientesFrecuentes(clientes);
                    break;
                case 6:
                    System.out.print("Ingrese ID del cliente: ");
                    String idPuntos = sc.nextLine();
                    Cliente clientePuntos = Cliente.buscarClientePorId(clientes, idPuntos);
                    if (clientePuntos != null) {
                        System.out.print("Ingrese puntos a agregar: ");
                        int puntos = sc.nextInt();
                        sc.nextLine();
                        Cliente.agregarPuntosCliente(clientePuntos, puntos);
                    }
                    break;
                case 7:
                    System.out.print("Ingrese ID del cliente: ");
                    String idCanje = sc.nextLine();
                    Cliente clienteCanje = Cliente.buscarClientePorId(clientes, idCanje);
                    if (clienteCanje != null) {
                        System.out.print("Ingrese puntos a canjear: ");
                        int puntosCanje = sc.nextInt();
                        sc.nextLine();
                        Cliente.canjearPuntosCliente(clienteCanje, puntosCanje);
                    }
                    break;
                case 0:
                    enMenu = false;
                    break;
                default:
                    System.out.println("❌ Opción inválida.");
            }
        }
    }

    private void menuBuscarCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n🔍 BUSCAR CLIENTE");
        System.out.println("1. Por ID");
        System.out.println("2. Por nombre");
        System.out.print("Seleccione: ");

        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese ID del cliente: ");
                String id = sc.nextLine();
                Cliente cliente = Cliente.buscarClientePorId(clientes, id);
                if (cliente != null) {
                    cliente.obtenerInfoCompleta();
                }
                break;
            case 2:
                System.out.print("Ingrese nombre del cliente: ");
                String nombre = sc.nextLine();
                Cliente clienteNombre = Cliente.buscarClientePorNombre(clientes, nombre);
                if (clienteNombre != null) {
                    clienteNombre.obtenerInfoCompleta();
                }
                break;
            default:
                System.out.println("❌ Opción inválida.");
        }
    }

    private void menuReportes() {
        Reporte.mostrarMenuReportes(ventas, inventario, clientes, transacciones);
    }

    private void menuTransacciones() {
        Transaccion.gestionarTransacciones(transacciones);
    }

    private void inicializarDatosDemo() {
        System.out.println("🔄 Inicializando datos de demostración...");

        // Inicializar inventario
        Inventario.inicializarInventarioDemo(inventario);

        // Crear clientes
        Cliente[] clientesDemo = {
                new Cliente("Juan Pérez", "juan@email.com", "777-1234", "Av. Siempre Viva 123"),
                new Cliente("María García", "maria@email.com", "777-5678", "Calle Falsa 456"),
                new Cliente("Carlos López", "carlos@email.com", "777-9012", "Plaza Central 789")

        };

        for (Cliente cliente : clientesDemo) {
            clientes.add(cliente);
        }

        // Agregar puntos a algunos clientes
        clientes.get(0).agregarPuntos(50);
        clientes.get(1).agregarPuntos(120); // Cliente frecuente

        System.out.println("✅ Datos de demostración cargados:");
        System.out.println("   - " + inventario.getLibros().size() + " libros en inventario");
        System.out.println("   - " + clientes.size() + " clientes registrados");
    }

    // MAIN para ejecutar el sistema
    public static void main(String[] args) {
        SistemaLibreria sistema = new SistemaLibreria();
        sistema.iniciarSistema();
    }
}