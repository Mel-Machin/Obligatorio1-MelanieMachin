package org.example.controller;

import org.example.DAO.CaracteristicaDAO;

public class CaracteristicaController {

    private CaracteristicaDAO caracteristicaDAO;

    public CaracteristicaController() {
        this.caracteristicaDAO = new CaracteristicaDAO();
    }

    public boolean agregarCaracteristica(String nombre, String tipo, int idHabitacion) {
        return caracteristicaDAO.agregarCaracteristica(nombre, tipo, idHabitacion);
    }

}
