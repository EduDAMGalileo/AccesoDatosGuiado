package basesDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import basesDatos.Configuracion.ConfiguracionInvalidaException;

public class PruebaExcepciones {

    // Apartado 10.3: este método NO captura SQLException, la PROPAGA.
    // Su única misión es ejecutar la consulta; no sabe cómo informar al usuario.
    public static int contar(String url, String usuario, String clave, String sql) throws SQLException {
        try (Connection cn = DriverManager.getConnection(url, usuario, clave);
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            rs.next();
            return rs.getInt(1);
        }
    }

    // Capa superior: aquí sí se CAPTURA y se traduce a algo comprensible (10.1, 10.2 y 10.3)
    private static void probarCaso(String titulo, String url, String usuario, String clave, String sql) {
        System.out.println("\n=== " + titulo + " ===");
        System.out.flush();

        try {
            int total = contar(url, usuario, clave, sql);
            System.out.println("OK -> Resultado: " + total);
        } catch (SQLException e) {
            gestionarErrorBD(e);
        }

        System.err.flush();
    }

    private static void gestionarErrorBD(SQLException e) {
        String estado = e.getSQLState();
        System.err.println("estado: " + estado);
        System.err.println("diagnóstico: " + diagnosticar(estado));

        // Apartado 10.2: recorrer TODAS las excepciones/causas encadenadas
        System.err.println("cadena completa:");
        int i = 1;
        for (Throwable t : e) {
            String codigo = (t instanceof SQLException se && se.getSQLState() != null)
                    ? " [SQLState=" + se.getSQLState() + "]"
                    : "";
            System.err.println("  " + (i++) + ") " + t.getClass().getSimpleName() + codigo + ": " + t.getMessage());
        }
    }

    // Apartado 10.1: clasificar por SQLState, nunca comparando mensajes de texto
    private static String diagnosticar(String sqlState) {
        if (sqlState == null) {
            return "Error sin código SQLState (fallo interno del conector).";
        }
        if (sqlState.startsWith("08")) {
            return "Problema de conexión: servidor caído, puerto erróneo o red inaccesible.";
        }
        if (sqlState.startsWith("28")) {
            return "Autenticación rechazada: usuario o contraseña incorrectos.";
        }
        if (sqlState.equals("42501")) {
            return "Privilegios insuficientes: el usuario no tiene permiso para hacer esto.";
        }
        if (sqlState.equals("42P01")) {
            return "La tabla consultada no existe.";
        }
        if (sqlState.startsWith("23")) {
            return "Violación de restricción de integridad (clave duplicada, FK inexistente, NOT NULL...).";
        }
        return "Otro error SQL (" + sqlState + ").";
    }

    public static void main(String[] args) {
        Configuracion config;
        try {
            config = new Configuracion();
        } catch (ConfiguracionInvalidaException e) {
            System.err.println("Error de configuración: " + e.getMessage());
            return;
        }

        String url     = config.obligatorio("bd.url");
        String usuario = config.obligatorio("bd.usuario");
        String clave   = config.obligatorio("bd.clave");

        // 1. Todo bien
        probarCaso("1. Consulta correcta",
                url, usuario, clave, "SELECT count(*) FROM alumno");

        // 2. Tabla inexistente -> 42P01
        probarCaso("2. Tabla que no existe (42P01)",
                url, usuario, clave, "SELECT count(*) FROM tabla_que_no_existe");

        // 3. Sin privilegios -> 42501 (pg_authid solo puede leerla un superusuario)
        probarCaso("3. Usuario sin privilegios (42501)",
                url, usuario, clave, "SELECT count(*) FROM pg_authid");

        // 4. Contraseña falsa -> 28P01 (familia 28...)
        probarCaso("4. Contraseña incorrecta (28...)",
                url, usuario, "clave_equivocada", "SELECT count(*) FROM alumno");

        // 5. Puerto donde no hay nada escuchando -> 08001 (familia 08...) + ConnectException encadenada
        probarCaso("5. Servidor o puerto incorrecto (08...)",
                "jdbc:postgresql://localhost:59999/centrofp", usuario, clave, "SELECT count(*) FROM alumno");
    }
}