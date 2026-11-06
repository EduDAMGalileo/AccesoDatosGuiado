package ejercicios.cuaderno0;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Ej23_ModificarEsReescribir {

    public static void main(String[] args) throws IOException {
        Path f = Path.of("datos", "cinco.csv");
        Files.createDirectories(f.getParent());
        Files.writeString(f, """
                1;Sara Villalba Marco
                2;Iker Bengoetxea Ruiz
                3;Lucia Penalver Gomez
                4;Marcos Prieto Sanz
                5;Ana Ruiz Toledo
                """, StandardCharsets.UTF_8);

        // Corregir el nombre del alumno 3
        List<String> nuevas = new ArrayList<>();
        for (String linea : Files.readAllLines(f, StandardCharsets.UTF_8)) {
            if (linea.startsWith("3;")) {
                linea = "3;Lucía Peñalver Gómez";
            }
            nuevas.add(linea);
        }

        Path temporal = f.resolveSibling(f.getFileName() + ".tmp");
        Files.write(temporal, nuevas, StandardCharsets.UTF_8);
        Files.move(temporal, f, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);

        System.out.println("Líneas reescritas: " + nuevas.size() + " para cambiar una");
        System.out.print(Files.readString(f, StandardCharsets.UTF_8));
    }
}
