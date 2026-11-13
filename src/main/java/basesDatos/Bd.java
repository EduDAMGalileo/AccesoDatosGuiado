package basesDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Bd {

    private static final Configuracion CONFIG = new Configuracion();

    private static final String URL     = CONFIG.obligatorio("bd.url");
    private static final String USUARIO = CONFIG.obligatorio("bd.usuario");
    private static final String CLAVE   = CONFIG.obligatorio("bd.clave");

    private Bd() {
        // Constructor privado: evita que nadie haga "new Bd()"
    }

    public static Connection conexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}