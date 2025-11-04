import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Cliente {
    private String clienteId, nombre, email, telefono, direccion;
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

    // Constructor simplificado para registro nuevo
    public Cliente(String nombre, String email, String telefono, String direccion) {
        this.clienteId = "CLI-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.puntosAcumulado = 0;
        this.fechaRegistro = new Date();
        this.esFrecuente = false;
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

    public void obtenerInfoCompleta() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'a las' HH:mm");

        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│            INFORMACIÓN DEL CLIENTE              │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│ %-15s: %-30s │%n", "ID Cliente", clienteId);
        System.out.printf("│ %-15s: %-30s │%n", "Nombre", nombre);
        System.out.printf("│ %-15s: %-30s │%n", "Email", email);
        System.out.printf("│ %-15s: %-30s │%n", "Teléfono", telefono);
        System.out.printf("│ %-15s: %-30s │%n", "Dirección", direccion);
        System.out.printf("│ %-15s: %-30d │%n", "Puntos", puntosAcumulado);
        System.out.printf("│ %-15s: %-30s │%n", "Registro", sdf.format(fechaRegistro));
        System.out.printf("│ %-15s: %-30s │%n", "Cliente Frecuente", esFrecuente ? "SÍ" : "NO");
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    public void agregarPuntos(int puntos) {
        this.puntosAcumulado += puntos;
        // Si tiene más de 100 puntos, se convierte en cliente frecuente
        if (this.puntosAcumulado >= 100 && !this.esFrecuente) {
            this.esFrecuente = true;
            System.out.println("¡Felicidades! " + nombre + " ahora es cliente frecuente.");
        }
    }

    public boolean canjearPuntos(int puntos) {
        if (puntos <= puntosAcumulado) {
            this.puntosAcumulado -= puntos;
            return true;
        }
        return false;
    }

    public String getRut() {
        // Simular un RUT (en una aplicación real vendría de la base de datos)
        return "RUT-" + clienteId.substring(4);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Cliente{" +
                "clienteId='" + clienteId + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", puntos=" + puntosAcumulado +
                ", registro=" + sdf.format(fechaRegistro) +
                ", frecuente=" + esFrecuente +
                '}';
    }

    // MÉTODOS ESTÁTICOS

    public static Cliente registrarCliente() {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== REGISTRAR NUEVO CLIENTE ===");

            System.out.print("NOMBRE COMPLETO: ");
            String nombre = sc.nextLine();

            System.out.print("EMAIL: ");
            String email = sc.nextLine();

            System.out.print("TELÉFONO: ");
            String telefono = sc.nextLine();

            System.out.print("DIRECCIÓN: ");
            String direccion = sc.nextLine();

            Cliente nuevoCliente = new Cliente(nombre, email, telefono, direccion);
            System.out.println("✅ CLIENTE REGISTRADO CORRECTAMENTE");
            nuevoCliente.obtenerInfoCompleta();

            return nuevoCliente;

        } catch (Exception ex) {
            System.out.println("Error al registrar cliente: " + ex.getMessage());
            return null;
        }
    }

    public static void actualizarCliente(Cliente cliente) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== ACTUALIZAR CLIENTE ===");
            cliente.obtenerInfoCompleta();

            System.out.println("(Deje en blanco para mantener valor actual)");

            System.out.print("NUEVO NOMBRE: ");
            String nuevoNombre = sc.nextLine();
            if (!nuevoNombre.trim().isEmpty()) {
                cliente.setNombre(nuevoNombre);
            }

            System.out.print("NUEVO EMAIL: ");
            String nuevoEmail = sc.nextLine();
            if (!nuevoEmail.trim().isEmpty()) {
                cliente.setEmail(nuevoEmail);
            }

            System.out.print("NUEVO TELÉFONO: ");
            String nuevoTelefono = sc.nextLine();
            if (!nuevoTelefono.trim().isEmpty()) {
                cliente.setTelefono(nuevoTelefono);
            }

            System.out.print("NUEVA DIRECCIÓN: ");
            String nuevaDireccion = sc.nextLine();
            if (!nuevaDireccion.trim().isEmpty()) {
                cliente.setDireccion(nuevaDireccion);
            }

            System.out.println("✅ CLIENTE ACTUALIZADO CORRECTAMENTE");
            cliente.obtenerInfoCompleta();

        } catch (Exception ex) {
            System.out.println("Error al actualizar cliente: " + ex.getMessage());
        }
    }

    public static boolean eliminarCliente(List<Cliente> clientes, String clienteId) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== ELIMINAR CLIENTE ===");

            // Buscar el cliente
            Cliente clienteAEliminar = null;
            for (Cliente cliente : clientes) {
                if (cliente.getClienteId().equals(clienteId)) {
                    clienteAEliminar = cliente;
                    break;
                }
            }

            if (clienteAEliminar == null) {
                System.out.println("No se encontró el cliente con ID: " + clienteId);
                return false;
            }

            // Mostrar información
            clienteAEliminar.obtenerInfoCompleta();

            // Confirmación
            System.out.print("¿ESTÁ SEGURO DE ELIMINAR ESTE CLIENTE? (y/n): ");
            String confirmacion = sc.nextLine();

            if (confirmacion.equalsIgnoreCase("y")) {
                clientes.remove(clienteAEliminar);
                System.out.println("✅ CLIENTE ELIMINADO CORRECTAMENTE");
                return true;
            } else {
                System.out.println("ELIMINACIÓN CANCELADA");
                return false;
            }

        } catch (Exception ex) {
            System.out.println("Error al eliminar cliente: " + ex.getMessage());
            return false;
        }
    }

    public static void mostrarTodosLosClientes(List<Cliente> clientes) {
        if (clientes == null || clientes.isEmpty()) {
            System.out.println("No hay clientes registrados");
            return;
        }

        System.out.println("=== LISTA DE CLIENTES ===");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            System.out.printf("%d. %s - %s - %s - %d puntos - %s%n",
                    i + 1, cliente.getClienteId(), cliente.getNombre(),
                    cliente.getEmail(), cliente.getPuntosAcumulado(),
                    cliente.isEsFrecuente() ? "Frecuente" : "Normal");
        }
    }

    public static Cliente buscarClientePorId(List<Cliente> clientes, String clienteId) {
        for (Cliente cliente : clientes) {
            if (cliente.getClienteId().equalsIgnoreCase(clienteId)) {
                return cliente;
            }
        }
        return null;
    }

    public static Cliente buscarClientePorNombre(List<Cliente> clientes, String nombre) {
        List<Cliente> resultados = new ArrayList<>();

        for (Cliente cliente : clientes) {
            if (cliente.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultados.add(cliente);
            }
        }

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron clientes con ese nombre");
            return null;
        }

        if (resultados.size() == 1) {
            return resultados.get(0);
        }

        // Si hay múltiples resultados, mostrar opciones
        System.out.println("MÚLTIPLES CLIENTES ENCONTRADOS:");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.printf("%d. %s - %s%n", i + 1, resultados.get(i).getNombre(), resultados.get(i).getEmail());
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("SELECCIONE EL CLIENTE: ");
        int seleccion = sc.nextInt();
        sc.nextLine();

        if (seleccion >= 1 && seleccion <= resultados.size()) {
            return resultados.get(seleccion - 1);
        }

        return null;
    }

    public static void agregarPuntosCliente(Cliente cliente, int puntos) {
        cliente.agregarPuntos(puntos);
        System.out.println("✅ " + puntos + " puntos agregados a " + cliente.getNombre());
        System.out.println("Puntos totales: " + cliente.getPuntosAcumulado());
    }

    public static void canjearPuntosCliente(Cliente cliente, int puntos) {
        if (cliente.canjearPuntos(puntos)) {
            System.out.println("✅ " + puntos + " puntos canjeados por " + cliente.getNombre());
            System.out.println("Puntos restantes: " + cliente.getPuntosAcumulado());
        } else {
            System.out.println("❌ Puntos insuficientes. Puntos disponibles: " + cliente.getPuntosAcumulado());
        }
    }

    public static void mostrarClientesFrecuentes(List<Cliente> clientes) {
        List<Cliente> frecuentes = new ArrayList<>();

        for (Cliente cliente : clientes) {
            if (cliente.isEsFrecuente()) {
                frecuentes.add(cliente);
            }
        }

        if (frecuentes.isEmpty()) {
            System.out.println("No hay clientes frecuentes");
            return;
        }

        System.out.println("=== CLIENTES FRECUENTES ===");
        for (Cliente cliente : frecuentes) {
            System.out.printf("- %s - %s - %d puntos%n",
                    cliente.getNombre(), cliente.getEmail(), cliente.getPuntosAcumulado());
        }
    }
}