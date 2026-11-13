package basesDatos;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PruebaConexionDecente {

    public static void main(String[] args) {

        Properties props = new Properties();

        // Leer el fichero bd.properties desde la raíz del proyecto
        try (InputStream in = Files.newInputStream(Path.of("bd.properties"))) {
            props.load(in);
        } catch (IOException e) {
            System.err.println("No se pudo leer bd.properties (¿copiaste bd.properties.plantilla?): " + e.getMessage());
            return;
        }

        String url     = props.getProperty("bd.url");
        String usuario = props.getProperty("bd.usuario");
        String clave   = props.getProperty("bd.clave");

        // Conectar y lanzar la consulta
        try (Connection cn = DriverManager.getConnection(url, usuario, clave);
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT count(*) FROM alumno")) {

            if (rs.next()) {
                System.out.println("Alumnos: " + rs.getInt(1));
            }

        } catch (SQLException e) {
            System.err.println("No ha podido consultarse: " + e.getMessage());
        }
    }
}