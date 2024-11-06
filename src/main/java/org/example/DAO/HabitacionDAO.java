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


    public ArrayList<Habitacion> obtenerHabitaciones() throws SQLException {
        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

        String query = "SELECT * FROM Habitacion;";
        ResultSet rs = connectionDB.executeQuery(query);

        while (rs != null && rs.next()) {
            Habitacion habitacion = new Habitacion();
            habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
            habitacion.setNroHabitacion(rs.getInt("nroHabitacion"));
            habitacion.setIdHotel(rs.getInt("idHotel"));
            habitacion.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));

            ArrayList<Cama> camas = camaDAO.obtenerCamasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCamas(camas);
            ArrayList<Caracteristica> caracteristicas = caracteristicaDAO.obtenerCaracteristicasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCaracteristicas(caracteristicas);

            habitaciones.add(habitacion);
        }
        return habitaciones;
    }

    public Habitacion obtenerHabitacion(int idHabitacion) throws SQLException {
        String query = "SELECT * FROM Habitacion WHERE idHabitacion = ? ;";
        ResultSet rs = connectionDB.executeQuery(query, idHabitacion);

        while (rs != null && rs.next()) {
            Habitacion habitacion = new Habitacion();
            habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
            habitacion.setNroHabitacion(rs.getInt("nroHabitacion"));
            habitacion.setIdHotel(rs.getInt("idHotel"));
            habitacion.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));

            ArrayList<Cama> camas = camaDAO.obtenerCamasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCamas(camas);
            ArrayList<Caracteristica> caracteristicas = caracteristicaDAO.obtenerCaracteristicasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCaracteristicas(caracteristicas);

            return habitacion;
        }

        return null;
    }

    public ArrayList<Habitacion> filtroHabitacionesConReserva() throws SQLException{
        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

        String query = "SELECT h.* FRom habitacion h left join habitacionreserva hr on h.idHabitacion = hr.idhabitacion where hr.idreserva is not null;";
        ResultSet rs = connectionDB.executeQuery(query);

        while (rs != null && rs.next()) {
            Habitacion habitacion = new Habitacion();
            habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
            habitacion.setNroHabitacion(rs.getInt("nroHabitacion"));
            habitacion.setIdHotel(rs.getInt("idHotel"));
            habitacion.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));

            ArrayList<Cama> camas = camaDAO.obtenerCamasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCamas(camas);
            ArrayList<Caracteristica> caracteristicas = caracteristicaDAO.obtenerCaracteristicasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCaracteristicas(caracteristicas);

            habitaciones.add(habitacion);
        }
        return habitaciones;
    }

    public ArrayList<Habitacion> filtroHabitacionesSinReserva() throws SQLException{
        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

        String query = "SELECT h.* FRom habitacion h left join habitacionreserva hr on h.idHabitacion = hr.idhabitacion where hr.idreserva is null;";
        ResultSet rs = connectionDB.executeQuery(query);

        while (rs != null && rs.next()) {
            Habitacion habitacion = new Habitacion();
            habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
            habitacion.setNroHabitacion(rs.getInt("nroHabitacion"));
            habitacion.setIdHotel(rs.getInt("idHotel"));
            habitacion.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));

            ArrayList<Cama> camas = camaDAO.obtenerCamasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCamas(camas);
            ArrayList<Caracteristica> caracteristicas = caracteristicaDAO.obtenerCaracteristicasPorHabitacion(rs.getInt("idHabitacion"));
            habitacion.setCaracteristicas(caracteristicas);

            habitaciones.add(habitacion);
        }
        return habitaciones;
    }

    //Obtiene un lista de tiposHabitaciones disponibles en un hotel específico que no están reservadas en el rango de fechas
    public ArrayList<TipoHabitacion> obtenerTipoHabitacionesHotelDisponibles(String fechaInicio, String fechaFin,int idHotel) throws SQLException{
        String query ="SELECT th.* FROM habitacion h LEFT JOIN tipohabitacion th ON th.idTipoHabitacion = h.idTipoHabitacion WHERE h.idHotel = ? AND h.idHabitacion NOT IN (SELECT hr.idHabitacion FROM habitacionreserva hr JOIN reserva r ON hr.idReserva = r.idReserva WHERE r.fechaCheckIn < ? AND r.fechaCheckOut > ?);";
        ResultSet rs = connectionDB.executeQuery(query,idHotel,fechaFin,fechaInicio);

        ArrayList<TipoHabitacion> tipoHabitaciones = new ArrayList<>();
        while (rs != null && rs.next()) {
            TipoHabitacion tipoHabitacion = new TipoHabitacion();
            tipoHabitacion.setHabitaciones(null);
            tipoHabitacion.setDescripcion(rs.getString("descripcion"));
            tipoHabitacion.setIdTipoHabitacion(rs.getInt("idTipoHabitacion"));
            tipoHabitacion.setNombre(rs.getString("nombre"));
           tipoHabitaciones.add(tipoHabitacion);
        }
        return  tipoHabitaciones;
    }

    //obtiene el ID de una habitación disponible de un hotel específico que coincide con un tipoTabitación determinado y no está reservada en el rango de fechas especificado
    public int obtenerIdHabitacionTipoHabitacionDisponible(int idTipoHabitacion, int idHotel, String fechaCheckIn, String fechaCheckOut) throws SQLException{
        String query = "SELECT h.* FROM habitacion h LEFT JOIN tipohabitacion th ON th.idTipoHabitacion = h.idTipoHabitacion WHERE h.idHotel = ? AND h.idHabitacion NOT IN (SELECT hr.idHabitacion FROM habitacionreserva hr JOIN reserva r ON hr.idReserva = r.idReserva WHERE r.fechaCheckIn < ? AND r.fechaCheckOut > ?) AND h.idHabitacion NOT IN (SELECT ro.idHabitacion FROM registroOcupacion ro WHERE ro.estado = true AND ro.medioRegistro = 'fuera del sistema') AND th.idTipoHabitacion = ? LIMIT 1;";
        ResultSet rs = connectionDB.executeQuery(query,idHotel,fechaCheckOut,fechaCheckIn,idTipoHabitacion);
        rs.next();
        return rs.getInt("idHabitacion");

    }



    public boolean existeHabitacion(int idHabitacion) throws SQLException {
        return obtenerHabitacion(idHabitacion) != null;
    }

}
