package org.example.controller;

import org.example.DAO.ReservaDAO;
import org.example.model.Hotel;
import org.example.model.Reserva;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservaController {

    private ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
    }

    public int agregarReserva(Reserva reserva) {
        return this.reservaDAO.agregarReserva(reserva);
    }

    public boolean modificarReserva(int idReserva, Reserva reserva) {

        try {
            boolean existe = reservaDAO.existeReserva(idReserva);
            if (existe) {
                return reservaDAO.modificarReserva(idReserva, reserva);

            } else {
                System.out.println("La reserva no existe.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar la reserva: " + e.getMessage());
            return false;
        }

    }

    public boolean eliminarReserva(int idReserva) {
        return reservaDAO.eliminarReserva(idReserva);
    }

    public Reserva obtenerReserva(int idReserva) {
        try {
            return this.reservaDAO.obtenerReserva(idReserva);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Reserva> obtenerReservas() {
        try {
            return reservaDAO.obtenerReservas();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean agregarReservaHabitacion(int idHabitacion, int idReserva) {
        return reservaDAO.agregarReservaHabitacion(idHabitacion,idReserva);
    }
}
