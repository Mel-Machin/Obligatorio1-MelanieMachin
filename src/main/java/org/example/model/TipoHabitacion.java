package org.example.model;
import java.util.ArrayList;

public class TipoHabitacion {

    private Integer idTipoHabitacion;
    private String nombre;
    private String descripcion;
    private ArrayList<Habitacion> habitaciones;



    private Integer idTarifa;

    public Integer getIdTipoHabitacion() {
        return idTipoHabitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public Integer getIdTarifa() {
        return idTarifa;
    }

    public void setIdTipoHabitacion(Integer idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void setIdTarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public TipoHabitacion() {
    }

    public TipoHabitacion(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return this.nombre;
    }
}
