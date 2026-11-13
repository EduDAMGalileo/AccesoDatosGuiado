package basesDatos;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class Configuracion {

    private static final String FICHERO_POR_DEFECTO = "bd.properties";

    private final Properties propiedades;

    public Configuracion() {
        this(FICHERO_POR_DEFECTO);
    }

    public Configuracion(String rutaFichero) {
        this.propiedades = new Properties();
        Path ruta = Path.of(rutaFichero);

        if (!Files.exists(ruta)) {
            throw new ConfiguracionInvalidaException(
                "No se encuentra el fichero '" + rutaFichero + "'. " +
                "Copia 'bd.properties.plantilla' como '" + rutaFichero + "' y pon tus datos."
            );
        }

        // newBufferedReader lee en UTF-8 (evita problemas si la clave tiene tildes o ñ)
        try (Reader reader = Files.newBufferedReader(ruta)) {
            propiedades.load(reader);
        } catch (IOException e) {
            throw new ConfiguracionInvalidaException(
                "No se pudo leer el fichero '" + rutaFichero + "'.", e
            );
        }
    }

    public String obligatorio(String clave) {
        String v = obtener(clave);
        if (v == null || v.isBlank()) {
            throw new ConfiguracionInvalidaException(
                "Falta la propiedad obligatoria '" + clave + "' en el fichero de configuración."
            );
        }
        return v;
    }

    public String opcional(String clave, String porOmision) {
        String v = obtener(clave);
        return (v == null || v.isBlank()) ? porOmision : v;
    }

    public int entero(String clave, int porOmision) {
        String v = obtener(clave);
        if (v == null || v.isBlank()) {
            return porOmision;
        }
        try {
            return Integer.parseInt(v.trim());
        } catch (NumberFormatException e) {
            throw new ConfiguracionInvalidaException(
                "La propiedad '" + clave + "' debe ser un número entero, y vale: " + v, e
            );
        }
    }

    private String obtener(String clave) {
        return propiedades.getProperty(clave);
    }

    public static class ConfiguracionInvalidaException extends RuntimeException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ConfiguracionInvalidaException(String mensaje) {
            super(mensaje);
        }

        public ConfiguracionInvalidaException(String mensaje, Throwable causa) {
            super(mensaje, causa);
        }
    }
}