import java.util.*;

public class Atleta {
    private String nombre;
    private int edad;
    private String disciplina;
    private String departamento;
    private List<Entrenamiento> entrenamientos;

    public Atleta(String nombre, int edad, String disciplina, String departamento) {
        this.nombre = nombre;
        this.edad = edad;
        this.disciplina = disciplina;
        this.departamento = departamento;
        this.entrenamientos = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public List<Entrenamiento> getEntrenamientos() { return entrenamientos; }

    public void agregarEntrenamiento(Entrenamiento e) {
        entrenamientos.add(e);
    }

    public void mostrarHistorial() {
        System.out.println("Historial de " + nombre + ":");
        entrenamientos.stream()
                .sorted(Comparator.comparing(Entrenamiento::getFecha))
                .forEach(System.out::println);
    }

    public double calcularPromedio() {
        return entrenamientos.stream()
                .mapToDouble(Entrenamiento::getMarca)
                .average()
                .orElse(0);
    }

    public double mejorMarca() {
        return entrenamientos.stream()
                .mapToDouble(Entrenamiento::getMarca)
                .min()
                .orElse(0);
    }
}
