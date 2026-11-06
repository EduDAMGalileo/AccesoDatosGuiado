package ejercicios.cuaderno0;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ej22_LeerParaBuscar {

    public static void main(String[] args) throws IOException {
        Path f = Path.of("datos", "mil-alumnos.csv");
        Files.createDirectories(f.getParent());
        try (BufferedWriter w = Files.newBufferedWriter(f, StandardCharsets.UTF_8)) {
            for (int id = 1; id <= 1000; id++) {
                w.write(id + "; Alumno número " + id);
                w.newLine();
            }
        }

        buscar(f, 3);
        buscar(f, 750);
        buscar(f, 2000);
    }

    static void buscar(Path f, int buscado) throws IOException {
        int leidas = 0;
        String encontrada = null;
        try (BufferedReader r = Files.newBufferedReader(f, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = r.readLine()) != null) {
                leidas++;
                int id = Integer.parseInt(linea.split(";")[0]);
                if (id == buscado) {
                    encontrada = linea;
                    break;
                }
            }
        }
        System.out.printf("id %4d: %-22s líneas leídas: %d%n", buscado, encontrada == null ? "(no está)" : encontrada, leidas);
    }
}
