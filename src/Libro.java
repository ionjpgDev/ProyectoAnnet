import java.util.Date;

public class Libro {
    private String libroId,titulo,autor,editorial,isbn,categoria;
    private int stock;
    private double pracio;
    private Date fechaPublicacion;

    public Libro(String libroId, String titulo, String autor, String editorial, String isbn, double pracio, String categoria, int stock, Date fechaPublicacion) {
        this.libroId = libroId;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.pracio = pracio;
        this.categoria = categoria;
        this.stock = stock;
        this.fechaPublicacion = fechaPublicacion;
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

    public double getPracio() {
        return pracio;
    }

    public void setPracio(double pracio) {
        this.pracio = pracio;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
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
                ", pracio=" + pracio +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }

}
