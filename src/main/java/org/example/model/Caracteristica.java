package org.example.model;

public class Caracteristica {

    private Integer codigoCaracteristica;
    private String nombre;
    private String tipo;

    public Integer getCodigoCaracteristica() {
        return codigoCaracteristica;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setCodigoCaracteristica(Integer codigoCaracteristica) {
        this.codigoCaracteristica = codigoCaracteristica;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Caracteristica() {
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
