package org.example.view;

import org.example.controller.HuespedController;
import org.example.model.Huesped;
import org.example.model.ScannerSingleton;

import java.util.ArrayList;
import java.util.Scanner;


public class HuespedView {


    private Scanner scanner = ScannerSingleton.getInstance();
    private HuespedController huespedController;

    public HuespedView() {
        this.huespedController = new HuespedController();
    }

    public void menuHuesped() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean continuar = true;

        while (continuar) {
            IndexView.limpiarConsola();

            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_PURPLE + "      GESTIONANDO HUÉSPEDES" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "1. Registrar Huésped" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "2. Consultar Huésped" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "3. Actualizar Huésped" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "4. Eliminar Huésped" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_WHITE + "0. Volver al Menú Principal" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.print(IndexView.ANSI_PURPLE + "Selecciona una opción: " + IndexView.ANSI_RESET);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    this.agregarHuesped();
                    break;
                case 2:
                    this.obtenerHuespedes();
                    break;
                case 3:
                    this.modificarHuesped();
                    break;
                case 4:
                    this.eliminarHuesped();
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


    public void agregarHuesped() {

        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            IndexView indexView = new IndexView();
            indexView.menuPrincipal();
        } else {
            System.out.println("Ingrese los datos del huésped:");
            System.out.println("Nombre:");
            String nombre = scanner.nextLine();
            System.out.println("Primer apellido:");
            String primerApellido = scanner.nextLine();
            System.out.println("Segundo apellido:");
            String segundoApellido = scanner.nextLine();
            System.out.println("Tipo de documento (ID):");
            Integer idTipoDocumento = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Teléfono:");
            String telefono = scanner.nextLine();

            Huesped huesped = new Huesped(idTipoDocumento, nombre, primerApellido, segundoApellido, telefono);
            boolean huespedAgregar = this.huespedController.agregarHuesped(huesped);
            if (huespedAgregar) {
                System.out.println("Huésped agregado correctamente.");
            } else {
                System.out.println("Error al agregar el huésped.");
            }
        }
    }

    public void modificarHuesped() {

        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            IndexView indexView = new IndexView();
            indexView.menuPrincipal();
        }else {
            System.out.println("Ingrese el ID del huésped a modificar:");
            int idHuesped = scanner.nextInt();
            scanner.nextLine();

            Huesped huespedActual = huespedController.obtenerHuesped(idHuesped);
            if (huespedActual == null) {
                System.out.println("El huésped con el ID " + idHuesped + " no existe.");
                return;
            }

            System.out.println("Datos actuales del huésped:");
            ArrayList<Huesped> huespedTemporal = new ArrayList<>();
            huespedTemporal.add(huespedActual);
            CrearTabla.mostrarTabla(huespedTemporal);

            System.out.println("Ingrese el nuevo nombre (dejar vacío para omitir):");
            String nuevoNombre = scanner.nextLine();
            if (nuevoNombre.isEmpty()) {
                nuevoNombre = huespedActual.getNombre();
            }

            System.out.println("Ingrese el nuevo primer apellido (dejar vacío para omitir):");
            String nuevoPrimerApellido = scanner.nextLine();
            if (nuevoPrimerApellido.isEmpty()) {
                nuevoPrimerApellido = huespedActual.getPrimerApellido();
            }

            System.out.println("Ingrese el nuevo segundo apellido (dejar vacío para omitir):");
            String nuevoSegundoApellido = scanner.nextLine();
            if (nuevoSegundoApellido.isEmpty()) {
                nuevoSegundoApellido = huespedActual.getSegundoApellido();
            }

            System.out.println("Ingrese el nuevo ID del tipo de documento (0 para omitir):");
            Integer nuevoIdTipoDocumento = scanner.nextInt();
            if (nuevoIdTipoDocumento == 0) {
                nuevoIdTipoDocumento = huespedActual.getIdTipoDocumento();
            }

            System.out.println("Ingrese el nuevo teléfono (dejar vacío para omitir):");
            String nuevoTelefono = scanner.nextLine();
            if (nuevoTelefono.isEmpty()) {
                nuevoTelefono = huespedActual.getTelefono();
            }

            scanner.nextLine();
            Huesped huespedModificado = new Huesped(nuevoIdTipoDocumento, nuevoNombre, nuevoPrimerApellido, nuevoSegundoApellido, nuevoTelefono);
            if (huespedController.modificarHuesped(idHuesped, huespedModificado)) {
                System.out.println("Huésped modificado con éxito.");
            } else {
                System.out.println("Error al modificar el huésped.");
            }
        }
    }

    public void eliminarHuesped() {
        System.out.println("Ingrese el ID del huésped a eliminar:");
        int idHuesped = scanner.nextInt();
        scanner.nextLine();

        System.out.println("¿Está seguro de que desea eliminar el huesped con ID " + idHuesped + "? (s/n)");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            if (huespedController.eliminarHuesped(idHuesped)) {
                System.out.println("Huesped eliminado con éxito.");
            } else {
                System.out.println("Error al eliminar el huésped. Puede que no exista o ya haya sido eliminado.");
            }
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    public void obtenerHuespedes() {
        ArrayList<Huesped> huespedesObtenidos = huespedController.obtenerHuespedes();

        System.out.println("Huéspedes registrados:");
        CrearTabla.mostrarTabla(huespedesObtenidos);

        System.out.println("\nPresione Enter para volver al menú anterior...");
        scanner.nextLine();
    }
}
