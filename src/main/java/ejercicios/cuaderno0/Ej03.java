package ejercicios.cuaderno0;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ej03 {

	record Alumno(int id, String nombre, String correo) { }

	public static void main(String[] args) throws IOException {
		Path fichero = Path.of("alumnos.txt");
		List<Alumno> alumnos = List.of(
				new Alumno(1, "Sara Villalba Marco",
						"sara.villalba@example.org"),
				new Alumno(2, "Iker Bengoetxea Ruiz", null));

		// Guardar: cada alumno, una línea con los campos separados por |
		List<String> lineas = new ArrayList<>();
		for (Alumno a : alumnos) {
			lineas.add(a.id() + "|" + a.nombre() + "|" + a.correo());
		}
		Files.write(fichero, lineas);

		System.out.println("En el fichero:");
		for (String linea : Files.readAllLines(fichero)) {
			System.out.println("  " + linea);
		}

		// Recuperar: partir cada línea y reconstruir el objeto
		System.out.println("Recuperados:");
		for (String linea : Files.readAllLines(fichero)) {
			String[] c = linea.split("\\|");
			Alumno a = new Alumno(Integer.parseInt(c[0]), c[1], c[2]);
			System.out.println("  " + a);
			System.out.println("    ¿correo nulo? " + (a.correo() == null));
		}
	}
}
