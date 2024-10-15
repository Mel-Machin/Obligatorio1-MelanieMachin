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
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

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
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean continuar = true;

        while (continuar) {
            limpiarConsola();

            System.out.println( ANSI_BOLD + ANSI_CYAN + "===============================" + ANSI_RESET);
            System.out.println( ANSI_BOLD + ANSI_PURPLE + "      GESTIÓN DE HOTEL" + ANSI_RESET);
            System.out.println( ANSI_BOLD + ANSI_CYAN + "===============================" + ANSI_RESET);
            System.out.println( ANSI_PURPLE + "1. Gestión del Hotel" + ANSI_RESET);
            System.out.println( ANSI_PURPLE + "2. Gestión de Huéspedes" + ANSI_RESET);
            System.out.println( ANSI_PURPLE + "3. Gestión de Reservas" + ANSI_RESET);
            System.out.println( ANSI_PURPLE + "4. Gestión de Habitaciones" + ANSI_RESET);
            System.out.println( ANSI_WHITE + "5. Salir" + ANSI_RESET);
            System.out.println( ANSI_BOLD + ANSI_CYAN + "===============================" + ANSI_RESET);
            System.out.print( ANSI_PURPLE + "Selecciona una opción: " + ANSI_RESET);


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

}
