package ejercicios.cuaderno0;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class Ej08_MetodosFiles {
	public static void main(String[] args) throws IOException {
		Path f = Path.of("datos", "saludo.txt");
		Files.createDirectories(f.getParent());
		Files.writeString(f, "Hola desde Peñafiel\n");
		System.out.println("Tamaño: " + Files.size(f) + " bytes");
		System.out.println("Caracteres: " + Files.readString(f).length());
		System.out.println("¿Fichero? " + Files.isRegularFile(f));
		System.out.println("¿Directorio? " + Files.isDirectory(f));
		System.out.println("¿Se puede leer? " + Files.isReadable(f));
		System.out.println("Modificado: " + Files.getLastModifiedTime(f));
		System.out.println("Tamaño de datos: " + Files.size(f.getParent()) + " bytes");
	}
}