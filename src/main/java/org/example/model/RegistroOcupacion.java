package org.example.model;

public class RegistroOcupacion {

    private Integer idOcupacion;
    private String medioRegistro;
    private boolean estado;

    public Integer getIdOcupacion() {
        return idOcupacion;
    }

    public void setIdOcupacion(Integer idOcupacion) {
        this.idOcupacion = idOcupacion;
    }

    public void setMedioRegistro(String medioRegistro) {
        this.medioRegistro = medioRegistro;
    }
    public String getMedioRegistro() {
        return medioRegistro;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isEstado() {
        return estado;
    }


    public RegistroOcupacion() {
    }

    public RegistroOcupacion(String medioRegistro) {
        this.medioRegistro = medioRegistro;
    }

    @Override
    public String toString(){
        return this.medioRegistro;
    }
}
