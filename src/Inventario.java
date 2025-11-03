import java.util.List;
import java.util.Map;

public class Inventario {
    private Map<String,Libro> libros;
    private int stockMinimo;

    public Inventario(Map<String, Libro> libros, int stockMinimo) {
        this.libros = libros;
        this.stockMinimo = stockMinimo;
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

    @Override
    public String toString() {
        return "Inventario{" +
                "libros=" + libros +
                ", stockMinimo=" + stockMinimo +
                '}';
    }
}
