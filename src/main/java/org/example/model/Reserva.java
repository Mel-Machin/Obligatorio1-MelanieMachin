package org.example.model;

import java.util.ArrayList;
import java.util.Date;

public class Reserva {
    private Integer idReserva;
    private Integer idHuesped;
    private Huesped huesped;
    private Integer idHotel;
    private Hotel hotel;
    private Integer idTarifa;
    private ArrayList<Habitacion> habitaciones;
    private String estadoPago;
    private Date fechaReserva;
    private Date fechaCheckIn;
    private String estadoCheckIn;
    private Date fechaCheckOut;
    private String estadoCheckOut;
    private Integer cantidadPersonas;
    private String observacion;


    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public String getEstadoCheckOut() {
        return estadoCheckOut;
    }

    public void setEstadoCheckOut(String estadoCheckOut) {
        this.estadoCheckOut = estadoCheckOut;
    }

    public Date getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(Date fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
    }

    public String getEstadoCheckIn() {
        return estadoCheckIn;
    }

    public void setEstadoCheckIn(String estadoCheckIn) {
        this.estadoCheckIn = estadoCheckIn;
    }

    public Date getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(Date fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public Integer getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public Integer getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Integer idHotel) {
        this.idHotel = idHotel;
    }

    public Integer getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(Integer idHuesped) {
        this.idHuesped = idHuesped;
    }

    public Reserva() {
    }

    public Reserva(Integer idHuesped, Integer idHotel, Integer idTarifa, String estadoPago, Date fechaReserva, Date fechaCheckIn, String estadoCheckIn, Date fechaCheckOut, String estadoCheckOut, Integer cantidadPersonas, String observacion) {
        this.idHuesped = idHuesped;
        this.idHotel = idHotel;
        this.idTarifa = idTarifa;
        this.estadoPago = estadoPago;
        this.fechaReserva = fechaReserva;
        this.fechaCheckIn = fechaCheckIn;
        this.estadoCheckIn = estadoCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.estadoCheckOut = estadoCheckOut;
        this.cantidadPersonas = cantidadPersonas;
        this.observacion = observacion;
    }
}
