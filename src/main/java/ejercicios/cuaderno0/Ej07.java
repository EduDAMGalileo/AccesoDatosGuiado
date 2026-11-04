package ejercicios.cuaderno0;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
public class Ej07 {
	public static void main(String[] args) throws IOException {
		Path destino = Path.of("datos", "2026", "notas");
		try {
			Files.createDirectory(destino);
		} catch (NoSuchFileException e) {
			System.out.println("1) createDirectory falla: no existe " + e.getMessage());
		}
		Files.createDirectories(destino);
		System.out.println("2) createDirectories crea " + destino);
		Files.createDirectories(destino);
		System.out.println("3) createDirectories otra vez: sin error");
		try {
			Files.createDirectory(destino);
		} catch (FileAlreadyExistsException e) {
			System.out.println("4) createDirectory falla: ya existe " + e.getMessage());
		}
	}
}

