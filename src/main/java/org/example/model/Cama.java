package org.example.model;

public class Cama {

    private Integer codCama;
    private String tipoCama;
    private Integer idHabitacion;

    public Integer getCodCama() {
        return codCama;
    }

    public void setCodCama(Integer codCama) {
        this.codCama = codCama;
    }

    public void setTipoCama(String tipoCama) {
        this.tipoCama = tipoCama;
    }

    public void setIdHabitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getTipoCama() {
        return tipoCama;
    }

    public Integer getIdHabitacion() {
        return idHabitacion;
    }

    public Cama() {
    }

    @Override
    public String toString() {
        return this.tipoCama;
    }
}
