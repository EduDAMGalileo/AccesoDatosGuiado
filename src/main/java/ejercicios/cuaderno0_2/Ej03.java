package ejercicios.cuaderno0_2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ej03 {

	static void intenta(String caso, String url, String usuario, String clave) {
		try (Connection cn = DriverManager.getConnection(url, usuario, clave)) {
			System.out.printf("%-18s conectado%n", caso);
		} catch (SQLException e) {
			// Solo la primera línea del mensaje: el resto es detalle
			String msg = e.getMessage().split("\n")[0];
			System.out.printf("%-18s [%s] %s%n", caso, e.getSQLState(), msg);
		}
	}

	public static void main(String[] args) {
		String bien = "jdbc:postgresql://localhost:5432/cine";
		intenta("Todo correcto",   bien, "app_cine", "admin");
		intenta("Puerto erróneo",  "jdbc:postgresql://localhost:5433/cine",
				"app_cine", "admin");
		intenta("Base inexistente", "jdbc:postgresql://localhost:5432/cines",
				"app_cine", "admin");
		intenta("Clave errónea",   bien, "app_cine", "otra-clave");
		intenta("Usuario inventado", bien, "app_cinema", "clave-app-cine");
	}
}
