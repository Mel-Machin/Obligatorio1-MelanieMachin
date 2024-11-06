package org.example.model;
import java.util.Scanner;


public class ScannerSingleton {
    private static Scanner instance;

    private ScannerSingleton() {
        // Constructor privado para evitar instanciación externa
    }

    public static Scanner getInstance() {
        if (instance == null) {
            instance = new Scanner(System.in);
        }
        return instance;
    }

    public static void close() {
        if (instance != null) {
            instance.close();
            instance = null; // Permite reinicializar en caso de ser necesario
        }
    }
}
