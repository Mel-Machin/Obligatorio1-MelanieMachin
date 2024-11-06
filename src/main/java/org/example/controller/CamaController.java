package org.example.controller;

import org.example.DAO.CamaDAO;

public class CamaController {

    private CamaDAO camaDAO;

    public CamaController() {
        this.camaDAO = new CamaDAO();
    }

    public boolean agregarCama(int idHabitacion, String tipoCama) {
        return camaDAO.agregarCama(idHabitacion, tipoCama);
    }

}
