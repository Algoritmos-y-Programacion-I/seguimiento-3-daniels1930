package ui;

import java.time.LocalDate;
import java.util.Scanner;
import model.SchoolController;

public class SchoolApp {

    private final SchoolController computaricemos;
    private final Scanner input;

    public static void main(String[] args) {
        SchoolApp ui = new SchoolApp();
        ui.menu();
    }

    public SchoolApp() {
        input = new Scanner(System.in);
        computaricemos = new SchoolController(5, 10);
    }

    public void menu() {
        System.out.println("Bienvenido a Computaricemos");

        int option;
        do {
            System.out.println("\nMenu Principal");
            System.out.println("--------------------------------------------------------");
            System.out.println("Digite alguna de las siguientes opciones");
            System.out.println("1) Registrar computador");
            System.out.println("2) Registrar incidente en computador");
            System.out.println("3) Consultar el computador con mas incidentes");
            System.out.println("4) Resolver un incidente");
            System.out.println("0) Salir del sistema");
            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    registrarComputador();
                    break;
                case 2:
                    registrarIncidenteEnComputador();
                    break;
                case 3:
                    consultarComputadorConMasIncidentes();
                    break;
                case 4:
                    resolverIncidente();
                    break;
                case 0:
                    System.out.println("\nGracias por usar nuestros servicios. Adios!");
                    break;
                default:
                    System.out.println("\nOpcion invalida. Intente nuevamente.");
                    break;
            }

        } while (option != 0);
    }

    public void registrarComputador() {
        System.out.print("Ingrese el serial del computador: ");
        String serial = input.nextLine();
        System.out.print("Ingrese el piso (0-4): ");
        int floor = input.nextInt();
        input.nextLine();
        String result = computaricemos.addComputer(serial, floor);
        System.out.println(result);
    }

    public void registrarIncidenteEnComputador() {
        System.out.print("Ingrese la fecha del reporte (AAAA-MM-DD): ");
        String dataReport = input.nextLine();
        LocalDate date = LocalDate.parse(dataReport);
        System.out.print("Ingrese el serial del computador: ");
        String serial = input.nextLine();
        System.out.print("Ingrese la descripcion del incidente: ");
        String description = input.nextLine();
        String result = computaricemos.addIncidentComputer(date, serial, description);
        System.out.println(result);
    }

    public void consultarComputadorConMasIncidentes() {
        String result = computaricemos.getComputerWithMostIncidents();
        System.out.println(result);
    }

    public void resolverIncidente() {
        System.out.print("Ingrese el serial del computador: ");
        String serial = input.nextLine();
        System.out.print("Ingrese el indice del incidente: ");
        int index = input.nextInt();
        System.out.print("Ingrese las horas invertidas: ");
        int hours = input.nextInt();
        input.nextLine();
        String result = computaricemos.solutionIncident(serial, index, hours);
        System.out.println(result);
    }
}