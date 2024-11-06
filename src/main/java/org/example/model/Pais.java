package org.example.model;

import java.util.ArrayList;

public class Pais {

    private String codigoISO;
    private String nombre;
    private ArrayList<Ciudad> ciudades;
    private ArrayList<Huesped> huespedes;

    public String getCodigoISO() {
        return codigoISO;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Ciudad> getCiudades() {
        return ciudades;
    }

    public ArrayList<Huesped> getHuespedes() {
        return huespedes;
    }

    public Pais() {
    }
}
