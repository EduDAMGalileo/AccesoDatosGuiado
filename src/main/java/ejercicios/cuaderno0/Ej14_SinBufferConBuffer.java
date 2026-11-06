package ejercicios.cuaderno0; 
 
import java.io.BufferedInputStream; 
import java.io.IOException; 
import java.io.InputStream; 
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.util.Random; 
 
public class Ej14_SinBufferConBuffer { 
 
    public static void main(String[] args) throws IOException { 
        Path f = Path.of("datos", "grande.bin"); 
        Files.createDirectories(f.getParent()); 
        byte[] datos = new byte[1_000_000];          // un megabyte 
        new Random(42).nextBytes(datos); 
        Files.write(f, datos); 
 
        long t0 = System.nanoTime(); 
        long suma1 = 0; 
        try (InputStream in = Files.newInputStream(f)) { 
            int b; 
            while ((b = in.read()) != -1) { 
                suma1 += b; 
            } 
        } 
        long t1 = System.nanoTime(); 
 
        long suma2 = 0; 
        try (InputStream in = 
                 new BufferedInputStream(Files.newInputStream(f))) { 
            int b; 
            while ((b = in.read()) != -1) { 
                suma2 += b; 
            } 
        } 
        long t2 = System.nanoTime(); 
 
        System.out.printf("Sin búfer: %5d ms  (suma %d)%n", 
                (t1 - t0) / 1_000_000, suma1); 
        System.out.printf("Con búfer: %5d ms  (suma %d)%n", 
                (t2 - t1) / 1_000_000, suma2); 
    } 
} 