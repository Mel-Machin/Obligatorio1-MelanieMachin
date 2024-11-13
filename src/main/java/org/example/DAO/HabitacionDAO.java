package org.example.DAO;

import org.example.DAO.connection.ConnectionDB;
import org.example.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HabitacionDAO {

    private ConnectionDB connectionDB;
    private CamaDAO camaDAO;
    private CaracteristicaDAO caracteristicaDAO;

    public HabitacionDAO() {
        this.connectionDB = new ConnectionDB();
        this.camaDAO = new CamaDAO();
        this.caracteristicaDAO = new CaracteristicaDAO();
    }

    //Alta, Baja y Modificacion
    public int agregarHabitacion(Habitacion habitacion) {
        String query = "INSERT INTO Habitacion (nroHabitacion, idHotel , idTipoHabitacion) VALUES (?,?,?)";
        int idHabitacion = connectionDB.executeUpdateAutoincrement(query, habitacion.getNroHabitacion(), habitacion.getIdHotel(), habitacion.getIdTipoHabitacion());
        return idHabitacion;
    }

    public boolean modificarHabitacion(int idHabitacion, Habitacion habitacion) {
        String query = "UPDATE Habitacion SET nroHabitacion = ?, idHotel = ?, idTipoHabitacion = ? WHERE idHabitacion = ?";
        int filasAfectadas = connectionDB.executeUpdate(query, habitacion.getNroHabitacion(), habitacion.getIdHotel(), habitacion.getIdTipoHabitacion(), idHabitacion);
        return filasAfectadas > 0;
    }

    public boolean eliminarHabitacion(int idHabitacion) {
        String query = "DELETE FROM Habitacion WHERE idHabitacion = ?";
        int filasEliminadas = connectionDB.executeUpdate(query, idHabitacion);
        return filasEliminadas > 0;
    }

    //Obtener
    public ArrayList<Habitacion> obtenerHabitaciones() throws SQLException {
        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

        String query = "SELECT h.*, th.nombre  FROM Habitacion h INNER JOIN tipohabitacion th ON h.idTipoHabitacion = th.idTipoHabitacion;";
        ResultSet rs = connectionDB.executeQuery(query);

        while (rs != null && rs.next()) {
            Habitacion habitacion = new Habitacion();
            habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
            habitacion.setNroHabitacion(rs.getInt("nroHabitacion"));
            habitacion.setIdHotel(rs.getInt("idHotel"));
            habitacion.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));
            TipoHabitacion tipoHabitacion = new TipoHabitacion(rs.getString("nombre"));
            habitacion.setTipoHabitacion(tipoHabitacion);
            ArrayList<Cama> camas = camaDAO.obtenerCamasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCamas(camas);
            ArrayList<Caracteristica> caracteristicas = caracteristicaDAO.obtenerCaracteristicasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCaracteristicas(caracteristicas);
            habitaciones.add(habitacion);
        }
        return habitaciones;
    }

    public Habitacion obtenerHabitacion(int idHabitacion) throws SQLException {
        String query = "SELECT h.*,  th.nombre  FROM Habitacion h INNER JOIN tipohabitacion th ON h.idTipoHabitacion = th.idTipoHabitacion WHERE idHabitacion = ? ;";
        ResultSet rs = connectionDB.executeQuery(query, idHabitacion);

        while (rs != null && rs.next()) {
            Habitacion habitacion = new Habitacion();
            habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
            habitacion.setNroHabitacion(rs.getInt("nroHabitacion"));
            habitacion.setIdHotel(rs.getInt("idHotel"));
            habitacion.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));
            TipoHabitacion tipoHabitacion = new TipoHabitacion(rs.getString("nombre"));
            habitacion.setTipoHabitacion(tipoHabitacion);
            ArrayList<Cama> camas = camaDAO.obtenerCamasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCamas(camas);
            ArrayList<Caracteristica> caracteristicas = caracteristicaDAO.obtenerCaracteristicasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCaracteristicas(caracteristicas);
            return habitacion;
        }

        return null;
    }

    public ArrayList<Habitacion> filtroHabitacionesConReserva() throws SQLException {
        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

        String query = "SELECT h.*, th.nombre FROM habitacion h LEFT JOIN habitacionreserva hr ON h.idHabitacion = hr.idhabitacion INNER JOIN tipohabitacion th ON h.idTipoHabitacion = th.idTipoHabitacion WHERE hr.idreserva IS NOT NULL;";
        ResultSet rs = connectionDB.executeQuery(query);

        while (rs != null && rs.next()) {
            Habitacion habitacion = new Habitacion();
            habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
            habitacion.setNroHabitacion(rs.getInt("nroHabitacion"));
            habitacion.setIdHotel(rs.getInt("idHotel"));
            habitacion.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));
            TipoHabitacion tipoHabitacion = new TipoHabitacion(rs.getString("nombre"));
            habitacion.setTipoHabitacion(tipoHabitacion);
            ArrayList<Cama> camas = camaDAO.obtenerCamasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCamas(camas);
            ArrayList<Caracteristica> caracteristicas = caracteristicaDAO.obtenerCaracteristicasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCaracteristicas(caracteristicas);
            habitaciones.add(habitacion);
        }
        return habitaciones;
    }

    public ArrayList<Habitacion> filtroHabitacionesSinReserva() throws SQLException {
        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

        String query = "SELECT h.*, th.nombre FROM habitacion h LEFT JOIN habitacionreserva hr ON h.idHabitacion = hr.idhabitacion INNER JOIN tipohabitacion th ON h.idTipoHabitacion = th.idTipoHabitacion WHERE hr.idreserva IS null;";
        ResultSet rs = connectionDB.executeQuery(query);

        while (rs != null && rs.next()) {
            Habitacion habitacion = new Habitacion();
            habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
            habitacion.setNroHabitacion(rs.getInt("nroHabitacion"));
            habitacion.setIdHotel(rs.getInt("idHotel"));
            habitacion.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));
            TipoHabitacion tipoHabitacion = new TipoHabitacion(rs.getString("nombre"));
            habitacion.setTipoHabitacion(tipoHabitacion);
            ArrayList<Cama> camas = camaDAO.obtenerCamasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCamas(camas);
            ArrayList<Caracteristica> caracteristicas = caracteristicaDAO.obtenerCaracteristicasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCaracteristicas(caracteristicas);
            habitaciones.add(habitacion);
        }
        return habitaciones;
    }

    public ArrayList<Habitacion> obtenerHabitacionesOcupadas() throws SQLException {
        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

        String query = "SELECT h.*, th.nombre, ro.medioRegistro FROM Habitacion h INNER JOIN tipohabitacion th ON h.idTipoHabitacion = th.idTipoHabitacion INNER JOIN registroocupacion ro ON h.idHabitacion = ro.idHabitacion WHERE ro.estado = 1;";
        ResultSet rs = connectionDB.executeQuery(query);

        while (rs != null && rs.next()) {
            Habitacion habitacion = new Habitacion();
            habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
            habitacion.setNroHabitacion(rs.getInt("nroHabitacion"));
            habitacion.setIdHotel(rs.getInt("idHotel"));
            habitacion.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));
            TipoHabitacion tipoHabitacion = new TipoHabitacion(rs.getString("nombre"));
            habitacion.setTipoHabitacion(tipoHabitacion);
            ArrayList<Cama> camas = camaDAO.obtenerCamasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCamas(camas);
            ArrayList<Caracteristica> caracteristicas = caracteristicaDAO.obtenerCaracteristicasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCaracteristicas(caracteristicas);
            RegistroOcupacion registroOcupacion = new RegistroOcupacion(rs.getString("medioRegistro"));
            habitacion.setMedioOcupacion(registroOcupacion);

            habitaciones.add(habitacion);
        }
        return habitaciones;
    }

    //Obtiene un lista de tiposHabitaciones disponibles en un hotel específico que no están reservadas en el rango de fechas
    public ArrayList<TipoHabitacion> obtenerTipoHabitacionesHotelDisponibles(String fechaInicio, String fechaFin, int idHotel) throws SQLException {
        String query = "SELECT th.* FROM habitacion h LEFT JOIN tipohabitacion th ON th.idTipoHabitacion = h.idTipoHabitacion WHERE h.idHotel = ? AND h.idHabitacion NOT IN (SELECT hr.idHabitacion FROM habitacionreserva hr JOIN reserva r ON hr.idReserva = r.idReserva WHERE r.fechaCheckIn < ? AND r.fechaCheckOut > ?);";
        ResultSet rs = connectionDB.executeQuery(query, idHotel, fechaFin, fechaInicio);

        ArrayList<TipoHabitacion> tipoHabitaciones = new ArrayList<>();
        while (rs != null && rs.next()) {
            TipoHabitacion tipoHabitacion = new TipoHabitacion();
            tipoHabitacion.setHabitaciones(null);
            tipoHabitacion.setDescripcion(rs.getString("descripcion"));
            tipoHabitacion.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));
            tipoHabitacion.setNombre(rs.getString("nombre"));
            tipoHabitaciones.add(tipoHabitacion);
        }
        return tipoHabitaciones;
    }

    //obtiene el ID de una habitación disponible de un hotel específico que coincide con un tipoTabitación determinado y no está reservada en el rango de fechas especificado
    public int obtenerIdHabitacionTipoHabitacionDisponible(int idTipoHabitacion, int idHotel, String fechaCheckIn, String fechaCheckOut) throws SQLException {
        String query = "SELECT h.* FROM habitacion h LEFT JOIN tipohabitacion th ON th.idTipoHabitacion = h.idTipoHabitacion WHERE h.idHotel = ? AND h.idHabitacion NOT IN (SELECT hr.idHabitacion FROM habitacionreserva hr JOIN reserva r ON hr.idReserva = r.idReserva WHERE r.fechaCheckIn < ? AND r.fechaCheckOut > ?) AND h.idHabitacion NOT IN (SELECT ro.idHabitacion FROM registroOcupacion ro WHERE ro.estado = true AND ro.medioRegistro = 'fuera del sistema') AND th.idTipoHabitacion = ? LIMIT 1;";
        ResultSet rs = connectionDB.executeQuery(query, idHotel, fechaCheckOut, fechaCheckIn, idTipoHabitacion);
        rs.next();
        return rs.getInt("idHabitacion");

    }

    public ArrayList<Habitacion> obtenerHabitacionesPorReserva(int idReserva) throws SQLException {

        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

        String query = "SELECT ha.*, th.nombre FROM habitacionreserva hr INNER JOIN habitacion ha ON hr.idHabitacion = ha.idHabitacion INNER JOIN tipohabitacion th ON ha.idTipoHabitacion = th.idTipoHabitacion WHERE hr.idReserva = ?;";
        ResultSet rs = connectionDB.executeQuery(query, idReserva);

        while (rs != null && rs.next()) {
            Habitacion habitacion = new Habitacion();
            habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
            habitacion.setNroHabitacion(rs.getInt("nroHabitacion"));
            habitacion.setIdHotel(rs.getInt("idHotel"));
            habitacion.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));
            TipoHabitacion tipoHabitacion = new TipoHabitacion(rs.getString("nombre"));
            habitacion.setTipoHabitacion(tipoHabitacion);
            ArrayList<Cama> camas = camaDAO.obtenerCamasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCamas(camas);
            ArrayList<Caracteristica> caracteristicas = caracteristicaDAO.obtenerCaracteristicasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCaracteristicas(caracteristicas);
            habitaciones.add(habitacion);
        }
        return habitaciones;
    }


    public boolean existeHabitacion(int idHabitacion) throws SQLException {
        return obtenerHabitacion(idHabitacion) != null;
    }
}
