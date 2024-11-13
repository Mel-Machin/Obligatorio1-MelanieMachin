package org.example.DAO;

import org.example.DAO.connection.ConnectionDB;
import org.example.model.Huesped;
import org.example.model.TipoDocumento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HuespedDAO {

    private ConnectionDB connectionDB;

    public HuespedDAO() {
        this.connectionDB = new ConnectionDB();
    }


    //Alta, Baja y Modificacion
    public boolean agregarHuesped(Huesped huesped) {
        String query = "INSERT INTO Huesped (tipoDocumento, nombre , primerApellido, segundoApellido, telefono ) VALUES (?,?,?,?,?)";
        int filasAfectadas = connectionDB.executeUpdate(query, huesped.getIdTipoDocumento(), huesped.getNombre(), huesped.getPrimerApellido(), huesped.getSegundoApellido(), huesped.getTelefono());
        return filasAfectadas > 0;
    }

    public boolean modificarHuesped(int idHuesped, Huesped huesped) {
        String query = "UPDATE Huesped SET tipoDocumento = ?, nombre = ?, primerApellido = ?, segundoApellido = ?, telefono = ? WHERE idHuesped = ?";
        int filasAfectadas = connectionDB.executeUpdate(query, huesped.getIdTipoDocumento(), huesped.getNombre(), huesped.getPrimerApellido(), huesped.getSegundoApellido(), huesped.getTelefono(), idHuesped);
        return filasAfectadas > 0;
    }

    public boolean eliminarHuesped(int idHuesped) {
        String query = "DELETE FROM Huesped WHERE idHuesped = ?";
        int filasEliminadas = connectionDB.executeUpdate(query, idHuesped);
        return filasEliminadas > 0;
    }


    //Obtener
    public ArrayList<Huesped> obtenerHuespedes() throws SQLException {
        ArrayList<Huesped> huespedes = new ArrayList<Huesped>();

        String query = "SELECT h.* , td.abreviatura ,td.identificador, td.codigoPaisOrigen FROM Huesped h INNER JOIN tipodocumento td ON h.tipoDocumento = td.idTipoDocumento";
        ResultSet rs = connectionDB.executeQuery(query);

        while (rs != null && rs.next()) {
            Huesped huesped = new Huesped();
            huesped.setIdHuesped(rs.getInt("idHuesped"));
            huesped.setIdTipoDocumento(rs.getInt("tipoDocumento"));
            TipoDocumento tipoDocumento = new TipoDocumento(rs.getString("abreviatura"), rs.getString("identificador"), rs.getString("codigoPaisOrigen"));
            huesped.setTipoDocumento(tipoDocumento);
            huesped.setNombre(rs.getString("nombre"));
            huesped.setPrimerApellido(rs.getString("primerApellido"));
            huesped.setSegundoApellido(rs.getString("segundoApellido"));
            huesped.setTelefono(rs.getString("telefono"));
            huespedes.add(huesped);
        }
        return huespedes;
    }

    public Huesped obtenerHuesped(int idHuesped) throws SQLException {
        String query = "SELECT h.*, td.abreviatura ,td.identificador, td.codigoPaisOrigen FROM Huesped  h INNER JOIN tipodocumento td ON h.tipoDocumento = td.idTipoDocumento WHERE idHuesped = ?";
        ResultSet rs = connectionDB.executeQuery(query, idHuesped);

        while (rs != null && rs.next()) {
            Huesped huesped = new Huesped();
            huesped.setIdHuesped(rs.getInt("idHuesped"));
            huesped.setIdTipoDocumento(rs.getInt("tipoDocumento"));
            TipoDocumento tipoDocumento = new TipoDocumento(rs.getString("abreviatura"), rs.getString("identificador"), rs.getString("codigoPaisOrigen"));
            huesped.setTipoDocumento(tipoDocumento);
            huesped.setNombre(rs.getString("nombre"));
            huesped.setPrimerApellido(rs.getString("primerApellido"));
            huesped.setSegundoApellido(rs.getString("segundoApellido"));
            huesped.setTelefono(rs.getString("telefono"));
            return huesped;
        }

        return null;
    }


    public boolean existeHuesped(int idHuesped) throws SQLException {
        return obtenerHuesped(idHuesped) != null;
    }
}
