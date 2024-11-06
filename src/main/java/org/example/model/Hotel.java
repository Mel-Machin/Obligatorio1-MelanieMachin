package org.example.model;

import java.util.ArrayList;

public class Hotel {

    private Integer idHotel;
    private String nombre;

    private Ciudad ciudad;



    private String codigoCiudad;


    private Integer cantidadEstrellas;


    public Integer getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Integer idHotel) {
        this.idHotel = idHotel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(String codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public Integer getCantidadEstrellas() {
        return cantidadEstrellas;
    }

    public void setCantidadEstrellas(Integer cantidadEstrellas) {
        this.cantidadEstrellas = cantidadEstrellas;
    }





    public Hotel() {
    }

    public Hotel(String nombre, String codigoCiudad, Integer cantidadEstrellas) {
        this.nombre = nombre;
        this.codigoCiudad = codigoCiudad;
        this.cantidadEstrellas = cantidadEstrellas;
    }

}
