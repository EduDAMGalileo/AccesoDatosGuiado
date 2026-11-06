package ejercicios.cuaderno0;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ej19_ExceptionUtil {

	static String leerPobre(Path ruta) {
		try {
			return Files.readString(ruta);
		} catch (IOException e) {
			throw new AccesoDatosException("Error leyendo el fichero", e);
		}
	}

	static String leerUtil(Path ruta) {
		try {
			return Files.readString(ruta);
		} catch (IOException e) {
			throw new AccesoDatosException(
					"No se pudo leer " + ruta.toAbsolutePath(), e);
		}
	}

	public static void main(String[] args) {
		Path ruta = Path.of("config", "alumnos.csv");

		try {
			leerPobre(ruta);
		} catch (AccesoDatosException e) {
			System.out.println("Pobre: " + e.getMessage());
		}

		try {
			leerUtil(ruta);
		} catch (AccesoDatosException e) {
			System.out.println("Útil:  " + e.getMessage());
			System.out.println("Causa: " + e.getCause());
		}
	}
}
