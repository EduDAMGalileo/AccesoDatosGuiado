package ejercicios.cuaderno0;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ej20_SaltarErroresCSV {

    record Alumno(int id, String nombre) { }
    record ErrorLinea(int numero, String contenido, String motivo) { }

    public static void main(String[] args) throws IOException {
        Path ruta = Path.of("datos", "importar.csv");
        Files.createDirectories(ruta.getParent());
        Files.writeString(ruta, """
                1;Sara Villalba Marco
                2;Iker Bengoetxea Ruiz
                tres;Lucía Peñalver Gómez
                4
                5;Marcos Prieto Sanz
                """);

        List<Alumno> alumnos = new ArrayList<>();
        List<ErrorLinea> errores = new ArrayList<>();
        int n = 0;

        try (BufferedReader lector =
                 Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                n++;
                try {
                    alumnos.add(convertir(linea));
                } catch (RuntimeException e) {
                    errores.add(new ErrorLinea(n, linea, e.toString()));
                }
            }
        }

        System.out.println("Leídos: " + alumnos.size());
        alumnos.forEach(a -> System.out.println("  " + a));
        System.out.println("Errores: " + errores.size());
        for (ErrorLinea e : errores) {
            System.out.println("  línea " + e.numero() + " ["
                    + e.contenido() + "] " + e.motivo());
        }
    }

    private static Alumno convertir(String linea) {
        String[] c = linea.split(";");
        return new Alumno(Integer.parseInt(c[0]), c[1]);
    }
}
