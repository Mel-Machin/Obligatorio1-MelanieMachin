package org.example.model;
import java.util.Date;

public class Tarifa {

    private Integer idTarifa;
    private Date fechaInicio;
    private Double precio;
    private Integer idTipoHabitacion;

    public Integer getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getIdTipoHabitacion() {
        return idTipoHabitacion;
    }

    public void setIdTipoHabitacion(Integer idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public Tarifa() {
    }

    public Tarifa(Date fechaInicio, Double precio, Integer idTipoHabitacion) {
        this.fechaInicio = fechaInicio;
        this.precio = precio;
        this.idTipoHabitacion = idTipoHabitacion;
    }
}
