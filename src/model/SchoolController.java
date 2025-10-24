package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class SchoolController {

    private final Computer[][] matrix;
    private final ArrayList<Computer> computers;
    private int totalSupportHours;

    // Constantes del sistema
    public static final int FLOORS = 5;
    public static final int COLUMNS = 10;
    public static final int HOURMAXSUPPORT = 100;

    public SchoolController(int floors, int columns) {
        matrix = new Computer[floors][columns];
        computers = new ArrayList<>();
        totalSupportHours = 0;
    }

    /**
     * Agrega un computador al sistema.
     * @param serial 
     * @param floor 
     * @return Mensaje con el resultado de la operación
     * Pre: serial != null, 0 <= floor < número de pisos
     * Post: Computador agregado si serial único y hay espacio
     */
    public String addComputer(String serial, int floor) {
        String message;

        // Validación de piso fuera de rango
        if (floor < 0 || floor >= matrix.length) {
            return "Número de piso inválido. Debe ser entre 0 y " + (matrix.length - 1);
        }

        for (Computer c : computers) {
            if (c.getSerial().equals(serial)) {
                message = "El serial ya existe.";
                return message;
            }
        }

        for (int col = 0; col < matrix[floor].length; col++) {
            if (matrix[floor][col] == null) {
                Computer comp = new Computer(serial, floor, col);
                matrix[floor][col] = comp;
                computers.add(comp);
                message = "Computador añadido exitosamente en el piso " + floor + ", columna " + col;
                return message;
            }
        }

        message = "No hay espacio disponible en este piso.";
        return message;
    }

    /**
     * Registra un incidente para un computador.
     * @param date
     * @param serial 
     * @param description
     * @return Mensaje con el resultado de la operación
     * Pre: serial != null, description != null
     * Post: Incidente agregado si el computador existe
     */
    public String addIncidentComputer(LocalDate date, String serial, String description) {
        String message;
        Computer comp = findComputer(serial);
        if (comp != null) {
            comp.addIncident(new Incident(description));
            message = "Incidente registrado para el computador con serial " + serial;
        } else {
            message = "Computador no encontrado.";
        }
        return message;
    }

    /**
     * Resuelve un incidente específico.
     * @param serial 
     * @param index 
     * @param hours 
     * @return Mensaje con el resultado de la operación
     * Pre: serial != null, index >= 0, hours >= 0
     * Post: Incidente marcado como resuelto si existe
     */
    public String solutionIncident(String serial, int index, int hours) {
        String message;
        Computer comp = findComputer(serial);
        if (comp != null && index >= 0 && index < comp.getIncidents().size()) {

            // Verificar límite de horas
            if (totalSupportHours + hours > HOURMAXSUPPORT) {
                return "No se puede registrar la solución. Se superarían las " + HOURMAXSUPPORT + " horas de soporte.";
            }

            comp.getIncidents().get(index).resolve(hours);
            totalSupportHours += hours;

            message = "Incidente resuelto para el computador con serial " + serial +
                       ". Total de horas usadas: " + totalSupportHours + "/" + HOURMAXSUPPORT;

        } else {
            message = "Computador o incidente no encontrado.";
        }
        return message;
    }

    /**
     * Encuentra el computador con más incidentes.
     * @return String con información del computador con más incidentes
     * Pre: Ninguna
     * Post: Retorna info del computador con más incidentes o mensaje si no hay
     */
    public String getComputerWithMostIncidents() {
        String message;
        if (computers.isEmpty()) {
            message = "No hay computadores registrados.";
            return message;
        }

        Computer maxComp = computers.get(0);
        int maxIncidents = maxComp.getIncidents().size();

        for (Computer c : computers) {
            int cantIncidents = c.getIncidents().size();
            if (cantIncidents > maxIncidents) {
                maxIncidents = cantIncidents;
                maxComp = c;
            }
        }

        message = "Computador con mas incidentes:\n" +
                  "Serial: " + maxComp.getSerial() + "\n" +
                  "Piso: " + maxComp.getFloor() + "\n" +
                  "Columna: " + maxComp.getColumn() + "\n" +
                  "Cantidad de incidentes: " + maxIncidents;

        return message;
    }

    /**
     * Lista de todos los computadores con sus incidentes.
     * @return String con la lista de computadores e incidentes
     * Pre: Ninguna
     * Post: Retorna información de todos los computadores
     */
    public String listComputers() {
        String message = "";
        for (Computer c : computers) {
            message = message + "Computador [Serial: " + c.getSerial() +
                      ", Piso: " + c.getFloor() +
                      ", Columna: " + c.getColumn() + "]\n";
            if (c.getIncidents().isEmpty()) {
                message = message + "   No tiene incidentes registrados\n";
            } else {
                for (int i = 0; i < c.getIncidents().size(); i++) {
                    message = message + "   " + i + ") " +
                              c.getIncidents().get(i).toString() + "\n";
                }
            }
        }
        if (message.equals("")) {
            message = "No hay computadores registrados aún.";
        }
        return message;
    }

    /**
     * Busca un computador por serial.
     * @param serial 
     * @return Computador encontrado o null
     * Pre: serial != null
     * Post: Retorna el computador si existe, null en caso contrario
     */
    private Computer findComputer(String serial) {
        for (Computer c : computers) {
            if (c.getSerial().equals(serial)) {
                return c;
            }
        }
        return null;
    }
}
