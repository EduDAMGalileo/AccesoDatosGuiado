package basesDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import basesDatos.Configuracion.ConfiguracionInvalidaException;


//Ahora parece mucho más largo, pero es mejor crear una clase configuración para la conexión.
public class PruebaConexionConfiguracion {

    public static Connection conectar(Configuracion config) throws SQLException {
        String url     = config.obligatorio("bd.url");
        String usuario = config.obligatorio("bd.usuario");
        String clave   = config.obligatorio("bd.clave");

        return DriverManager.getConnection(url, usuario, clave);
    }

    public static void main(String[] args) {
        try {
            Configuracion config = new Configuracion();

            try (Connection cn = conectar(config);
                 Statement st = cn.createStatement();
                 ResultSet rs = st.executeQuery("SELECT count(*) FROM alumno")) {

                if (rs.next()) {
                    System.out.println("Alumnos: " + rs.getInt(1));
                }
            }

        } catch (ConfiguracionInvalidaException e) {
            System.err.println("Error de configuración: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("No ha podido consultarse: " + e.getMessage());
        }
    }
}