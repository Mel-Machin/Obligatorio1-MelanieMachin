package org.example.view;

import org.example.controller.HotelController;
import org.example.model.Hotel;
import org.example.model.ScannerSingleton;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;


public class HotelView {

    private Scanner scanner = ScannerSingleton.getInstance();
    private HotelController hotelController;

    public HotelView() {
        this.hotelController = new HotelController();
    }


    public void menuHotel() {
        int opcion;
        boolean continuar = true;

        while (continuar) {
            IndexView.limpiarConsola();

            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_PURPLE + "      GESTIONANDO HOTELES    " + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "1. Registrar Hotel" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "2. Consultar Hotel" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "3. Actualizar Datos" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "4. Eliminar Hotel" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_WHITE + "0. Volver al Menú Principal" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.print(IndexView.ANSI_PURPLE + "Selecciona una opción: " + IndexView.ANSI_RESET);


            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        this.agregarHotel();
                        break;
                    case 2:
                        this.menuConsultarHotel();
                        break;
                    case 3:
                        this.modificarHotel();
                        break;
                    case 4:
                        this.eliminarHotel();
                        break;
                    case 0:
                        continuar = false;
                        IndexView indexView = new IndexView();
                        indexView.menuPrincipal();
                        break;
                    default:
                        System.out.println(IndexView.ANSI_RED + "Opción no válida. Inténtalo de nuevo." + IndexView.ANSI_RESET);
                        IndexView.esperar();
                        break;
                }
            } else {
                System.out.println(IndexView.ANSI_RED + "Entrada no válida. Por favor, ingresa un número." + IndexView.ANSI_RESET);
                scanner.nextLine();
            }
        }

        System.out.println(IndexView.ANSI_WHITE + "Volviendo al menú principal..." + IndexView.ANSI_RESET);
    }

    public void menuConsultarHotel() {
        boolean subMenu;
        do {
            subMenu = true;
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_PURPLE + "      CONSULTAR HOTEL" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "1. Consultar por Ciudad" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "2. Consultar por Nombre" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "3. Consultar por Fecha Disponible" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "4. Consultar por Categoría" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "5. Consultar Todo" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_WHITE + "0. Volver al Menú de Gestión de Hoteles" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.print(IndexView.ANSI_PURPLE + "Selecciona una opción: " + IndexView.ANSI_RESET);


            int subOpcion = scanner.hasNextInt() ? scanner.nextInt() : -1;
            scanner.nextLine();

            switch (subOpcion) {
                case 1:
                    this.obtenerHoteles();
                    System.out.print("Ingrese la ciudad : ");
                    String ciudadFiltro = scanner.nextLine();
                    this.filtrarHoteles("", ciudadFiltro, 0);
                    break;
                case 2:
                    hotelController.obtenerHoteles();
                    System.out.print("Ingrese el nombre: ");
                    String nombreFiltro = scanner.nextLine();
                    this.filtrarHoteles(nombreFiltro, "", 0);
                    break;
                case 3:
                    this.obtenerHoteles();
                    consultarHotelPorFechaDisponible();
                    break;
                case 4:
                    this.obtenerHoteles();
                    System.out.print("Ingrese la categoría: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Por favor, ingresa un número válido.");
                        scanner.next();
                    }
                    int categoriaFiltro = scanner.nextInt();
                    scanner.nextLine();
                    this.filtrarHoteles("", "", categoriaFiltro);
                    break;
                case 5:
                    this.obtenerHoteles();
                    System.out.print("Ingrese el nombre del hotel (dejar en blanco para omitir): ");
                    String nombre = scanner.nextLine();

                    System.out.print("Ingrese la ciudad del hotel (dejar en blanco para omitir): ");
                    String ciudad = scanner.nextLine();

                    System.out.print("Ingrese la ciudad del hotel (dejar en blanco para omitir): ");
                    int categoria = scanner.hasNextInt() ? scanner.nextInt() : 0;
                    scanner.nextLine();

                    this.filtrarHoteles(nombre, ciudad, categoria);
                    break;
                case 0:
                    subMenu = false;
                    break;
                default:
                    System.out.println(IndexView.ANSI_RED + "Opción no válida. Inténtalo de nuevo." + IndexView.ANSI_RESET);
                    IndexView.esperar();
                    break;
            }
        } while (subMenu);
    }


    public void agregarHotel() {
        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            IndexView indexView = new IndexView();
            indexView.menuPrincipal();
        } else {
            System.out.println("Ingrese los datos del hotel a continuación:");
            System.out.println("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Código de la ciudad: ");
            String ciudad = scanner.nextLine();
            System.out.println("Cantidad de estrellas: ");
            Integer cantidadEstrellas = scanner.nextInt();

            Hotel hotel = new Hotel(nombre, ciudad, cantidadEstrellas);
            boolean hotelAgregar = this.hotelController.agregarHotel(hotel);

            if (hotelAgregar) {
                System.out.println("Hotel agregado correctamente.");
            } else {
                System.out.println("Error al agregar el hotel.");
            }
        }
    }

    public void modificarHotel() {

        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            IndexView indexView = new IndexView();
            indexView.menuPrincipal();
        } else {

            System.out.print("Ingrese el ID del hotel a modificar: ");
            int idHotel = scanner.nextInt();
            scanner.nextLine();

            Hotel hotelActual = hotelController.obtenerHotel(idHotel);
            if (hotelActual == null) {
                System.out.println("Hotel con el ID " + idHotel + " no existe.");
                return;
            }

            System.out.println("Datos actuales del Hotel: ");
            ArrayList<Hotel> hotelTemporal = new ArrayList<>();
            hotelTemporal.add(hotelActual);
            CrearTabla.mostrarTabla(hotelTemporal);


            System.out.print("Ingrese el nuevo nombre del hotel (dejar vacío para omitir): ");
            String nuevoNombre = scanner.nextLine();
            if (nuevoNombre.isEmpty()) {
                nuevoNombre = hotelActual.getNombre();
            }

            System.out.print("Ingrese el nuevo código de la ciudad (dejar vacío para omitir): ");
            String nuevaCiudad = scanner.nextLine();
            if (nuevaCiudad.isEmpty()) {
                nuevaCiudad = hotelActual.getCodigoCiudad();
            }

            System.out.print("Ingrese la nueva cantidad de estrellas (0 para omitir): ");
            Integer nuevaCantidadEstrellas = scanner.nextInt();
            if (nuevaCantidadEstrellas == 0) {
                nuevaCantidadEstrellas = hotelActual.getCantidadEstrellas();
            }
            scanner.nextLine();
            Hotel hotelModificado = new Hotel(nuevoNombre, nuevaCiudad, nuevaCantidadEstrellas);
            if (hotelController.modificarHotel(idHotel, hotelModificado)) {
                System.out.println("Hotel modificado con éxito.");
            } else {
                System.out.println("Error al modificar el hotel.");
            }
        }

    }

    public void eliminarHotel() {
        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            IndexView indexView = new IndexView();
            indexView.menuPrincipal();
        } else {
            System.out.println("Ingrese el id del hotel a eliminar:");
            int idHotel = scanner.nextInt();
            scanner.nextLine();

            System.out.println("¿Está seguro de que desea eliminar el hotel con ID " + idHotel + "? (s/n): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                if (hotelController.eliminarHotel(idHotel)) {
                    System.out.println("Hotel eliminado con éxito.");
                } else {
                    System.out.println("Error al eliminar el hotel. Es posible que no exista o ya haya sido eliminado.");
                }
            } else {
                System.out.println("Eliminación cancelada.");
            }
        }
    }

    public void consultarHotelPorFechaDisponible() {

        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            IndexView indexView = new IndexView();
            indexView.menuPrincipal();
        } else {
            System.out.println("Ingrese la fecha de check-in (año-mes-día): ");
            String fechaInicio = scanner.nextLine();
            System.out.println("Ingrese la fecha de check-out (año-mes-día): ");
            String fechaFin = scanner.nextLine();
            ArrayList<Hotel> hoteles = hotelController.obtenerHotelesEnRangoFechas(fechaInicio, fechaFin);
            CrearTabla.mostrarTabla(hoteles);
        }
    }

    public void filtrarHoteles(String nombre, String ciudad, int categoria) {

        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            IndexView indexView = new IndexView();
            indexView.menuPrincipal();
        } else {
            ArrayList<Hotel> hotelesFiltrados = hotelController.filtroHoteles(nombre, ciudad, categoria);

            if (hotelesFiltrados.isEmpty()) {
                System.out.println("No se encontraron hoteles.");
            } else {
                System.out.println("Hoteles encontrados:");
                CrearTabla.mostrarTabla(hotelesFiltrados);
            }
            System.out.println("\nPresione Enter para volver al menú anterior...");
            scanner.nextLine();
        }
    }

    public void obtenerHoteles() {

        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            IndexView indexView = new IndexView();
            indexView.menuPrincipal();
        } else {
            ArrayList<Hotel> hotelesObtenidos = hotelController.obtenerHoteles();

            System.out.println("Hoteles registrados:");
            CrearTabla.mostrarTabla(hotelesObtenidos);

            System.out.println("\nPresione Enter para volver al menú anterior...");
            scanner.nextLine();
        }
    }
}
