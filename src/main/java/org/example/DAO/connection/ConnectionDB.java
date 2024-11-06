package org.example.DAO.connection;

import java.sql.*;

public class ConnectionDB {
    //Detalles de la conexión
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gestorGlobalHotelero";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }


    public int executeUpdate(String query, Object... params) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            // Configurar los parámetros del PreparedStatement
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            // Ejecutar la consulta
            int rowsAffected = statement.executeUpdate();

            statement.close();
            connection.close();

            return rowsAffected; // Devuelve el número de filas afectadas
        } catch (SQLException ex) {
            System.err.println("Error al ejecutar la consulta: " + ex.getMessage());
            ex.printStackTrace();
            return 0; // Si hay un error, devuelve 0
        }
    }

    public ResultSet executeQuery(String query, Object... parameters) throws SQLException {
        Connection connection = getConnection(); // Obtiene la conexión
        PreparedStatement statement = connection.prepareStatement(query); // Prepara la consulta

        // Configurar los parámetros del PreparedStatement
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]); // Establece cada parámetro
        }

        // Ejecutar la consulta y obtener el ResultSet
        return statement.executeQuery(); // Devuelve el ResultSet para ser gestionado fuera
    }

    public int executeUpdateAutoincrement(String query, Object... params) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // Configurar los parámetros del PreparedStatement
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            // Ejecutar la consulta
            int rowsAffected = statement.executeUpdate();

            // Obtener el ID generado automáticamente si hubo una inserción exitosa
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Devuelve el ID generado
                    }
                }
            }
            return -1; // Indica que no se generó un ID
        } catch (SQLException ex) {
            System.err.println("Error al ejecutar la consulta con autoincremento: " + ex.getMessage());
            ex.printStackTrace();
            return -1; // Si hay un error, devuelve -1
        }
    }


}
