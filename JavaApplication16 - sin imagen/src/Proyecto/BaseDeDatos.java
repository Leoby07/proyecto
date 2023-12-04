package Proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDeDatos {
    private static final String URL = "jdbc:sqlite:reservas.db";

    // Crear la tabla si no existe
    public static void inicializarBaseDeDatos() {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS reservas (" +
                             "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                             "pelicula TEXT," +
                             "horario TEXT," +
                             "cantidad INTEGER," +
                             "tipoBoleto TEXT," +
                             "formaPago TEXT," +
                             "totalPagar REAL," +
                             "asientosOcupados TEXT)"
             )) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insertar una reserva en la base de datos
    public static void insertarReserva(String pelicula, String horario, int cantidad, String tipoBoleto,
                                       String formaPago, double totalPagar, String asientosOcupados) {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO reservas (pelicula, horario, cantidad, tipoBoleto, formaPago, totalPagar, asientosOcupados) VALUES (?, ?, ?, ?, ?, ?, ?)"
             )) {
            statement.setString(1, pelicula);
            statement.setString(2, horario);
            statement.setInt(3, cantidad);
            statement.setString(4, tipoBoleto);
            statement.setString(5, formaPago);
            statement.setDouble(6, totalPagar);
            statement.setString(7, asientosOcupados);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> obtenerAsientosOcupados(String pelicula, String horario) {
        // Lógica para obtener los asientos ocupados de la base de datos
        // Aquí se proporciona un ejemplo con datos ficticios
        List<Integer> asientosOcupados = new ArrayList<>();
        asientosOcupados.add(1);
        asientosOcupados.add(2);
        // Agrega más asientos ocupados según tu lógica de base de datos

        return asientosOcupados;
    }
}
