package ejercicios.cuaderno0;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class Ej04_PAth {

	public static void main(String[] args) throws IOException {
		Path ruta = Path.of("no-existe.txt");
		System.out.println("Ruta creada: " + ruta);
		System.out.println("¿Existe? " + Files.exists(ruta));

		try {
			String contenido = Files.readString(ruta);
			System.out.println(contenido);
		} catch (NoSuchFileException e) {
			System.out.println("Al leer: NoSuchFileException");
			System.out.println("  getMessage(): " + e.getMessage());
		}
	}
}
