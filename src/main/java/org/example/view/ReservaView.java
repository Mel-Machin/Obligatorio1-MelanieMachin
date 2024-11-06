package org.example.view;

import org.example.controller.HabitacionController;
import org.example.controller.HotelController;
import org.example.controller.ReservaController;
import org.example.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class ReservaView {

    private Scanner scanner = ScannerSingleton.getInstance();
    private ReservaController reservaController;
    private SimpleDateFormat formato = new SimpleDateFormat("YYYY-mm-dd");
    private HotelController hotelController;
    private HabitacionController habitacionController;

    public ReservaView() {
        this.reservaController = new ReservaController();
        this.hotelController = new HotelController();
        this.habitacionController = new HabitacionController();
    }

    public void menuReserva() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean continuar = true;

        while (continuar) {
            IndexView.limpiarConsola();

            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_PURPLE + "      GESTIONANDO RESERVAS" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "1. Crear Reserva" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "2. Consultar Reserva" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "3. Actualizar Reserva" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "4. Cancelar Reserva" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_WHITE + "0. Volver al Menú Principal" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.print(IndexView.ANSI_PURPLE + "Selecciona una opción: " + IndexView.ANSI_RESET);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    this.agregarReserva();
                    break;
                case 2:
                    this.obtenerReservas();
                    break;
                case 3:
                    this.modificarReserva();
                    break;
                case 4:
                    this.eliminarReserva();
                    break;
                case 5:
                    continuar = false;
                    IndexView indexView = new IndexView();
                    indexView.menuPrincipal();
                    break;
                default:
                    System.out.println(IndexView.ANSI_RED + "Opción no válida. Inténtalo de nuevo." + IndexView.ANSI_RESET);
                    IndexView.esperar();
                    break;
            }
        }

        System.out.println(IndexView.ANSI_WHITE + "Volviendo al menú principal..." + IndexView.ANSI_RESET);
        scanner.close();
    }

    public void agregarReserva() {

        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            IndexView indexView = new IndexView();
            indexView.menuPrincipal();
        }

        try {
            System.out.println("Fecha de Check-in (Año-Mes-Día): ");
            String fechaCheckIn = scanner.nextLine();
            Date fechaCheckInD = formato.parse(fechaCheckIn);
            String estadoCheckIn = "pendiente";

            System.out.println("Fecha de Check-out (Año-Mes-Día): ");
            String fechaCheckOut = scanner.nextLine();
            Date fechaCheckOutD = formato.parse(fechaCheckOut);
            String estadoCheckOut = "pendiente";

            ArrayList<Hotel> hoteles = hotelController.obtenerHotelesEnRangoFechas(fechaCheckIn, fechaCheckOut);
            CrearTabla.mostrarTabla(hoteles);
            System.out.println("Ingrese el ID del hotel:");
            int idHotel = scanner.nextInt();
            boolean existeHotel = false;
            for (Hotel hotel : hoteles) {
                if (hotel.getIdHotel().equals(idHotel)) {
                    existeHotel = true;
                    break;
                }
            }

            if (existeHotel) {
                ArrayList<TipoHabitacion> tipoHabitaciones = habitacionController.obtenerTipoHabitacionesHotelDisponibles(fechaCheckIn, fechaCheckOut, idHotel);
                CrearTabla.mostrarTabla(tipoHabitaciones);
                System.out.println("Ingrese el ID del tipo de habitación:");
                int idTipoHabitacion = scanner.nextInt();
                boolean existeTipoHabitacion = false;
                for (TipoHabitacion tipoHabitacion : tipoHabitaciones) {
                    if (tipoHabitacion.getIdTipoHabitacion().equals(idTipoHabitacion)) {
                        existeTipoHabitacion = true;
                        break;
                    }
                }
                if (existeTipoHabitacion) {
                    int idHabitacion = habitacionController.obtenerIdHabitacionTipoHabitacionDisponible(idTipoHabitacion, idHotel, fechaCheckIn, fechaCheckOut);
                    System.out.println("Ingrese el ID del huésped:");
                    int idHuesped = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Fecha de reserva (Año-Mes-Día): ");
                    String fechaReserva = scanner.nextLine();
                    Date fechaReservaD = formato.parse(fechaReserva);
                    System.out.println("Estado del pago (pendiente, realizado): ");
                    String estadoPago = scanner.nextLine();
                    System.out.println("Cantidad de personas: ");
                    int cantidadPersonas = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Observaciones: ");
                    String observaciones = scanner.nextLine();
                    if(observaciones == " "){
                        observaciones = "Ninguna";
                    }

                    Reserva reserva = new Reserva(idHuesped, idHotel, -1, estadoPago, fechaReservaD, fechaCheckInD, estadoCheckIn, fechaCheckOutD, estadoCheckOut, cantidadPersonas, observaciones);
                    int idReserva = this.reservaController.agregarReserva(reserva);
                    this.reservaController.agregarReservaHabitacion(idHabitacion, idReserva);
                    if (idReserva != -1) {
                        System.out.println("Reserva agregada correctamente.");
                    } else {
                        System.out.println("Error al agregar la reserva.");
                    }


                } else {
                    System.out.println("El ID del tipo de habitación no se encuentra en la lista.");
                }
            } else {
                System.out.println("El ID ingresado no se encuentra en la lista de hoteles.");
            }
        } catch (ParseException e) {
            System.out.println("Error en el formato de la fecha. Por favor, use el formato Año-Mes-Día.");
        }


    }

    public void modificarReserva() {

        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            IndexView indexView = new IndexView();
            indexView.menuPrincipal();
        }

        try {
            System.out.println("Ingrese el ID de la reserva:");
            int idReserva = scanner.nextInt();
            scanner.nextLine();

            Reserva reservaActual = reservaController.obtenerReserva(idReserva);
            if (reservaActual == null) {
                System.out.println("Reserva con el ID " + idReserva + " no existe.");
                return;
            }

            ArrayList<Reserva> reservaTemporal = new ArrayList<>();
            reservaTemporal.add(reservaActual);
            System.out.println("Datos actuales de la reserva: ");
            CrearTabla.mostrarTabla(reservaTemporal);


            System.out.println("Ingrese el nuevo ID del huésped (ingrese 0 si no desea modificar): ");
            Integer nuevoIdHuesped = scanner.nextInt();
            if (nuevoIdHuesped == 0) {
                nuevoIdHuesped = reservaActual.getIdHuesped();
            }

            System.out.println("Ingrese el nuevo ID del hotel (ingrese  0 si no desea modificar): ");
            Integer nuevoIdHotel = scanner.nextInt();
            if (nuevoIdHotel == 0) {
                nuevoIdHotel = reservaActual.getIdHotel();
            }

            System.out.println("Ingrese el nuevo ID de la tarifa (ingrese 0 si no desea modificar): ");
            Integer nuevoIdTarifa = scanner.nextInt();
            if (nuevoIdTarifa == 0) {
                nuevoIdTarifa = reservaActual.getIdTarifa();
            }

            System.out.println("Ingrese el nuevo estado del pago (debe ser 'pendiente' o 'realizado'; deje vacío si no desea modificar): ");
            String nuevoEstadoPago = scanner.nextLine();
            if (nuevoEstadoPago.isEmpty()) {
                nuevoEstadoPago = reservaActual.getEstadoPago();
            }

            System.out.println("Ingrese la nueva fecha de la reserva (Año-Mes-Día; deje vacío si no desea modificar):");
            String nuevaFechaReserva = scanner.nextLine();
            Date fechaReserva;
            if (nuevaFechaReserva.isEmpty()) {
                fechaReserva = reservaActual.getFechaReserva();
            } else {
                fechaReserva = formato.parse(nuevaFechaReserva);
            }

            System.out.println("Ingrese la nueva fecha del Check-in (Año-Mes-Día; deje vacío si no desea modificar): ");
            String nuevaFechaCheckIn = scanner.nextLine();
            Date fechaCheckIn;
            if (nuevaFechaCheckIn.isEmpty()) {
                fechaCheckIn = reservaActual.getFechaCheckIn();
            } else {
                fechaCheckIn = formato.parse(nuevaFechaCheckIn);
            }

            System.out.println("Ingrese el nuevo estado del Check-in (debe ser 'pendiente' o 'realizado'; deje vacío si no desea modificar): ");
            String nuevoEstadoCheckIn = scanner.nextLine();
            if (nuevoEstadoCheckIn.isEmpty()) {
                nuevoEstadoCheckIn = reservaActual.getEstadoCheckIn();
            }

            System.out.println("Ingrese la nueva fecha del Check-out (Año-Mes-Día; deje vacío si no desea modificar): ");
            String nuevaFechaCheckOut = scanner.nextLine();
            Date fechaCheckOut;
            if (nuevaFechaCheckOut.isEmpty()) {
                fechaCheckOut = reservaActual.getFechaCheckOut();
            } else {
                fechaCheckOut = formato.parse(nuevaFechaCheckOut);
            }

            System.out.println("Ingrese el nuevo estado del Check-out (debe ser 'pendiente' o 'realizado'; deje vacío si no desea modificar): ");
            String nuevoEstadoCheckOut = scanner.nextLine();
            if (nuevoEstadoCheckOut.isEmpty()) {
                nuevoEstadoCheckOut = reservaActual.getEstadoCheckOut();
            }


            System.out.println("Ingrese la nueva cantidad de personas (ingrese 0 si no desea modificar): ");
            Integer nuevaCantidadPersonas = scanner.nextInt();
            if (nuevaCantidadPersonas == 0) {
                nuevaCantidadPersonas = reservaActual.getCantidadPersonas();
            }

            System.out.println("Ingrese las nuevas observaciones (deje vacío si no desea modificar): ");
            String nuevaObservaciones = scanner.nextLine();
            if (nuevaObservaciones.isEmpty()) {
                nuevaObservaciones = reservaActual.getObservacion();
            }

            scanner.nextLine();
            Reserva reservaModificada = new Reserva(nuevoIdHuesped, nuevoIdHotel, nuevoIdTarifa, nuevoEstadoPago, fechaReserva, fechaCheckIn, nuevoEstadoCheckIn, fechaCheckOut, nuevoEstadoCheckOut, nuevaCantidadPersonas, nuevaObservaciones);
            if (reservaController.modificarReserva(idReserva, reservaModificada)) {
                System.out.println("Reserva modificada con éxito.");
            } else {
                System.out.println("Error al modificar la reserva.");
            }
        } catch (ParseException e) {
            System.out.println("Error: Formato de fecha inválido. Por favor, ingrese la fecha en el formato Año-Mes-Día.");
            e.printStackTrace();
        }
    }

    public void eliminarReserva() {
        System.out.println("Ingrese el ID de la reserva a eliminar:");
        int idReserva = scanner.nextInt();
        scanner.nextLine();

        System.out.println("¿Está seguro de que desea eliminar la reserva con ID " + idReserva + "? (s/n)");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            if (reservaController.eliminarReserva(idReserva)) {
                System.out.println("Reserva eliminada con éxito.");
            } else {
                System.out.println("Error al eliminar la reserva. Puede que no exista o ya ha sido eliminada.");
            }
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    public void obtenerReservas() {
        ArrayList<Reserva> reservasObtenidas = reservaController.obtenerReservas();

        System.out.println("Reservas registradas:");
        CrearTabla.mostrarTabla(reservasObtenidas);

        System.out.println("\nPresione Enter para volver al menú anterior...");
        scanner.nextLine();
    }
}
