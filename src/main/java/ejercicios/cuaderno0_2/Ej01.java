package ejercicios.cuaderno0_2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ej01 {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/cine";
        Connection cn = DriverManager.getConnection(url, "app_cine", "admin");
        DatabaseMetaData md = cn.getMetaData();
        System.out.println("Conectado a " + md.getDatabaseProductName()
                + " " + md.getDatabaseProductVersion());
        System.out.println("Controlador: " + md.getDriverName()
                + " " + md.getDriverVersion());
        cn.close();
    }
}
