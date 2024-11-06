package org.example.controller;
import org.example.DAO.HuespedDAO;
import org.example.model.Huesped;
import java.sql.SQLException;
import java.util.ArrayList;

public class HuespedController {

    private HuespedDAO huespedDAO;

    public HuespedController() { this.huespedDAO = new HuespedDAO();}


    public boolean agregarHuesped(Huesped huesped) {
        return this.huespedDAO.agregarHuesped(huesped);
    }

    public boolean modificarHuesped(int idHuesped, Huesped huesped) {

        try {
            boolean existe = huespedDAO.existeHuesped(idHuesped);
            if(existe){
                return huespedDAO.modificarHuesped(idHuesped, huesped);
            }else{
                System.out.println("El huesped no existe.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar el huesped: " + e.getMessage());
            return false;
        }

    }

    public boolean eliminarHuesped(int idHuesped) {
        return huespedDAO.eliminarHuesped(idHuesped);
    }

    public ArrayList<Huesped> obtenerHuespedes(){
        try {
            return huespedDAO.obtenerHuespedes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Huesped obtenerHuesped(int idHuesped){
        try {
            return this.huespedDAO.obtenerHuesped(idHuesped);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
