package org.example.model;
import java.util.Date;

public class TipoDocumento {

    private Integer idTipoDocumento;
    private String nombre;
    private String identificador;
    private String abreviatura;
    private String codigoPaisOrigen;
    private Date fechaEmitido;
    private Date fechaVencimiento;

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public String getCodigoPaisOrigen() {
        return codigoPaisOrigen;
    }

    public Date getFechaEmitido() {
        return fechaEmitido;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public TipoDocumento() {
    }
}
