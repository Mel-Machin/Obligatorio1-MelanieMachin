package org.example.controller;

import org.example.model.Hotel;
import org.example.DAO.HotelDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelController {

    private HotelDAO hotelDAO;

    public HotelController() { this.hotelDAO = new HotelDAO();}


    public boolean agregarHotel(Hotel hotel) {
        return this.hotelDAO.agregarHotel(hotel);
    }

    public boolean modificarHotel(int idHotel, Hotel hotel) {

        try {
            boolean existe = hotelDAO.existeHotel(idHotel);
            if(existe){
                return hotelDAO.modificarHotel(idHotel, hotel);

            }else{
                System.out.println("El hotel no existe.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar el hotel: " + e.getMessage());
            return false;
        }

    }

    public boolean eliminarHotel(int idHotel) {
        return hotelDAO.eliminarHotel(idHotel);
    }

    public Hotel obtenerHotel(int id){
        try {
            return this.hotelDAO.obtenerHotel(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Hotel> filtroHoteles(String nombre, String ciudad, int categoria){
        return hotelDAO.filtroHoteles(nombre, ciudad, categoria);
    }

    public ArrayList<Hotel>  obtenerHotelesEnRangoFechas(String fechaInicio,String fechaFin){
        try {
            return new HotelDAO().obtenerHotelesEnRangoFechas(fechaInicio,fechaFin);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public ArrayList<Hotel> obtenerHoteles(){
        try {
            return hotelDAO.obtenerHoteles();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
