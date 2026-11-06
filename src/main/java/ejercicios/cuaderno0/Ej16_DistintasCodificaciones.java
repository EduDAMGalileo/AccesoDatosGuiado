package ejercicios.cuaderno0;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.nio.charset.MalformedInputException; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; 
import java.nio.file.Path; 
 
public class Ej16_DistintasCodificaciones { 
 
    public static void main(String[] args) throws IOException { 
        String nombre = "Lucía Peñalver"; 
        Path utf8 = Path.of("datos", "nombre-utf8.txt"); 
        Path latin1 = Path.of("datos", "nombre-latin1.txt"); 
        Files.createDirectories(utf8.getParent()); 
        Files.writeString(utf8, nombre, StandardCharsets.UTF_8); 
        Files.writeString(latin1, nombre, StandardCharsets.ISO_8859_1); 
 
        System.out.println("Caracteres: " + nombre.length()); 
        System.out.println("Bytes en UTF-8:      " + Files.size(utf8)); 
        System.out.println("Bytes en ISO-8859-1: " + Files.size(latin1)); 
 
        // 1. UTF-8 leído como ISO-8859-1: no falla, sale basura 
        System.out.println("UTF-8 leído como ISO-8859-1: " 
                + Files.readString(utf8, StandardCharsets.ISO_8859_1)); 
 
        // 2. ISO-8859-1 leído como UTF-8 con un conversor: sustituye 
        try (BufferedReader r = new BufferedReader(new InputStreamReader( 
                Files.newInputStream(latin1), StandardCharsets.UTF_8))) { 
            System.out.println("ISO-8859-1 leído como UTF-8 (lector): " 
                    + r.readLine()); 
        } 
 
        // 3. Lo mismo con el atajo: falla 
        try { 
            Files.readString(latin1, StandardCharsets.UTF_8); 
        } catch (MalformedInputException e) { 
            System.out.println("ISO-8859-1 leído como UTF-8 (atajo): " 
                    + "MalformedInputException, " + e.getMessage()); 
        } 
    } 
    }