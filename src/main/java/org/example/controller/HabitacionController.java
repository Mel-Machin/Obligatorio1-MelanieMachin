package org.example.controller;

import org.example.DAO.HabitacionDAO;
import org.example.model.Habitacion;
import org.example.model.TipoHabitacion;
import java.sql.SQLException;
import java.util.ArrayList;


public class HabitacionController {

    private HabitacionDAO habitacionDAO;

    public HabitacionController(){ this.habitacionDAO = new HabitacionDAO();}


    public int agregarHabitacion(Habitacion habitacion) {
        return this.habitacionDAO.agregarHabitacion(habitacion);
    }

    public boolean modificarHabitacion(int idHabitacion, Habitacion habitacion) {

        try {
            boolean existe = habitacionDAO.existeHabitacion(idHabitacion);
            if(existe){
                return habitacionDAO.modificarHabitacion(idHabitacion, habitacion);
            }else{
                System.out.println("La habitacion no existe.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar la habitacion: " + e.getMessage());
            return false;
        }

    }

    public boolean eliminarHabitacion(int idHabitacion) {
        return habitacionDAO.eliminarHabitacion(idHabitacion);
    }



    public  ArrayList<TipoHabitacion> obtenerTipoHabitacionesHotelDisponibles(String fechaInicio, String fechaFin,int idHotel){
        try {
            return habitacionDAO.obtenerTipoHabitacionesHotelDisponibles(fechaInicio,fechaFin,idHotel);
        }catch (SQLException e) {
            System.err.println("Error al obtener tipo habitacion: " + e.getMessage());
            return new ArrayList<TipoHabitacion>();
        }
    }

    public Habitacion obtenerHabitacion(int idHabitacion){
        try {
            return this.habitacionDAO.obtenerHabitacion(idHabitacion);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Habitacion> obtenerHabitaciones(){
        try {
            return habitacionDAO.obtenerHabitaciones();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Habitacion> obtenerHabitacionesOcupadas(){
        try{
            return habitacionDAO.obtenerHabitacionesOcupadas();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int obtenerIdHabitacionTipoHabitacionDisponible(int idTipoHabitacion, int idHotel, String fechaCheckIn, String fechaCheckOut) {
        try {
            return habitacionDAO.obtenerIdHabitacionTipoHabitacionDisponible(idTipoHabitacion,idHotel,fechaCheckIn,fechaCheckOut);
        }catch (SQLException e) {
            System.err.println("Error al obtener id habitacion: " + e.getMessage());
            return -1;
        }
    }

    public ArrayList<Habitacion> filtroHabitacionesConReserva(){
        try{
            return habitacionDAO.filtroHabitacionesConReserva();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Habitacion> filtroHabitacionesSinReserva(){
        try{
            return habitacionDAO.filtroHabitacionesSinReserva();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
