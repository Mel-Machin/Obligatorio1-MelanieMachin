package org.example.model;

public class RegistroOcupacion {

    private Integer idOcupacion;
    private String medioRegistro;
    private boolean estado;

    public Integer getIdOcupacion() {
        return idOcupacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public String getMedioRegistro() {
        return medioRegistro;
    }

    public RegistroOcupacion() {
    }
}
