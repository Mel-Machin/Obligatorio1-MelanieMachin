package org.example.view;

import org.example.controller.TarifaController;
import org.example.model.ScannerSingleton;
import org.example.model.Tarifa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TarifaView {

    private Scanner scanner = ScannerSingleton.getInstance();
    private TarifaController tarifaController;
    private SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

    public TarifaView() {
        this.tarifaController = new TarifaController();
    }

    public void menuTarifa() {
        int opcion;
        boolean continuar = true;

        while (continuar) {
            IndexView.limpiarConsola();

            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_PURPLE + "      GESTIONANDO TARIFAS" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "1. Registrar Tarifa" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "2. Listar Tarifas" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "3. Actualizar Datos" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_PURPLE + "4. Eliminar Tarifa" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_WHITE + "0. Volver al Menú Principal" + IndexView.ANSI_RESET);
            System.out.println(IndexView.ANSI_BOLD + IndexView.ANSI_CYAN + "===============================" + IndexView.ANSI_RESET);
            System.out.print(IndexView.ANSI_PURPLE + "Selecciona una opción: " + IndexView.ANSI_RESET);


            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        this.agregarTarifa();
                        break;
                    case 2:
                        this.obtenerTarifas();
                        break;
                    case 3:
                        this.modificarTarifa();
                        break;
                    case 4:
                        this.eliminarTarifa();
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


    public void agregarTarifa() {

        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            this.menuTarifa();
        }else {

            try {
                System.out.println("Ingrese los datos de la tarifa: ");
                System.out.println("Fecha de inicio (día-mes-año):");
                String fechaInicio = scanner.next();
                Date fecha = formato.parse(fechaInicio);
                System.out.println("Precio: ");
                Double precio = scanner.nextDouble();
                System.out.println("Tipo de habitación: ");
                Integer tipoHabitacion = scanner.nextInt();

                Tarifa tarifa = new Tarifa(fecha, precio, tipoHabitacion);
                boolean tarifaAgregar = this.tarifaController.agregarTarifa(tarifa);
                if (tarifaAgregar) {
                    System.out.println("Tarifa agregada correctamente.");
                } else {
                    System.out.println("Error al agregar la tarifa.");
                }
            } catch (ParseException e) {
                System.out.println("Error en el formato de la fecha. Por favor, use el formato día-mes-año.");
            }
        }
    }

    public void modificarTarifa() {

        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            this.menuTarifa();
        }else {
            try {
                System.out.println("Ingrese el ID de la tarifa a modificar:");
                int idTarifa = scanner.nextInt();
                scanner.nextLine();

                Tarifa tarifaActual = tarifaController.obtenerTarifa(idTarifa);
                if (tarifaActual == null) {
                    System.out.println("La tarifa con el ID " + idTarifa + " no existe.");
                    return;
                }

                System.out.println("Datos actuales de la tarifa:");
                System.out.println("Fecha de inicio: " + tarifaActual.getFechaInicio());
                System.out.println("Precio: " + tarifaActual.getPrecio());
                System.out.println("Tipo de habitación: " + tarifaActual.getIdTipoHabitacion());


                System.out.println("Ingrese la nueva fecha de inicio (mes-día-año): ");
                String nuevaFechaInicio = scanner.nextLine();
                Date fecha;
                if (nuevaFechaInicio.isEmpty()) {
                    fecha = tarifaActual.getFechaInicio();
                } else {
                    fecha = formato.parse(nuevaFechaInicio);
                }


                System.out.println("Ingrese el nuevo precio (0 para omitir): ");
                Double nuevoPrecio = scanner.nextDouble();
                if (nuevoPrecio == 0) {
                    nuevoPrecio = tarifaActual.getPrecio();
                }

                System.out.println("Ingrese el nuevo ID del tipo de habitación (0 para omitir): ");
                Integer nuevoIdTipoHabitacion = scanner.nextInt();
                if (nuevoIdTipoHabitacion == 0) {
                    nuevoIdTipoHabitacion = tarifaActual.getIdTipoHabitacion();
                }
                scanner.nextLine();
                Tarifa tarifaModificada = new Tarifa(fecha, nuevoPrecio, nuevoIdTipoHabitacion);
                if (tarifaController.modificarTarifa(idTarifa, tarifaModificada)) {
                    System.out.println("Tarifa modificada con éxito.");
                } else {
                    System.out.println("Error al modificar la tarifa.");
                }
            } catch (ParseException e) {
                System.out.println("Error en el formato de la fecha. Por favor, use el formato día-mes-año.");
                e.printStackTrace();
            }
        }
    }

    public void eliminarTarifa() {

        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            this.menuTarifa();
        }else {

            System.out.println("Ingrese el ID de la tarifa a eliminar:");
            int idTarifa = scanner.nextInt();
            scanner.nextLine();

            System.out.println("¿Está seguro de que desea eliminar la tarifa con ID " + idTarifa + "? (s/n)");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                if (tarifaController.eliminarTarifa(idTarifa)) {
                    System.out.println("Tarifa eliminada con éxito.");
                } else {
                    System.out.println("Error al eliminar la tarifa. Es posible que no exista o que ya haya sido eliminada.");
                }
            } else {
                System.out.println("Eliminación cancelada.");
            }
        }
    }

    public void obtenerTarifas() {
        System.out.print("¿Desea volver al menú anterior? (s/n): ");
        String opcionVolver = scanner.next();
        if (opcionVolver.equalsIgnoreCase("s")) {
            this.menuTarifa();
        } else {
            ArrayList<Tarifa> tarifasObtenidas = tarifaController.obtenerTarifas();

            System.out.println("Tarifas registradas:");
            CrearTabla.mostrarTabla(tarifasObtenidas);

            System.out.println("\nPresione Enter para volver al menú anterior...");
            scanner.nextLine();
        }
    }
}

