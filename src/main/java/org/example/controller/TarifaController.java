package org.example.controller;
import org.example.DAO.TarifaDAO;
import org.example.model.Tarifa;
import java.sql.SQLException;
import java.util.ArrayList;

public class TarifaController {

    private TarifaDAO tarifaDAO;

    public TarifaController() { this.tarifaDAO = new TarifaDAO();}

    public boolean agregarTarifa(Tarifa tarifa) {
        return this.tarifaDAO.agregarTarifa(tarifa);
    }

    public boolean modificarTarifa(int idTarifa, Tarifa tarifa) {

        try {
            boolean existe = tarifaDAO.existeTarifa(idTarifa);
            if(existe){
                return tarifaDAO.modificarTarifa(idTarifa, tarifa);
            }else{
                System.out.println("La tarifa no existe.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar la tarifa: " + e.getMessage());
            return false;
        }

    }

    public boolean eliminarTarifa(int idTarifa) {
        return tarifaDAO.eliminarTarifa(idTarifa);
    }

    public ArrayList<Tarifa> obtenerTarifas(){
        try {
            return tarifaDAO.obtenerTarifas();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Tarifa obtenerTarifa(int idTarifa){
        try {
            return this.tarifaDAO.obtenerTarifa(idTarifa);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
