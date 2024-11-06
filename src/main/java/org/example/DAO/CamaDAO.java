package org.example.DAO;

import org.example.DAO.connection.ConnectionDB;
import org.example.model.Cama;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CamaDAO {

    private ConnectionDB connectionDB;

    public CamaDAO() {
        this.connectionDB = new ConnectionDB();
    }

    public ArrayList<Cama> obtenerCamasPorHabitacion(int idHabitacion) throws SQLException {
        ArrayList<Cama> camas = new ArrayList<Cama>();

        String query = "SELECT * FROM Cama WHERE idHabitacion = ?";
        ResultSet rs = connectionDB.executeQuery(query, idHabitacion);

        while (rs != null && rs.next()) {
            Cama cama = new Cama();
            cama.setCodCama(rs.getInt("codCama"));
            cama.setTipoCama(rs.getString("tipoCama"));
            camas.add(cama);
        }

        return camas;
    }

    public boolean agregarCama(int idHabitacion, String tipoCama) {
        String query = "INSERT INTO Cama(tipoCama,idHabitacion) VALUES(?,?)";
        int filasAfectadas = connectionDB.executeUpdate(query, tipoCama, idHabitacion);
        return filasAfectadas > 0;
    }

}
