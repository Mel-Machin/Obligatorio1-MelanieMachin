package org.example.DAO;

import org.example.DAO.connection.ConnectionDB;
import org.example.model.Tarifa;
import org.example.model.TipoHabitacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TarifaDAO {

    private ConnectionDB connectionDB;

    public TarifaDAO() {
        this.connectionDB = new ConnectionDB();
    }

    //Alta, Baja y Modificacion
    public boolean agregarTarifa(Tarifa tarifa) {
        // Primero, desactivar tarifas vigentes anteriores para el mismo tipo de habitación
        String desactivarTarifasQuery = "UPDATE Tarifa SET vigente = FALSE WHERE idTipoHabitacion = ? AND vigente = TRUE";
        connectionDB.executeUpdate(desactivarTarifasQuery, tarifa.getIdTipoHabitacion());

        // Insertar la nueva tarifa
        String insertarQuery = "INSERT INTO Tarifa (fechaInicio, Precio, idTipoHabitacion, vigente) VALUES (?, ?, ?, TRUE)";
        int filasAfectadas = connectionDB.executeUpdate(insertarQuery, tarifa.getFechaInicio(), tarifa.getPrecio(), tarifa.getIdTipoHabitacion());

        return filasAfectadas > 0;
    }

    public boolean modificarTarifa(int idTarifa, Tarifa tarifa) {
        // Desactivar tarifas vigentes anteriores para el mismo tipo de habitación excepto la que estamos actualizando
        String desactivarTarifasQuery = "UPDATE Tarifa SET vigente = FALSE WHERE idTipoHabitacion = ? AND vigente = TRUE AND idTarifa != ?";
        connectionDB.executeUpdate(desactivarTarifasQuery, tarifa.getIdTipoHabitacion(), idTarifa);

        // Actualizar la tarifa con los nuevos valores
        String modificarQuery = "UPDATE Tarifa SET fechaInicio = ?, Precio = ?, idTipoHabitacion = ?, vigente = TRUE WHERE idTarifa = ?";
        int filasAfectadas = connectionDB.executeUpdate(modificarQuery, tarifa.getFechaInicio(), tarifa.getPrecio(), tarifa.getIdTipoHabitacion(), idTarifa);

        return filasAfectadas > 0;
    }

    public boolean eliminarTarifa(int idTarifa) {
        String query = "DELETE FROM Tarifa WHERE idTarifa = ?";
        int filasEliminadas = connectionDB.executeUpdate(query, idTarifa);
        return filasEliminadas > 0;
    }

    //Obtener
    public ArrayList<Tarifa> obtenerTarifas() throws SQLException {
        ArrayList<Tarifa> tarifas = new ArrayList<Tarifa>();

        String query = "SELECT t.*, th.nombre FROM Tarifa t INNER JOIN tipohabitacion th ON t.idTipoHabitacion = th.idTipoHabitacion";
        ResultSet rs = connectionDB.executeQuery(query);

        while (rs != null && rs.next()) {
            Tarifa tarifa = new Tarifa();
            tarifa.setIdTarifa(rs.getInt("idTarifa"));
            tarifa.setFechaInicio(rs.getDate("fechaInicio"));
            tarifa.setPrecio(rs.getDouble("precio"));
            tarifa.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));
            TipoHabitacion tipoHabitacion = new TipoHabitacion(rs.getString("nombre"));
            tarifa.setTipoHabitacion(tipoHabitacion);
            tarifas.add(tarifa);
        }
        return tarifas;
    }

    public Tarifa obtenerTarifa(int idTarifa) throws SQLException {
        String query = "SELECT t.*, th.nombre FROM Tarifa t INNER JOIN tipohabitacion th ON t.idTipoHabitacion = th.idTipoHabitacion WHERE idTarifa = ?";
        ResultSet rs = connectionDB.executeQuery(query, idTarifa);

        while (rs != null && rs.next()) {
            Tarifa tarifa = new Tarifa();
            tarifa.setIdTarifa(rs.getInt("idTarifa"));
            tarifa.setFechaInicio(rs.getDate("fechaInicio"));
            tarifa.setPrecio(rs.getDouble("precio"));
            tarifa.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));
            TipoHabitacion tipoHabitacion = new TipoHabitacion(rs.getString("nombre"));
            tarifa.setTipoHabitacion(tipoHabitacion);
            return tarifa;
        }

        return null;
    }


    public boolean existeTarifa(int idTarifa) throws SQLException {
        return obtenerTarifa(idTarifa) != null;
    }

}
