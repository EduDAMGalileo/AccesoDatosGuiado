package basesDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebaConexion {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/centrofp";

        //Es una primera prueba, pero esto NUNCA, NUNCA, NUNCA se hace así
        try (Connection cn = DriverManager.getConnection(url, "app_centro", "admin");
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT count(*) FROM alumno")) {

            rs.next();
            System.out.println("Alumnos: " + rs.getInt(1));

        } catch (SQLException e) {
            System.err.println("No ha podido consultarse: " + e.getMessage());
        }
    }
}
