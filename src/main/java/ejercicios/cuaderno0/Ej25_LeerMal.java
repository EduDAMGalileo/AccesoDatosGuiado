package ejercicios.cuaderno0;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class Ej25_LeerMal {

    public static void main(String[] args) throws IOException {
    	// el del ejercicio 24
    	Path f = Path.of("datos", "tipos.bin");        

        try (DataInputStream in = new DataInputStream(new BufferedInputStream(Files.newInputStream(f)))) {
            // Mismo fichero, orden equivocado: primero la nota
            double nota = in.readDouble();
            int id = in.readInt();
            long fecha = in.readLong();
            //Este se desborda
            //LocalDate fechaDate = LocalDate.ofEpochDay(fecha);
            System.out.println("nota  = " + nota);
            System.out.println("id    = " + id);
            System.out.println("fecha = " + fecha);
            System.out.println("Quedan " + in.available() + " bytes");
            
            //System.out.println("La fecha en LocalDate " + fechaDate);
        }
    }
}
