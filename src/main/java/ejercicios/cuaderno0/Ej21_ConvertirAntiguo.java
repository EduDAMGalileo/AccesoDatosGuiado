package ejercicios.cuaderno0;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ej21_ConvertirAntiguo {

    public static void main(String[] args) {
        Path origen = Path.of("datos", "antiguo.csv");
        Path destino = Path.of("datos", "convertido.csv");
        prepararOrigen(origen);

        int lineas = 0;
        try (BufferedReader r = Files.newBufferedReader(origen,
                    StandardCharsets.ISO_8859_1);
             BufferedWriter w = Files.newBufferedWriter(destino,
                    StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = r.readLine()) != null) {
                w.write(linea);
                w.newLine();
                lineas++;
            }
        } catch (IOException e) {
            throw new AccesoDatosException("No se pudo convertir "
                    + origen.toAbsolutePath() + " en "
                    + destino.toAbsolutePath(), e);
        }

        System.out.println("Líneas copiadas: " + lineas);
        System.out.println("Origen:  " + tamano(origen) + " bytes");
        System.out.println("Destino: " + tamano(destino) + " bytes");
    }

    /** Simula el fichero que deja un programa antiguo de Windows. */
    private static void prepararOrigen(Path origen) {
        String texto = "id;nombre;municipio\r\n"
                + "1;Sara Villalba Marco;Peñafiel\r\n"
                + "2;Iker Bengoetxea Ruiz;Medina del Campo\r\n"
                + "3;Lucía Peñalver Gómez;Ávila\r\n";
        try {
            Files.createDirectories(origen.getParent());
            Files.writeString(origen, texto, StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            throw new AccesoDatosException("No se pudo preparar "
                    + origen.toAbsolutePath(), e);
        }
    }

    private static long tamano(Path p) {
        try {
            return Files.size(p);
        } catch (IOException e) {
            throw new AccesoDatosException("No se pudo medir "
                    + p.toAbsolutePath(), e);
        }
    }
}
