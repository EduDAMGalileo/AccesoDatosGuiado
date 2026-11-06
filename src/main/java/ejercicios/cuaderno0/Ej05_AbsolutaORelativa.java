package ejercicios.cuaderno0;

import java.nio.file.Path;

public class Ej05_AbsolutaORelativa {

    public static void main(String[] args) {
        Path relativa = Path.of("datos", "alumnos.txt");

        System.out.println("Directorio de trabajo: " + System.getProperty("user.dir"));
        System.out.println("Relativa:      " + relativa);
        System.out.println("Absoluta:      " + relativa.toAbsolutePath());
        System.out.println("¿Es absoluta?  " + relativa.isAbsolute());
    }
}
