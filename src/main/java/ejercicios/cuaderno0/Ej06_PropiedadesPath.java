package ejercicios.cuaderno0;

import java.nio.file.Path;

public class Ej06_PropiedadesPath {
	public static void main(String[] args) {
		Path base = Path.of("datos");
		Path fichero = base.resolve("2026").resolve("notas.csv");
		System.out.println("Ruta: " + fichero);
		System.out.println("Nombre: " + fichero.getFileName());
		System.out.println("Padre: " + fichero.getParent());
		System.out.println("Raíz: " + fichero.getRoot());
		System.out.println("Segmentos: " + fichero.getNameCount());
		
		Path rara = Path.of("datos/./2026/../2026/notas.csv");
		System.out.println("Sin normalizar: " + rara);
		System.out.println("Normalizada: " + rara.normalize());
		System.out.println("¿Iguales sin normalizar? " + rara.equals(fichero));
		System.out.println("¿Iguales normalizada? " + rara.normalize().equals(fichero));
	}
}
