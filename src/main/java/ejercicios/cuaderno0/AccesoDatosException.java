package ejercicios.cuaderno0;

/** Fallo de acceso a datos, con un mensaje que dice qué y dónde. */
public class AccesoDatosException extends RuntimeException {

    public AccesoDatosException(String mensaje) {
        super(mensaje);
    }

    public AccesoDatosException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
