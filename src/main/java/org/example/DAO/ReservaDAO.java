package org.example.DAO;

import org.example.DAO.connection.ConnectionDB;
import org.example.model.Reserva;
import org.example.model.Tarifa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservaDAO {

    private ConnectionDB connectionDB;

    public ReservaDAO() {
        this.connectionDB = new ConnectionDB();
    }

    public int agregarReserva(Reserva reserva) {
        String query = "INSERT INTO Reserva ( idHuesped, idHotel, estadoPago, fechaReserva, fechaCheckIn, estadoCheckIn, fechaCheckOut, estadoCheckOut, cantidadPersonas, observacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int idReserva = connectionDB.executeUpdateAutoincrement(query, reserva.getIdHuesped(), reserva.getIdHotel(), reserva.getEstadoPago(), reserva.getFechaReserva(), reserva.getFechaCheckIn(), reserva.getEstadoCheckIn(), reserva.getFechaCheckOut(), reserva.getEstadoCheckOut(), reserva.getCantidadPersonas(), reserva.getObservacion());
        return idReserva;
    }

    public boolean modificarReserva(int idReserva, Reserva reserva) {
        String query = "UPDATE Reserva SET idHuesped = ?,idHotel = ?,idTarifa = ?,estadoPago = ?,fechaReserva = ?,fechaCheckIn = ?,estadoCheckIn = ?,fechaCheckOut = ?,estadoCheckOut = ?,cantidadPersonas = ?,observacion = ?WHERE idReserva = ?";
        int filasAfectadas = connectionDB.executeUpdate(query, reserva.getIdHuesped(), reserva.getIdHotel(), reserva.getIdTarifa(), reserva.getEstadoPago(), reserva.getFechaReserva(), reserva.getFechaCheckIn(), reserva.getEstadoCheckIn(), reserva.getFechaCheckOut(), reserva.getEstadoCheckOut(), reserva.getCantidadPersonas(), reserva.getObservacion(), idReserva);
        return filasAfectadas > 0;
    }

    public boolean eliminarReserva(int idReserva) {
        String query = "DELETE FROM Reserva WHERE idReserva = ?";
        int filasEliminadas = connectionDB.executeUpdate(query, idReserva);
        return filasEliminadas > 0;
    }

    public ArrayList<Reserva> obtenerReservas() throws SQLException {
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();

        String query = "SELECT * FROM Reserva";
        ResultSet rs = connectionDB.executeQuery(query);

        while (rs != null && rs.next()) {
            Reserva reserva = new Reserva();
            reserva.setIdReserva(rs.getInt("idReserva"));
            reserva.setIdReserva(rs.getInt("idHuesped"));
            reserva.setIdReserva(rs.getInt("idHotel"));
            reserva.setIdTarifa(rs.getInt("idTarifa"));
            reserva.setEstadoPago(rs.getString("estadoPago"));
            reserva.setFechaReserva(rs.getDate("fechaReserva"));
            reserva.setFechaCheckIn(rs.getDate("fechaCheckIn"));
            reserva.setEstadoCheckIn(rs.getString("estadoCheckIn"));
            reserva.setFechaCheckOut(rs.getDate("fechaCheckOut"));
            reserva.setEstadoCheckOut(rs.getString("estadoCheckOut"));
            reserva.setCantidadPersonas(rs.getInt("cantidadPersonas"));
            reserva.setObservacion(rs.getString("observacion"));
            reservas.add(reserva);
        }
        return reservas;
    }

    public Reserva obtenerReserva(int idReserva) throws SQLException {
        String query = "SELECT * FROM Reserva WHERE idReserva = ?";
        ResultSet rs = connectionDB.executeQuery(query, idReserva);

        while (rs != null && rs.next()) {
            Reserva reserva = new Reserva();
            reserva.setIdReserva(rs.getInt("idReserva"));
            reserva.setIdReserva(rs.getInt("idHuesped"));
            reserva.setIdReserva(rs.getInt("idHotel"));
            reserva.setIdTarifa(rs.getInt("idTarifa"));
            reserva.setEstadoPago(rs.getString("estadoPago"));
            reserva.setFechaReserva(rs.getDate("fechaReserva"));
            reserva.setFechaCheckIn(rs.getDate("fechaCheckIn"));
            reserva.setEstadoCheckIn(rs.getString("estadoCheckIn"));
            reserva.setFechaCheckOut(rs.getDate("fechaCheckOut"));
            reserva.setEstadoCheckOut(rs.getString("estadoCheckOut"));
            reserva.setCantidadPersonas(rs.getInt("cantidadPersonas"));
            reserva.setObservacion(rs.getString("observacion"));
            return reserva;
        }

        return null;
    }

    public boolean existeReserva(int idReserva) throws SQLException {
        return obtenerReserva(idReserva) != null;
    }

    public boolean agregarReservaHabitacion(int idHabitacion, int idReserva) {
        String query = "INSERT INTO habitacionreserva ( idHabitacion, idReserva) VALUES (?, ?)";
        int filasAfectadas = connectionDB.executeUpdate(query,idHabitacion,idReserva);
        return filasAfectadas>0;
    }
}
