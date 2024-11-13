package org.example.model;

import java.util.ArrayList;

public class Habitacion {

    private Integer idHabitacion;
    private Integer nroHabitacion;
    private Integer idHotel;
    private Integer idTipoHabitacion;
    private TipoHabitacion tipoHabitacion;
    private ArrayList<Cama> camas;
    private ArrayList<Caracteristica> caracteristicas;
    private RegistroOcupacion medioOcupacion;


    public Integer getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public void setNroHabitacion(Integer nroHabitacion) {
        this.nroHabitacion = nroHabitacion;
    }

    public Integer getNroHabitacion() {
        return nroHabitacion;
    }

    public Integer getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Integer idHotel) {
        this.idHotel = idHotel;
    }

    public Integer getIdTipoHabitacion() {
        return idTipoHabitacion;
    }

    public void setIdTipoHabitacion(Integer idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public ArrayList<Cama> getCamas() {
        return camas;
    }

    public void setCamas(ArrayList<Cama> camas) {
        this.camas = camas;
    }

    public ArrayList<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(ArrayList<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public RegistroOcupacion getMedioOcupacion() {
        return medioOcupacion;
    }

    public void setMedioOcupacion(RegistroOcupacion medioOcupacion) {
        this.medioOcupacion = medioOcupacion;
    }

    public Habitacion() {
    }

    public Habitacion(Integer nroHabitacion, Integer idHotel, Integer idTipoHabitacion) {
        this.nroHabitacion = nroHabitacion;
        this.idHotel = idHotel;
        this.idTipoHabitacion = idTipoHabitacion;
    }

    @Override
    public String toString(){
        return this.nroHabitacion.toString();
    }
}
