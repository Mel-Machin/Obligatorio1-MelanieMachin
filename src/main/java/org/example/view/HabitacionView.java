package org.example.view;
import java.util.Scanner;


public class HabitacionView {

        public void menuHabitacion() {
            Scanner scanner = new Scanner(System.in);
            int opcion;
            boolean continuar = true;

            while (continuar) {
                IndexView.limpiarConsola();

                System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_PURPLE + "      GESTIÓN DE HABITACIONES" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_PURPLE + "1. Registrar Habitación" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_PURPLE + "2. Consultar Habitación" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_PURPLE + "3. Actualizar Habitación" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_PURPLE + "4. Eliminar Habitación" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_WHITE + "5. Volver al Menú Principal" + IndexView.ANSI_RESET);
                System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
                System.out.print(IndexView.ANSI_PURPLE + "Selecciona una opción: " + IndexView.ANSI_RESET);

                opcion = scanner.nextInt();
                scanner.nextLine();  // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        // Lógica para registrar habitación
                        break;
                    case 2:
                        // Lógica para consultar habitación
                        break;
                    case 3:
                        // Lógica para actualizar habitación
                        break;
                    case 4:
                        // Lógica para eliminar habitación
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
