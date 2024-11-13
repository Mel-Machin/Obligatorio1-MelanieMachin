package org.example.view;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class CrearTabla {


    public static <T> void mostrarTabla(ArrayList<T> objetos) {
        if (objetos == null || objetos.isEmpty()) {
            System.out.println("No hay registros.");
            return;
        }

        Class<?> clase = objetos.get(0).getClass();
        Field[] campos = clase.getDeclaredFields();

        // Obtener el ancho máximo para cada columna
        int[] anchos = new int[campos.length];
        for (int i = 0; i < campos.length; i++) {
            campos[i].setAccessible(true);
            anchos[i] = campos[i].getName().length();
        }

        // Ajustar ancho de columna según el contenido más largo
        for (T objeto : objetos) {
            for (int i = 0; i < campos.length; i++) {
                try {
                    Object valor = campos[i].get(objeto);
                    String texto = (valor != null) ? valor.toString() : " ";
                    if (texto.length() > anchos[i]) {
                        anchos[i] = texto.length();
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // Imprimir encabezado
        for (int i = 0; i < campos.length; i++) {
            System.out.print(padRight(campos[i].getName(), anchos[i]) + " | ");
        }
        System.out.println();

        // Separador
        for (int ancho : anchos) {
            System.out.print("-".repeat(ancho + 2) + "+");
        }
        System.out.println();

        // Imprimir filas (datos)
        for (T objeto : objetos) {
            for (int i = 0; i < campos.length; i++) {
                try {
                    Object valor = campos[i].get(objeto);
                    String texto = (valor != null) ? valor.toString() : " ";
                    System.out.print(padRight(texto, anchos[i]) + " | ");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }

    // Metodo para rellenar la derecha
    private static String padRight(String texto, int longitud) {
        return String.format("%-" + longitud + "s", texto);
    }

}
