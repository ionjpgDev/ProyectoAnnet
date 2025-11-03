import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaLibreria {
    private Inventario inventario;
    private List<Cliente> clientes;
    private List<Venta> ventas;
    private List<Reporte> reportes;

    public SistemaLibreria() {
        this.inventario = null;
        this.clientes=new ArrayList<Cliente>();
        this.ventas=new ArrayList<Venta>();
        this.reportes=new ArrayList<Reporte>();
    }

    public SistemaLibreria(Inventario inventario, List<Cliente> clientes, List<Venta> ventas, List<Reporte> reportes) {
        this.inventario = inventario;
        this.clientes = clientes;
        this.ventas = ventas;
        this.reportes = reportes;
    }

    public int iniciarSistema(){
        System.out.println("---------- Sistema Libreria ----------");
        System.out.printf("Seleciones una opcion\n" +
                "1. Inventario\n" +
                "2. Ventas\n" +
                "3. Clientes\n" +
                "4. Reportes\n");
        Scanner sc=new Scanner(System.in);
        return sc.nextInt();
    }


}
