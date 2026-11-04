package ejercicios.cuaderno0;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
public class Ej10 {
	public static void main(String[] args) throws IOException {
		// la del ejercicio 7
		Path carpeta = Path.of("datos", "2026"); 
		//Escribimos en el fichero y construimos el Path a la vez
		Files.writeString(carpeta.resolve("notas").resolve("1T.csv"),"id;nota\n");
		try {
			Files.delete(carpeta);
		} catch (DirectoryNotEmptyException e) {
			System.out.println("delete: DirectoryNotEmptyException " + e.getMessage());
		}
		//Código con streams y colecciones, con Comparator. Dificil
		try (Stream<Path> todo = Files.walk(carpeta)) {
			List<Path> alReves = todo.sorted(Comparator.reverseOrder()).toList();
			for (Path p : alReves) {
				System.out.println("Borro " + p);
				Files.delete(p);
			}
		}
		System.out.println("¿Existe todavía? " + Files.exists(carpeta));
		System.out.println("deleteIfExists devuelve "
				+ Files.deleteIfExists(carpeta));
	}
}
