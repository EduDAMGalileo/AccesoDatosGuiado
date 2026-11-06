package ejercicios.cuaderno0; 

import java.nio.charset.Charset; 

public class Ej15_CodificacionSistema { 

	public static void main(String[] args) { 
		System.out.println("Charset.defaultCharset(): " 
				+ Charset.defaultCharset()); 
		System.out.println("file.encoding:   " 
				+ System.getProperty("file.encoding")); 
		System.out.println("native.encoding: " 
				+ System.getProperty("native.encoding")); 
		System.out.println("stdout.encoding: " 
				+ System.getProperty("stdout.encoding")); 
		System.out.println("Prueba de consola: Peñafiel, camión, Ávila"); 
	} 
} 