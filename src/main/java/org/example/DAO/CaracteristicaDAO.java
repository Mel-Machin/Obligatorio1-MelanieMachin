package org.example.DAO;

import org.example.DAO.connection.ConnectionDB;
import org.example.model.Caracteristica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CaracteristicaDAO {

    private ConnectionDB connectionDB;

    public CaracteristicaDAO() {
        this.connectionDB = new ConnectionDB();
    }


    public ArrayList<Caracteristica> obtenerCaracteristicasPorHabitacion(int idHabitacion) throws SQLException {
        ArrayList<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();

        String query = "SELECT * FROM Caracteristica WHERE idHabitacion = ?";
        ResultSet rs = connectionDB.executeQuery(query, idHabitacion);

        while (rs != null && rs.next()) {
            Caracteristica caracteristica = new Caracteristica();
            caracteristica.setCodigoCaracteristica(rs.getInt("codigoCaracteristica"));
            caracteristica.setNombre(rs.getString("nombre"));
            caracteristica.setTipo(rs.getString("tipo"));
            caracteristicas.add(caracteristica);
        }

        return caracteristicas;
    }

    public boolean agregarCaracteristica(String nombre, String tipo, int idHabitacion) {
        String query = "INSERT INTO Caracteristica (nombre,tipo, idHabitacion) VALUES(?,?,?)";
        int filasAfectadas = connectionDB.executeUpdate(query, nombre, tipo, idHabitacion);
        return filasAfectadas > 0;
    }
}

