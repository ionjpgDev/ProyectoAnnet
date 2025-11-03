import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Libro {
    private String libroId, titulo, autor, editorial, isbn, categoria;
    private int stock, anioPublicacion;
    private double precio;


    public Libro(String titulo, String autor, String editorial, String isbn, String categoria, int anioPublicacion) {
        this.libroId = "LBR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.precio = 0;
        this.categoria = categoria;
        this.stock = 0;
        this.anioPublicacion = anioPublicacion;
    }

    public String getLibroId() {
        return libroId;
    }

    public void setLibroId(String libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getprecio() {
        return precio;
    }

    public void setprecio(double precio) {
        this.precio = precio;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }


    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void agregarStock(int cantidad) {
        this.stock = stock + cantidad;
        System.out.println("Stock actualizado");
    }

    public void reducirStock(int cantidad) {
        if (cantidad > stock) {
            System.out.println("Cantidad insuficiente en stock");
        } else {
            this.stock = stock - cantidad;
            System.out.println("Stock actualizado");
        }
    }


    public void obtenerInfoCompleta() {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│             INFORMACIÓN DEL LIBRO               │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│ %-15s: %-30s │%n", "ID Libro", libroId);
        System.out.printf("│ %-15s: %-30s │%n", "Título", titulo);
        System.out.printf("│ %-15s: %-30s │%n", "Autor", autor);
        System.out.printf("│ %-15s: %-30s │%n", "Editorial", editorial);
        System.out.printf("│ %-15s: %-30s │%n", "ISBN", isbn);
        System.out.printf("│ %-15s: %-30s │%n", "Categoría", categoria);
        System.out.printf("│ %-15s: %-30d │%n", "Stock", stock);
        System.out.printf("│ %-15s: $%-29.2f │%n", "Precio", precio);
        System.out.printf("│ %-15s: %-30s │%n", "Año publicación", anioPublicacion);
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    @Override
    public String toString() {
        return "Libro{" +
                "libroId='" + libroId + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editorial='" + editorial + '\'' +
                ", isbn='" + isbn + '\'' +
                ", categoria='" + categoria + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                ", anioPublicacion=" + anioPublicacion +
                '}';
    }

    public static Libro registrarLibro() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("INGRESE TITULO DEL LIBRO");
            String titulo = sc.nextLine();
            System.out.println("INGRESE AUTOR DEL LIBRO");
            String autor = sc.nextLine();
            System.out.println("INGRESE EDITORIAL DEL LIBRO");
            String editorial = sc.nextLine();
            System.out.println("INGRESE ISBN DEL LIBRO");
            String isbn = sc.nextLine();
            System.out.println("INGRESE LA CATEGORIA DEL LIBRO");
            String categoria = sc.nextLine();
            System.out.println("INGRESE LA AÑO DE PUBLICACIÓN DEL LIBRO");
            int anioPublicacion = sc.nextInt();
            sc.nextLine();
            Libro nue = new Libro(titulo, autor, editorial, isbn, categoria, anioPublicacion);
            System.out.println("DESEA AÑADIR EL PRECIO? (y/n): n");
            String op = sc.nextLine();
            if (op.equals("y")) {
                System.out.println("INTRODUCE PRECIO (123,42)");
                double precio = sc.nextDouble();
                sc.nextLine();
                nue.setprecio(precio);
                System.out.println("PRECIO AÑADIDO");
            }
            System.out.println("DESEA AÑADIR EL STOCK? (y/n): n");
            op = sc.nextLine();
            if (op.equals("y")) {
                System.out.println("INTRODUCE STOCK");
                int stock = sc.nextInt();
                sc.nextLine();
                nue.setStock(stock);
                System.out.println("STOCK AÑADIDO");
            }
            System.out.println("LIBRO REGISTRADO CORRECTAMENTE");
            return nue;
        } catch (Exception ex) {
            System.out.println("Datos no registrados intente de nuevo");
            return null;
        }


    }

    public static void actualizarLibro(Libro libroExistente) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("ACTUALIZAR LIBRO: " + libroExistente.getTitulo());
            System.out.println("(Deje en blanco para mantener valor actual)");

            // Título
            System.out.println("Título actual: " + libroExistente.getTitulo());
            System.out.print("Nuevo título: ");
            String nuevoTitulo = sc.nextLine();
            if (!nuevoTitulo.trim().isEmpty()) {
                libroExistente.setTitulo(nuevoTitulo);
            }

            // Autor
            System.out.println("Autor actual: " + libroExistente.getAutor());
            System.out.print("Nuevo autor: ");
            String nuevoAutor = sc.nextLine();
            if (!nuevoAutor.trim().isEmpty()) {
                libroExistente.setAutor(nuevoAutor);
            }

            // Editorial
            System.out.println("Editorial actual: " + libroExistente.getEditorial());
            System.out.print("Nueva editorial: ");
            String nuevaEditorial = sc.nextLine();
            if (!nuevaEditorial.trim().isEmpty()) {
                libroExistente.setEditorial(nuevaEditorial);
            }

            // ISBN
            System.out.println("ISBN actual: " + libroExistente.getIsbn());
            System.out.print("Nuevo ISBN: ");
            String nuevoIsbn = sc.nextLine();
            if (!nuevoIsbn.trim().isEmpty()) {
                libroExistente.setIsbn(nuevoIsbn);
            }

            // Categoría
            System.out.println("Categoría actual: " + libroExistente.getCategoria());
            System.out.print("Nueva categoría: ");
            String nuevaCategoria = sc.nextLine();
            if (!nuevaCategoria.trim().isEmpty()) {
                libroExistente.setCategoria(nuevaCategoria);
            }

            // Año
            System.out.println("Año actual: " + libroExistente.getAnioPublicacion());
            System.out.print("Nuevo año: ");
            String nuevoAnioStr = sc.nextLine();
            if (!nuevoAnioStr.trim().isEmpty()) {
                try {
                    int nuevoAnio = Integer.parseInt(nuevoAnioStr);
                    libroExistente.setAnioPublicacion(nuevoAnio);
                } catch (NumberFormatException e) {
                    System.out.println("Año invalido, se mantiene el actual");
                }
            }

            // Precio
            System.out.println("Precio actual: " + libroExistente.getprecio());
            System.out.print("Actualizar precio? (y/n): ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                System.out.print("Nuevo precio: ");
                try {
                    double nuevoPrecio = sc.nextDouble();
                    sc.nextLine();
                    libroExistente.setprecio(nuevoPrecio);
                    System.out.println("PRECIO ACTUALIZADO");
                } catch (Exception e) {
                    System.out.println("Precio invalido");
                    sc.nextLine();
                }
            }

            // Stock
            System.out.println("Stock actual: " + libroExistente.getStock());
            System.out.print("Actualizar stock? (y/n): ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                System.out.print("Nuevo stock: ");
                try {
                    int nuevoStock = sc.nextInt();
                    sc.nextLine();
                    libroExistente.setStock(nuevoStock);
                    System.out.println("STOCK ACTUALIZADO");
                } catch (Exception e) {
                    System.out.println("Stock invalido");
                    sc.nextLine();
                }
            }

            System.out.println("LIBRO ACTUALIZADO CORRECTAMENTE");

        } catch (Exception ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        }
    }
    public static boolean eliminarLibro(List<Libro> libros, String libroId) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("LIBRO A ELIMINAR: " + libroId);

            // Buscar el libro
            Libro libroAEliminar = null;
            for (Libro libro : libros) {
                if (libro.getLibroId().equals(libroId)) {
                    libroAEliminar = libro;
                    break;
                }
            }

            if (libroAEliminar == null) {
                System.out.println("No se encontro el libro con ID: " + libroId);
                return false;
            }

            // Mostrar información del libro
            libroAEliminar.obtenerInfoCompleta();

            // Confirmación
            System.out.print("¿ESTA SEGURO DE ELIMINAR ESTE LIBRO? (y/n): ");
            String confirmacion = sc.nextLine();

            if (confirmacion.equalsIgnoreCase("y")) {
                libros.remove(libroAEliminar);
                System.out.println("LIBRO ELIMINADO CORRECTAMENTE");
                return true;
            } else {
                System.out.println("ELIMINACION CANCELADA");
                return false;
            }

        } catch (Exception ex) {
            System.out.println("Error al eliminar libro: " + ex.getMessage());
            return false;
        }
    }

}

