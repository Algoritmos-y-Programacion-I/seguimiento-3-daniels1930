package model;

import java.util.ArrayList;

public class Computer {
    
    private String serial;
    private int floor;
    private int column;
    private ArrayList<Incident> incidents;

    public Computer(String serial, int floor, int column) {
        this.serial = serial;
        this.floor = floor;
        this.column = column;
        this.incidents = new ArrayList<>();
    }

    public String getSerial() {
        return serial;
    }

    public int getFloor() {
        return floor;
    }

    public int getColumn() {
        return column;
    }

    public ArrayList<Incident> getIncidents() {
        return incidents;
    }

    public void addIncident(Incident incident) {
        incidents.add(incident);
    }
}
