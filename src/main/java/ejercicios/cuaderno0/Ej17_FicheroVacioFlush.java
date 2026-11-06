package ejercicios.cuaderno0;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ej17_FicheroVacioFlush {

    public static void main(String[] args) throws IOException {
        Path f = Path.of("datos", "sin-cerrar.txt");
        Files.createDirectories(f.getParent());

        BufferedWriter w = Files.newBufferedWriter(f);
        w.write("Sara Villalba Marco");
        w.newLine();
        System.out.println("Escrito, sin cerrar: " + Files.size(f)
                + " bytes en disco");
        w.flush();
        System.out.println("Tras flush():        " + Files.size(f)
                + " bytes en disco");
        w.close();

        Path g = Path.of("datos", "bien-cerrado.txt");
        try (BufferedWriter w2 = Files.newBufferedWriter(g)) {
            w2.write("Iker Bengoetxea Ruiz");
            w2.newLine();
        }
        System.out.println("Con try-with-resources: " + Files.size(g)
                + " bytes en disco");
    }
}
