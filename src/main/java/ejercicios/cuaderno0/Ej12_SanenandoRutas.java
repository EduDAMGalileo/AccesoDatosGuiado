package ejercicios.cuaderno0;
import java.nio.file.Path;
public class Ej12_SanenandoRutas {
	public static void main(String[] args) {
		Path base = Path.of("datos", "expedientes")
				.toAbsolutePath().normalize();
		System.out.println("Base: " + base);
		String[] peticiones = {
				"sara.txt",
				"2026/iker.txt",
				"../../../../../../etc/passwd",
				"../expedientes-viejos/sara.txt"
		};
		for (String nombre : peticiones) {
			Path real = base.resolve(nombre).normalize();
			boolean comoRuta = real.startsWith(base);
			boolean comoTexto = real.toString().startsWith(base.toString());
			System.out.println(nombre);
			System.out.println(" normalizada: " + real);
			System.out.println(" Path.startsWith: " + comoRuta);
			System.out.println(" String.startsWith: " + comoTexto);
		}
	}
}
