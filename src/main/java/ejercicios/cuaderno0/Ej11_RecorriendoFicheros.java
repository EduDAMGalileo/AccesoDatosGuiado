package ejercicios.cuaderno0;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.stream.Stream;
public class Ej11_RecorriendoFicheros {
	public static void main(String[] args) throws IOException {
		Path base = Path.of("datos", "curso");
		crear(base.resolve("lista.txt"));
		crear(base.resolve("1T").resolve("notas.csv"));
		crear(base.resolve("2T").resolve("notas.csv"));
		crear(base.resolve("2T").resolve("faltas.csv"));
		System.out.println("list: solo el primer nivel");
		
		//Aquí usamos Streams
		try (Stream<Path> s = Files.list(base)) {
			s.sorted().forEach(p -> System.out.println(" " + p));
		}
		System.out.println("walk: el árbol entero");
		try (Stream<Path> s = Files.walk(base)) {
			s.sorted().forEach(p -> System.out.println(" " + p));
		}
		PathMatcher csv = FileSystems.getDefault().getPathMatcher("glob:**.csv");
		//PathMatcher csv = FileSystems.getDefault().getPathMatcher("regex:.*\\.csv");
		/*
		 try (Stream<Path> stream = Files.walk(Paths.get("tu/ruta"))) {
    		List<Path> archivosCsv = stream.filter(path -> path.toString().endsWith(".csv")).collect(Collectors.toList());
			}
		 */
		
		System.out.println("walk con filtro **.csv");
		try (Stream<Path> s = Files.walk(base)) {
			s.filter(Files::isRegularFile)
			.filter(csv::matches)
			.sorted()
			.forEach(p -> System.out.println(" " + p));
		}
	}
	private static void crear(Path fichero) throws IOException {
		Files.createDirectories(fichero.getParent());
		Files.writeString(fichero, "");
	}
}
