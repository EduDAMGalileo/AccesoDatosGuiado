package ejercicios.cuaderno0;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
public class Ej13 {
	public static void main(String[] args) throws IOException {
		Path f = Path.of("datos", "apellido.txt");
		Files.createDirectories(f.getParent());
		Files.writeString(f, "🎲Peña🍺");
		try (InputStream entrada = Files.newInputStream(f)) {
			int b;
			int n = 0;
			while ((b = entrada.read()) != -1) {
				String como = (b < 128) ? "'" + (char) b + "'"
						: "(no es ASCII)";
				System.out.printf("byte %d: %3d 0x%02X %s%n",
						n++, b, b, como);
			}
		}
		System.out.println("Letras: " + "🎲Peña🍺".length()
				+ " Bytes: " + Files.size(f));
	}
}