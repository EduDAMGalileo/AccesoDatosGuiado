package ejercicios.cuaderno0;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class Ej24_LeerTiposPrimitivos {

    public static void main(String[] args) throws IOException {
        Path f = Path.of("datos", "tipos.bin");
        Files.createDirectories(f.getParent());

        try (DataOutputStream out = new DataOutputStream( new BufferedOutputStream(Files.newOutputStream(f)))) {
            out.writeInt(1);                                   // id
            out.writeLong(LocalDate.of(2004, 3, 11).toEpochDay()); // fecha
            out.writeDouble(7.5);                              // nota
            out.writeUTF("Peña");                              // apellido
        }
        System.out.println("Tamaño: " + Files.size(f) + " bytes");

        try (DataInputStream in = new DataInputStream(new BufferedInputStream(Files.newInputStream(f)))) {
            int id = in.readInt();
            LocalDate fecha = LocalDate.ofEpochDay(in.readLong());
            double nota = in.readDouble();
            String apellido = in.readUTF();
            System.out.println(id + " " + fecha + " " + nota + " " + apellido);
        }
    }
}
