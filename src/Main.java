import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaAtletas sistema = new SistemaAtletas();
        sistema.cargarCSV();

        while (true) {
            System.out.println("\n--- Sistema de Monitoreo de Atletas ---");
            System.out.println("1. Registrar Atleta");
            System.out.println("2. Registrar Entrenamiento");
            System.out.println("3. Ver Historial");
            System.out.println("4. Ver Estadísticas");
            System.out.println("5. Guardar y Salir");
            int op = sc.nextInt(); sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Edad: ");
                    int edad = sc.nextInt(); sc.nextLine();
                    System.out.print("Disciplina: ");
                    String disciplina = sc.nextLine();
                    System.out.print("Departamento: ");
                    String departamento = sc.nextLine();
                    sistema.registrarAtleta(new Atleta(nombre, edad, disciplina, departamento));
                    break;

                case 2:
                    System.out.print("Nombre del Atleta: ");
                    String n = sc.nextLine();
                    Atleta atleta = sistema.buscarAtleta(n);
                    if (atleta != null) {
                        System.out.print("Fecha (YYYY-MM-DD): ");
                        LocalDate fecha = LocalDate.parse(sc.nextLine());
                        System.out.print("Tipo: ");
                        String tipo = sc.nextLine();
                        System.out.print("Marca: ");
                        double marca = sc.nextDouble(); sc.nextLine();
                        atleta.agregarEntrenamiento(new Entrenamiento(fecha, tipo, marca));
                    } else {
                        System.out.println("Atleta no encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Nombre del Atleta: ");
                    String na = sc.nextLine();
                    Atleta at = sistema.buscarAtleta(na);
                    if (at != null) at.mostrarHistorial();
                    else System.out.println("Atleta no encontrado.");
                    break;

                case 4:
                    System.out.print("Nombre del Atleta: ");
                    String nb = sc.nextLine();
                    Atleta at2 = sistema.buscarAtleta(nb);
                    if (at2 != null) {
                        System.out.println("Promedio: " + at2.calcularPromedio());
                        System.out.println("Mejor marca: " + at2.mejorMarca());
                    } else System.out.println("Atleta no encontrado.");
                    break;

                case 5:
                    sistema.guardarCSV();
                    System.out.println("Saliendo...");
                    return;
            }
        }
    }
}
