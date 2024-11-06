package org.example.view;

import org.example.DAO.CamaDAO;
import org.example.controller.CamaController;
import org.example.controller.CaracteristicaController;
import org.example.controller.HabitacionController;
import org.example.model.*;

import java.util.ArrayList;
import java.util.Scanner;


public class HabitacionView {


    private Scanner scanner = ScannerSingleton.getInstance();
    private HabitacionController habitacionController;
    private CamaController camaController;
    private CaracteristicaController caracteristicaController;

    public HabitacionView() {
        this.habitacionController = new HabitacionController();
        this.camaController = new CamaController();
        this.caracteristicaController = new CaracteristicaController();
    }


    public void menuHabitacion() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean continuar = true;

        while (continuar) {
            IndexView.limpiarConsola();

            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_PURPLE + "      GESTIONANDO HABITACIONES" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "1. Registrar Habitación" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "2. Consultar Habitación" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "3. Actualizar Habitación" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "4. Eliminar Habitación" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_WHITE + "0. Volver al Menú Principal" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.print(IndexView.ANSI_PURPLE + "Selecciona una opción: " + IndexView.ANSI_RESET);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    this.agregarHabitacion();
                    break;
                case 2:
                    this.obtenerHabitaciones();
                    break;
                case 3:
                    this.modificarHabitacion();
                    break;
                case 4:
                    this.eliminarHabitacion();
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
        }

        System.out.println(IndexView.ANSI_WHITE + "Volviendo al menú principal..." + IndexView.ANSI_RESET);
        scanner.close();
    }

    public void menuAgregarDetallesHabitacion(int idHabitacion) {

        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            IndexView indexView = new IndexView();
            indexView.menuPrincipal();
        } else {
            boolean continuar = true;
            while (continuar) {
                System.out.println("\nSeleccione una opción:");
                System.out.println("1. Agregar cama");
                System.out.println("2. Agregar característica");
                System.out.println("3. Finalizar y guardar");
                System.out.print("Opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        boolean bandera = true;
                        while (bandera) {
                            System.out.println("Seleccione el tipo de cama:");
                            System.out.println("1: Individual");
                            System.out.println("2: Matrimonial");
                            System.out.println("3: Queen");
                            System.out.println("4: King");
                            System.out.println("5: Redonda");
                            System.out.println("6: Sofá Cama");
                            System.out.println("7: Litera");

                            int opcionTipoCama = 0;
                            opcionTipoCama = scanner.nextInt();

                            String tipoCamaSelecionado = "";
                            switch (opcionTipoCama) {
                                case 1:
                                    tipoCamaSelecionado = "Individual";
                                    break;
                                case 2:
                                    tipoCamaSelecionado = "Matrimonial";
                                    break;
                                case 3:
                                    tipoCamaSelecionado = "Queen";
                                    break;
                                case 4:
                                    tipoCamaSelecionado = "King";
                                    break;
                                case 5:
                                    tipoCamaSelecionado = "Redonda";
                                    break;
                                case 6:
                                    tipoCamaSelecionado = "Sofá Cama";
                                    break;
                                case 7:
                                    tipoCamaSelecionado = "Litera";
                                    break;
                                default:
                                    System.out.println("Debe ingresar un tipo de cama válido.");
                                    break;
                            }
                            if (tipoCamaSelecionado != "") {
                                boolean estado = camaController.agregarCama(idHabitacion, tipoCamaSelecionado);
                                if (estado) {
                                    System.out.println("Cama agregada correctamente.");
                                }
                            }
                            System.out.print("Presione 1 para agregar otra cama o 0 para terminar: ");
                            int opcionBandera = scanner.nextInt();
                            scanner.nextLine();
                            if (opcionBandera == 0) {
                                bandera = false;
                            }
                        }
                        break;
                    case 2:
                        boolean bandera2 = true;
                        while (bandera2) {
                            System.out.println("Ingrese los datos de la característica a continuación: ");
                            System.out.println("Nombre: ");
                            String nombre = scanner.nextLine();

                            System.out.println("Seleccione el tipo de característica:");
                            System.out.println("1: Vista");
                            System.out.println("2: Baño");
                            System.out.println("3: Climatización ");
                            System.out.println("4: Entretenimiento ");
                            System.out.println("5: Conectividad ");
                            System.out.println("6: Seguridad ");
                            System.out.println("7: Servicios de cortesía");
                            System.out.println("8: Accesibilidad");
                            System.out.println("9: Mobiliario adicional");
                            System.out.println("10: Privacidad");
                            int opcionTipoCaracteristica = 0;
                            opcionTipoCaracteristica = scanner.nextInt();

                            String tipoCaracteristicaSelecionado = "";

                            switch (opcionTipoCaracteristica) {
                                case 1:
                                    tipoCaracteristicaSelecionado = "Vista";
                                    break;
                                case 2:
                                    tipoCaracteristicaSelecionado = "Baño";
                                    break;
                                case 3:
                                    tipoCaracteristicaSelecionado = "Climatización";
                                    break;
                                case 4:
                                    tipoCaracteristicaSelecionado = "Entretenimiento";
                                    break;
                                case 5:
                                    tipoCaracteristicaSelecionado = "Conectividad";
                                    break;
                                case 6:
                                    tipoCaracteristicaSelecionado = "Seguridad";
                                    break;
                                case 7:
                                    tipoCaracteristicaSelecionado = "Servicios de cortesía";
                                    break;
                                case 8:
                                    tipoCaracteristicaSelecionado = "Accesibilidad";
                                    break;
                                case 9:
                                    tipoCaracteristicaSelecionado = "Mobiliario adicional";
                                    break;
                                case 10:
                                    tipoCaracteristicaSelecionado = "Privacidad";
                                    break;
                                default:
                                    System.out.println("Debe ingresar un tipo de característica válido.");
                                    break;
                            }
                            if (tipoCaracteristicaSelecionado != "") {
                                boolean estado = caracteristicaController.agregarCaracteristica(nombre, tipoCaracteristicaSelecionado, idHabitacion);
                                if (estado) {
                                    System.out.println("Caracteristica agregada correctamente");
                                }

                            } else {
                                System.out.println("Debe ingresar un tipo de caracteristica valido");
                            }
                            System.out.println("Precione 1 para agregar otra caracteristica");
                            System.out.println("Precione 0 para terminar");
                            int opcionBandera2 = scanner.nextInt();
                            scanner.nextLine();
                            if (opcionBandera2 == 0) {
                                bandera2 = false;
                            }
                        }

                        System.out.println("Caracteriostica agregada correctamente.");
                        break;

                    case 3:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            }
        }
    }

    public void agregarHabitacion() {

        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            IndexView indexView = new IndexView();
            indexView.menuPrincipal();
        } else {
            System.out.println("Ingrese los datos de la habitacion a continuacion.");
            System.out.println("Numero de habitacion: ");
            Integer numeroHabitacion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Id hotel: ");
            Integer idHotel = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Id tipo de habitacion: ");
            Integer idTipoHabitacion = scanner.nextInt();
            scanner.nextLine();


            Habitacion habitacion = new Habitacion(numeroHabitacion, idHotel, idTipoHabitacion);
            int idHabitacion = this.habitacionController.agregarHabitacion(habitacion);

            if (idHabitacion != -1) {
                System.out.println("Habitacion agregada correctamente.");
                this.menuAgregarDetallesHabitacion(idHabitacion);
            } else {
                System.out.println("Error al agregar la habitacion.");
            }
        }
    }

    public void modificarHabitacion() {
        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            IndexView indexView = new IndexView();
            indexView.menuPrincipal();
        } else {
            System.out.println("Ingrese el ID de la habitacion a modificar:");
            int idHabitacion = scanner.nextInt();
            scanner.nextLine();

            Habitacion habitacionActual = habitacionController.obtenerHabitacion(idHabitacion);
            if (habitacionActual == null) {
                System.out.println("Habitacion con el ID " + idHabitacion + " no existe.");
                return;
            }

            System.out.println("Datos actuales de la Habitacion: ");
            System.out.println("Numero de la habitacion: " + habitacionActual.getNroHabitacion());
            System.out.println("ID hotel: " + habitacionActual.getIdHotel());
            System.out.println("ID habitacion: " + habitacionActual.getIdTipoHabitacion());


            System.out.println("Ingrese el nuevo numero de la habitacion (0 para omitir): ");
            int nuevoNroHabitacion = scanner.nextInt();
            if (nuevoNroHabitacion == 0) {
                nuevoNroHabitacion = habitacionActual.getNroHabitacion();
            }

            System.out.println("Ingrese el nuevo ID del hotel (0 para omitir): ");
            int nuevoIdHotel = scanner.nextInt();
            if (nuevoIdHotel == 0) {
                nuevoIdHotel = habitacionActual.getIdHotel();
            }

            System.out.println("Ingrese el nuevo ID del tipo de habitacion (0 para omitir): ");
            int nuevoIdTipoHabitacion = scanner.nextInt();
            if (nuevoIdTipoHabitacion == 0) {
                nuevoIdTipoHabitacion = habitacionActual.getIdTipoHabitacion();
            }

            scanner.nextLine();

            Habitacion habitacionModificada = new Habitacion(nuevoNroHabitacion, nuevoIdHotel, nuevoIdTipoHabitacion);
            if (habitacionController.modificarHabitacion(idHabitacion, habitacionModificada)) {
                System.out.println("Habitacion modificada con éxito.");
            } else {
                System.out.println("Error al modificar la habitacion.");
            }
        }

    }

    public void eliminarHabitacion() {

        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            IndexView indexView = new IndexView();
            indexView.menuPrincipal();
        } else {
            System.out.println("Ingrese el ID de la habitacion a eliminar:");
            int idHabitacion = scanner.nextInt();
            scanner.nextLine();

            System.out.println("¿Está seguro de que desea eliminar la habitacion con ID " + idHabitacion + "? (s/n)");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                if (habitacionController.eliminarHabitacion(idHabitacion)) {
                    System.out.println("Habitacion eliminada con éxito.");
                } else {
                    System.out.println("Error al eliminar la habitacion. Puede que no exista o ya ha sido eliminado.");
                }
            } else {
                System.out.println("Eliminación cancelada.");
            }
        }
    }

    public void obtenerHabitaciones() {
        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            IndexView indexView = new IndexView();
            indexView.menuPrincipal();
        }else{
        System.out.println("Seleccione una opción para filtrar las habitaciones:");
        System.out.println("1. Todas");
        System.out.println("2. Reservadas");
        System.out.println("3. Sin reserva");
        System.out.println("0. Volver al menú anterior");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 0) {
            System.out.println("Volviendo al menú anterior...");
            return;
        }

        ArrayList<Habitacion> habitacionesObtenidas;

        switch (opcion) {
            case 1:
                habitacionesObtenidas = habitacionController.obtenerHabitaciones();
                CrearTabla.mostrarTabla(habitacionesObtenidas);
                break;
            case 2:
                habitacionesObtenidas = habitacionController.filtroHabitacionesConReserva();
                CrearTabla.mostrarTabla(habitacionesObtenidas);

                break;
            case 3:
                habitacionesObtenidas = habitacionController.filtroHabitacionesSinReserva();
                CrearTabla.mostrarTabla(habitacionesObtenidas);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }

        System.out.println("\nPresione Enter para volver al menú anterior...");
        scanner.nextLine();
    }
}
}
