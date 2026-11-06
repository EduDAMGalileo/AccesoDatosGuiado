package ejercicios.cuaderno0;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ej02_ContadorFichero {

    public static void main(String[] args) throws IOException {
        Path fichero = Path.of("visitas.txt");

        int visitas = 0;
        if (Files.exists(fichero)) {
            String texto = Files.readString(fichero).trim();
            visitas = Integer.parseInt(texto);
        }

        visitas++;
        Files.writeString(fichero, String.valueOf(visitas));
        System.out.println("Visitas: " + visitas);
    }
}
