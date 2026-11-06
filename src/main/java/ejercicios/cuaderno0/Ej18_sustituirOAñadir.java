package ejercicios.cuaderno0;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Ej18_sustituirOAñadir {

    public static void main(String[] args) throws IOException {
        Path log = Path.of("datos", "registro.txt");
        Files.createDirectories(log.getParent());

        Files.deleteIfExists(log);
        for (int i = 1; i <= 3; i++) {
            Files.writeString(log, "Línea " + i + "\n");
        }
        System.out.println("Sin opciones:");
        System.out.print(Files.readString(log));

        Files.deleteIfExists(log);
        for (int i = 1; i <= 3; i++) {
            Files.writeString(log, "Línea " + i + "\n",
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
        System.out.println("Con CREATE y APPEND:");
        System.out.print(Files.readString(log));

        Path nuevo = Path.of("datos", "no-esta.txt");
        Files.deleteIfExists(nuevo);
        try {
            Files.writeString(nuevo, "x", StandardOpenOption.APPEND);
        } catch (NoSuchFileException e) {
            System.out.println("Solo APPEND: NoSuchFileException "
                    + e.getMessage());
        }
    }
}
