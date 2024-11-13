package org.example.DAO;
import org.example.model.Ciudad;
import org.example.model.Hotel;
import org.example.DAO.connection.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class HotelDAO {

    private ConnectionDB connectionDB;

    public HotelDAO() {
        this.connectionDB = new ConnectionDB();
    }


    //Alta, Baja y Modificacion
    public boolean agregarHotel(Hotel hotel) {
        String query = "INSERT INTO Hotel (nombre, codigoCiudad, cantidadEstrellas) VALUES(?,?,?)";
        int filasAfectadas =  connectionDB.executeUpdate(query, hotel.getNombre(), hotel.getCodigoCiudad(), hotel.getCantidadEstrellas());
        return filasAfectadas >0;
    }

    public boolean modificarHotel(int idHotel, Hotel hotel) {
        String query = "UPDATE Hotel SET nombre = ?, codigoCiudad = ?, cantidadEstrellas = ? WHERE idHotel = ?";
        int filasAfectadas = connectionDB.executeUpdate(query, hotel.getNombre(), hotel.getCodigoCiudad(), hotel.getCantidadEstrellas(), idHotel);
        return filasAfectadas > 0;
    }

    public boolean eliminarHotel(int idHotel) {
        String query = "DELETE FROM Hotel WHERE idHotel = ?";
        int filasEliminadas = connectionDB.executeUpdate(query, idHotel);
        return filasEliminadas > 0; // Retorna true si se eliminó al menos un registro
    }

    //Obtener
    public Hotel obtenerHotel(int id) throws SQLException {
        String query = "SELECT h.*,c.nombre AS nombreCiudad,c.codigoPais FROM Hotel h LEFT JOIN Ciudad c ON c.codigoPostal = h.codigoCiudad WHERE idHotel = ?";
        ResultSet rs = connectionDB.executeQuery(query, id);

        if (rs != null && rs.next()) {
            Hotel hotel = new Hotel();
            hotel.setIdHotel(rs.getInt("idHotel"));
            hotel.setNombre(rs.getString("nombre"));
            Ciudad ciudad = new Ciudad(rs.getString("codigoPais"),rs.getString("nombreCiudad"),rs.getString("codigoCiudad"));
            hotel.setCiudad(ciudad);
            hotel.setCantidadEstrellas(rs.getInt("cantidadEstrellas"));
            hotel.setCodigoCiudad(rs.getString("codigoCiudad"));
            return hotel;
        }

        return null;
    }

    public ArrayList<Hotel> obtenerHoteles() throws SQLException {
        ArrayList<Hotel> hoteles = new ArrayList<>();

        String query = "SELECT h.*,c.nombre AS nombreCiudad,c.codigoPais FROM Hotel h LEFT JOIN Ciudad c ON c.codigoPostal = h.codigoCiudad ";
        ResultSet rs = connectionDB.executeQuery(query);

        while (rs != null && rs.next()) {
            Hotel hotel = new Hotel();
            hotel.setIdHotel(rs.getInt("idHotel"));
            Ciudad ciudad = new Ciudad(rs.getString("codigoPais"),rs.getString("nombreCiudad"),rs.getString("codigoCiudad"));
            hotel.setCiudad(ciudad);
            hotel.setCodigoCiudad(rs.getString("codigoCiudad"));
            hotel.setNombre(rs.getString("nombre"));
            hotel.setCantidadEstrellas(rs.getInt("cantidadEstrellas"));
            hoteles.add(hotel);
        }
        return hoteles;
    }

    public ArrayList<Hotel> filtroHoteles(String nombre, String ciudad, int categoria) {

        ArrayList<Hotel> hoteles = new ArrayList<>();
        String query = "SELECT h.*,c.nombre AS nombreCiudad,c.codigoPais FROM Hotel h LEFT JOIN Ciudad c ON c.codigoPostal = h.codigoCiudad WHERE 1=1";
        ArrayList<Object> params = new ArrayList<>();




        if (nombre != null && !nombre.isEmpty()) {
            query += " AND h.nombre LIKE ?";
            params.add("%" + nombre + "%");
        }

        if (ciudad != null && !ciudad.isEmpty()) {
            query += " AND c.nombre LIKE ?";
            params.add("%" + ciudad + "%");
        }

        if (categoria > 0) {
            query += " AND h.cantidadEstrellas = ?";
            params.add(categoria);
        }


        try {
            ResultSet rs = connectionDB.executeQuery(query, params.toArray());

            while (rs != null && rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setIdHotel(rs.getInt("idHotel"));
                hotel.setNombre(rs.getString("nombre"));
                Ciudad ciudad1 = new Ciudad(rs.getString("codigoPais"),rs.getString("nombreCiudad"),rs.getString("codigoCiudad"));
                hotel.setCiudad(ciudad1);
                hotel.setCodigoCiudad(rs.getString("codigoCiudad"));
                hotel.setCantidadEstrellas(rs.getInt("cantidadEstrellas"));
                hoteles.add(hotel);
            }
        } catch (SQLException e) {
            System.err.println("Error al filtrar hoteles: " + e.getMessage());
        }

        return hoteles;
    }

    public ArrayList<Hotel> obtenerHotelesEnRangoFechas(String fechaInicio, String fechaFin) throws SQLException{
        String query = "SELECT h.*,c.nombre AS nombreCiudad,c.codigoPais FROM Hotel h LEFT JOIN Ciudad c ON c.codigoPostal = h.codigoCiudad JOIN habitacion h1 ON h.idHotel = h1.idHotel WHERE NOT EXISTS ( SELECT 1 FROM habitacionreserva hr JOIN reserva r ON hr.idReserva = r.idReserva WHERE hr.idHabitacion = h1.idHabitacion AND ( (r.fechaCheckIn < ? AND r.fechaCheckOut > ?) ) ) GROUP BY h.idHotel, h.nombre;";
        ArrayList<Hotel> hoteles = new ArrayList<>();

        ResultSet rs = connectionDB.executeQuery(query,fechaFin,fechaInicio);

        while (rs != null && rs.next()) {
            Hotel hotel = new Hotel();
            hotel.setIdHotel(rs.getInt("idHotel"));
            Ciudad ciudad = new Ciudad(rs.getString("codigoPais"),rs.getString("nombreCiudad"),rs.getString("codigoCiudad"));
            hotel.setCiudad(ciudad);
            hotel.setCodigoCiudad(rs.getString("codigoCiudad"));
            hotel.setNombre(rs.getString("nombre"));
            hotel.setCantidadEstrellas(rs.getInt("cantidadEstrellas"));
            hoteles.add(hotel);
        }
        return hoteles;
    }


    public boolean existeHotel(int id) throws SQLException {
        return obtenerHotel(id) != null;
    }
}


