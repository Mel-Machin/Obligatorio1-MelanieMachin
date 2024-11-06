package org.example.model;

import java.util.ArrayList;

public class Ciudad {
    private String codigoPostal;
    private String nombre;
    private String codigoPais;


    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigoPais() {
        return codigoPais;
    }



    public Ciudad(String codigoPais,String nombre,String codigoPostal) {
        this.codigoPais=codigoPais;
        this.codigoPostal=codigoPostal;
        this.nombre=nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
