package ejercicios.cuaderno0;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
public class Ej09 {
	public static void main(String[] args) throws IOException {
		// del ejercicio 8
		Path origen = Path.of("datos", "saludo.txt"); 
		Path copia = Path.of("datos", "saludo-copia.txt");
		Files.deleteIfExists(copia);
		Files.copy(origen, copia);
		System.out.println("Primera copia: hecha");
		try {
			Files.copy(origen, copia);
		} catch (FileAlreadyExistsException e) {
			System.out.println("Segunda copia: FileAlreadyExistsException " + e.getMessage());
		}
		Files.copy(origen, copia, StandardCopyOption.REPLACE_EXISTING);
		System.out.println("Tercera copia, con REPLACE_EXISTING: hecha");
		Path nuevo = Path.of("datos", "saludo-renombrado.txt");
		Files.move(copia, nuevo, StandardCopyOption.REPLACE_EXISTING);
		System.out.println("Tras mover: ¿existe la copia? " + Files.exists(copia) + "; ¿y el nuevo? " + Files.exists(nuevo));
	}
}