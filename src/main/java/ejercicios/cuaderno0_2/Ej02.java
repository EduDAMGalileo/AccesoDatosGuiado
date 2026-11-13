package ejercicios.cuaderno0_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ej02 {
    public static void main(String[] args) {
        System.out.println("Controladores registrados:");
        //Aquí se usa una Lambda
        DriverManager.drivers().forEach(d -> System.out.println("  " + d.getClass().getName()));

        String url = "jdbc:postgresql://localhost:5432/cine";
        try (Connection cn = DriverManager.getConnection(url, "app_cine", "admin")) {
            System.out.println("Conectado");
        } catch (SQLException e) {
            System.out.println("[" + e.getSQLState() + "] " + e.getMessage());
        }
    }
}
