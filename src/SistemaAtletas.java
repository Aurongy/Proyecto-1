import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class SistemaAtletas {
    private List<Atleta> atletas;
    private static final String ARCHIVO = "atletas.csv";

    public SistemaAtletas() {
        atletas = new ArrayList<>();
    }

    public void registrarAtleta(Atleta a) {
        atletas.add(a);
    }

    public Atleta buscarAtleta(String nombre) {
        return atletas.stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public void guardarCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Atleta a : atletas) {
                for (Entrenamiento e : a.getEntrenamientos()) {
                    pw.println(a.getNombre() + "," + a.calcularPromedio() + "," +
                            a.mejorMarca() + "," + e.getFecha() + "," +
                            e.getTipo() + "," + e.getMarca());
                }
            }
            System.out.println("Datos guardados en " + ARCHIVO);
        } catch (IOException ex) {
            System.out.println("Error al guardar CSV: " + ex.getMessage());
        }
    }

    public void cargarCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            Map<String, Atleta> mapa = new HashMap<>();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                LocalDate fecha = LocalDate.parse(datos[3]);
                String tipo = datos[4];
                double marca = Double.parseDouble(datos[5]);

                Atleta atleta = mapa.get(nombre);
                if (atleta == null) {
                    atleta = new Atleta(nombre, 0, "Desconocida", "Desconocido");
                    mapa.put(nombre, atleta);
                    atletas.add(atleta);
                }
                atleta.agregarEntrenamiento(new Entrenamiento(fecha, tipo, marca));
            }
            System.out.println("Datos cargados desde " + ARCHIVO);
        } catch (IOException e) {
            System.out.println("No se encontró archivo, iniciando vacío.");
        }
    }
}