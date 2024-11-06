package org.example.view;
import java.util.Scanner;


public class IndexView {

    // Secuencias ANSI para colores y estilos.
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean continuar = true;

        while (continuar) {
            limpiarConsola();

            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_PURPLE + "      GESTOR GLOBAL HOTELERO" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "1. Gestión de Hoteles" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "2. Gestión de Huéspedes" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "3. Gestión de Reservas" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "4. Gestión de Habitaciones" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "5. Gestión de Tarifas" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_WHITE + "0. Salir" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.print(IndexView.ANSI_PURPLE + "Selecciona una opción: " + IndexView.ANSI_RESET);


            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    limpiarConsola();
                    HotelView hotelView = new HotelView();
                    hotelView.menuHotel();
                    esperar();
                    break;
                case 2:
                    limpiarConsola();
                    HuespedView huespedView = new HuespedView();
                    huespedView.menuHuesped();
                    esperar();
                    break;
                case 3:
                    limpiarConsola();
                    ReservaView reservaView = new ReservaView();
                    reservaView.menuReserva();
                    esperar();
                    break;
                case 4:
                    limpiarConsola();
                    HabitacionView habitacionView = new HabitacionView();
                    habitacionView.menuHabitacion();
                    esperar();
                    break;
                case 5:
                    limpiarConsola();
                    TarifaView tarifaView = new TarifaView();
                    tarifaView.menuTarifa();
                    esperar();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println(ANSI_WHITE + "Opción no válida. Inténtalo de nuevo." + ANSI_RESET);
                    esperar();
                    break;
            }
        }

        System.out.println(ANSI_WHITE + "Gracias por usar el sistema. ¡Hasta luego!" + ANSI_RESET);
        scanner.close();
    }

    // Limpiar la consola
    public static void limpiarConsola() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error al limpiar la consola.");
        }
    }
    // Esperar
    public static void esperar() {
        System.out.println(ANSI_YELLOW + "Presiona 'Enter' para continuar..." + ANSI_RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

}
