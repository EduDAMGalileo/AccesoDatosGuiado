package basesDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EjemploUsoBd {

    // Método de acceso a datos: pide conexión a Bd y propaga el error si falla
    public static int contarAlumnos() throws SQLException {
        String sql = "SELECT count(*) FROM alumno";

        try (Connection cn = Bd.conexion()) {
            cn.setReadOnly(true); // Solo consulta 

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setQueryTimeout(10); // Máximo 10 segundos

                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    return rs.getInt(1);
                }
            }
        }
    }

    // Capa superior: llama al método y gestiona el posible fallo
    public static void main(String[] args) {
        try {
            int total = contarAlumnos();
            System.out.println("Total de alumnos: " + total);

        } catch (SQLException e) {
            System.err.println("Error al consultar [SQLState=" + e.getSQLState() + "]: " + e.getMessage());
        }
    }
}