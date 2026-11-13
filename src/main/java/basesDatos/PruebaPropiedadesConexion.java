package basesDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import basesDatos.Configuracion.ConfiguracionInvalidaException;

public class PruebaPropiedadesConexion {

    // 12.1. Confirmación automática (autoCommit = true)
    private static void probarAutoCommit(Configuracion config) {
        System.out.println("=== 12.1. Confirmación automática ===");

        try (Connection cn = PruebaConexionConfiguracion.conectar(config);
             Statement st = cn.createStatement()) {

            System.out.println("autoCommit por omisión: " + cn.getAutoCommit());

            // Primera sentencia: se confirma sola al instante
            st.execute("CREATE TEMP TABLE prueba_ac (id int)");
            System.out.println("1ª sentencia ejecutada (tabla temporal creada).");

            // Segunda sentencia: falla a propósito
            try {
                st.executeUpdate("INSERT INTO tabla_inexistente VALUES (1)");
            } catch (SQLException e) {
                System.out.println("2ª sentencia falló (" + e.getSQLState() + "), pero...");
            }

            // La 1ª sentencia NO se deshizo: la tabla temporal sigue ahí
            try (ResultSet rs = st.executeQuery("SELECT count(*) FROM prueba_ac")) {
                if (rs.next()) {
                    System.out.println("La tabla temporal sigue existiendo (filas: " + rs.getInt(1) + ").");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en 12.1 [" + e.getSQLState() + "]: " + e.getMessage());
        }
    }

    // 12.2. Conexión de solo lectura (setReadOnly)
    private static void probarSoloLectura(Configuracion config) {
        System.out.println("\n=== 12.2. Solo lectura ===");

        try (Connection cn = PruebaConexionConfiguracion.conectar(config)) {
            cn.setReadOnly(true);
            System.out.println("isReadOnly(): " + cn.isReadOnly());

            // Consultar funciona sin problema
            try (Statement st = cn.createStatement();
                 ResultSet rs = st.executeQuery("SELECT count(*) FROM alumno")) {
                if (rs.next()) {
                    System.out.println("Consulta en modo solo lectura OK -> Alumnos: " + rs.getInt(1));
                }
            }

            // Matiz real del driver de PostgreSQL (pgjdbc):
            // Con autoCommit=true, setReadOnly(true) es solo una sugerencia salvo que la URL
            // lleve ?readOnlyMode=always. Al pasar a transacción (autoCommit=false),
            // PostgreSQL sí envía BEGIN READ ONLY y bloquea escrituras con SQLState 25006.
            cn.setAutoCommit(false);
            try (Statement st = cn.createStatement()) {
                st.execute("CREATE TEMP TABLE no_deberia (id int)");
            } catch (SQLException e) {
                System.out.println("Escritura bloqueada en transacción read-only -> SQLState: "
                        + e.getSQLState() + " (" + e.getMessage() + ")");
                cn.rollback();
            }

        } catch (SQLException e) {
            System.out.println("Error en 12.2 [" + e.getSQLState() + "]: " + e.getMessage());
        }
    }

    // 12.3. Tiempo de espera de la consulta (setQueryTimeout)
    private static void probarQueryTimeout(Configuracion config) {
        System.out.println("\n=== 12.3. Tiempo de espera de consulta ===");

        try (Connection cn = PruebaConexionConfiguracion.conectar(config);
             Statement st = cn.createStatement()) {

            st.setQueryTimeout(2); // Límite de 2 segundos para la sentencia
            System.out.println("Lanzando consulta lenta (pg_sleep de 5s) con timeout de 2s...");

            long inicio = System.currentTimeMillis();
            st.executeQuery("SELECT pg_sleep(5)");
            System.out.println("Terminó sin timeout.");

        } catch (SQLException e) {
            // PostgreSQL aborta la sentencia y devuelve SQLState 57014 (query_canceled)
            System.out.println("Consulta abortada por timeout -> SQLState: "
                    + e.getSQLState() + " | Mensaje: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Configuracion config;
        try {
            config = new Configuracion();
        } catch (ConfiguracionInvalidaException e) {
            System.err.println("Error de configuración: " + e.getMessage());
            return;
        }

        probarAutoCommit(config);
        probarSoloLectura(config);
        probarQueryTimeout(config);
    }
}