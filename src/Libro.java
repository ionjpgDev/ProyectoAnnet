import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Libro {
    private String libroId,titulo,autor,editorial,isbn,categoria;
    private int stock,anioPublicacion;
    private double precio;


    public Libro(String titulo, String autor, String editorial, String isbn, String categoria, int anioPublicacion) {
        this.libroId = "LBR-"+ UUID.randomUUID().toString().substring(0,8).toUpperCase();
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

    public void agregarStock(int cantidad){
        this.stock=stock+cantidad;
        System.out.println("Stock actualizado");
    }
    public void reducirStock(int cantidad){
        if (cantidad>stock){
            System.out.println("Cantidad insuficiente en stock");
        }
        else {
            this.stock=stock-cantidad;
            System.out.println("Stock actualizado");
        }
    }


    public void obtenerInfoCompleta(){
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│             INFORMACIÓN DEL LIBRO               │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf ("│ %-15s: %-30s │%n", "ID Libro", libroId);
        System.out.printf ("│ %-15s: %-30s │%n", "Título", titulo);
        System.out.printf ("│ %-15s: %-30s │%n", "Autor", autor);
        System.out.printf ("│ %-15s: %-30s │%n", "Editorial", editorial);
        System.out.printf ("│ %-15s: %-30s │%n", "ISBN", isbn);
        System.out.printf ("│ %-15s: %-30s │%n", "Categoría", categoria);
        System.out.printf ("│ %-15s: %-30d │%n", "Stock", stock);
        System.out.printf ("│ %-15s: $%-29.2f │%n", "Precio", precio);
        System.out.printf ("│ %-15s: %-30s │%n", "Año publicación", anioPublicacion);
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
    public static Libro registrarLibro(){
        try {
            Scanner sc=new Scanner(System.in);
            System.out.println("INGRESE TITULO DEL LIBRO");
            String titulo=sc.nextLine();
            System.out.println("INGRESE AUTOR DEL LIBRO");
            String autor=sc.nextLine();
            System.out.println("INGRESE EDITORIAL DEL LIBRO");
            String editorial=sc.nextLine();
            System.out.println("INGRESE ISBN DEL LIBRO");
            String isbn=sc.nextLine();
            System.out.println("INGRESE LA CATEGORIA DEL LIBRO");
            String categoria=sc.nextLine();
            System.out.println("INGRESE LA AÑO DE PUBLICACIÓN DEL LIBRO");
            int anioPublicacion=sc.nextInt();
            Libro nue=new Libro(titulo,autor,editorial,isbn,categoria,anioPublicacion);
            System.out.println("LIBRO REGISTRADO CORRECTAMENTE");
            return nue;
        }catch (Exception ex ){
            System.out.println("Datos no registrados intente de nuevo");
            return null;
        }


    }
}
