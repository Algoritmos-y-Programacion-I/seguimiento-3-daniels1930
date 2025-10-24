package model;

public class Incident {

    private final String description;
    private boolean resolved;
    private int hours;

    public Incident(String description) {
        this.description = description;
        this.resolved = false;
        this.hours = 0;
    }

    public String getDescription() {
        return description;
    }

    public boolean isResolved() {
        return resolved;
    }

    public int getHours() {
        return hours;
    }

    public void resolve(int hours) {
        this.resolved = true;
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Incidente: " + description + 
               " | Estado: " + (resolved ? "Resuelto" : "Pendiente") + 
               " | Horas: " + hours;
    }
}