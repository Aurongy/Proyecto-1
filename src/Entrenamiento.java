import java.time.LocalDate;

public class Entrenamiento {
    private LocalDate fecha;
    private String tipo;
    private double marca;

    public Entrenamiento(LocalDate fecha, String tipo, double marca) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.marca = marca;
    }

    public LocalDate getFecha() { return fecha; }
    public String getTipo() { return tipo; }
    public double getMarca() { return marca; }

    @Override
    public String toString() {
        return fecha + " - " + tipo + " - Marca: " + marca;
    }
}