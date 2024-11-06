package org.example.DAO;
import org.example.DAO.connection.ConnectionDB;
import org.example.model.Tarifa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TarifaDAO {

    private ConnectionDB connectionDB;
    public TarifaDAO(){ this.connectionDB = new ConnectionDB();}

    public boolean agregarTarifa(Tarifa tarifa){
        String query = "INSERT INTO Tarifa (fechaInicio, Precio, idTipoHabitacion) VALUES (?,?,?)";
        int filasAfectadas = connectionDB.executeUpdate(query, tarifa.getFechaInicio(), tarifa.getPrecio(), tarifa.getIdTipoHabitacion());
        return filasAfectadas > 0;
    }

    public boolean modificarTarifa(int idTarifa, Tarifa tarifa) {
        String query = "UPDATE Tarifa SET fechaInicio = ?, Precio = ?, idTipoHabitacion = ? WHERE idTarifa = ?";
        int filasAfectadas = connectionDB.executeUpdate(query,  tarifa.getFechaInicio(), tarifa.getPrecio(), tarifa.getIdTipoHabitacion(), idTarifa);
        return filasAfectadas > 0;
    }

    public boolean eliminarTarifa(int idTarifa) {
        String query = "DELETE FROM Tarifa WHERE idTarifa = ?";
        int filasEliminadas = connectionDB.executeUpdate(query, idTarifa);
        return filasEliminadas > 0;
    }

    public ArrayList<Tarifa> obtenerTarifas() throws SQLException {
        ArrayList<Tarifa> tarifas = new ArrayList<Tarifa>();

        String query = "SELECT * FROM Tarifa";
        ResultSet rs = connectionDB.executeQuery(query);

        while (rs != null && rs.next()) {
            Tarifa tarifa = new Tarifa();
            tarifa.setIdTarifa(rs.getInt("idTarifa"));
            tarifa.setFechaInicio(rs.getDate("fechaInicio"));
            tarifa.setPrecio(rs.getDouble("precio"));
            tarifa.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));
            tarifas.add(tarifa);
        }
        return tarifas;
    }

    public  Tarifa obtenerTarifa(int idTarifa) throws SQLException {
        String query = "SELECT * FROM Tarifa WHERE idTarifa = ?";
        ResultSet rs = connectionDB.executeQuery(query, idTarifa);

        while (rs != null && rs.next()) {
            Tarifa tarifa = new Tarifa();
            tarifa.setIdTarifa(rs.getInt("idTarifa"));
            tarifa.setFechaInicio(rs.getDate("fechaInicio"));
            tarifa.setPrecio(rs.getDouble("precio"));
            tarifa.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));
            return tarifa;
        }

        return null;
    }

    public boolean existeTarifa(int idTarifa) throws SQLException {
        return obtenerTarifa(idTarifa) != null;
    }

}
