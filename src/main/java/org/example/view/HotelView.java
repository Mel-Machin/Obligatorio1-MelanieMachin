package org.example.view;
import java.util.Scanner;



public class HotelView {

        public void menuHotel() {
            Scanner scanner = new Scanner(System.in);
            int opcion;
            boolean continuar = true;

            while (continuar) {
                IndexView.limpiarConsola();

                System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_PURPLE + "      GESTIÓN DEL HOTEL" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_PURPLE + "1. Registrar Hotel" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_PURPLE + "2. Consultar Hotel" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_PURPLE + "3. Actualizar Datos" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_PURPLE + "4. Eliminar Hotel" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_WHITE + "5. Volver al Menú Principal" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
                System.out.print(IndexView.ANSI_PURPLE + "Selecciona una opción: " + IndexView.ANSI_RESET);

                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        // Lógica para registrar hotel
                        break;
                    case 2:
                        // Lógica para consultar hotel
                        break;
                    case 3:
                        // Lógica para actualizar datos
                        break;
                    case 4:
                        // Lógica para eliminar hotel
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
}
