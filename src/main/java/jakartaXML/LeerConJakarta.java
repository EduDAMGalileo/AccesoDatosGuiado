package jakartaXML;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class LeerConJakarta {

    public static void main(String[] args) throws Exception {

        File archivo = new File("centros.xml");

        // Inicializamos el contexto indicando la clase raíz
        JAXBContext contexto = JAXBContext.newInstance(Centro.class);

        // Creamos el deserializador (Unmarshaller)
        Unmarshaller unmarshaller = contexto.createUnmarshaller();

        // Leemos el archivo y obtenemos todo el objeto Centro completo
        Centro centro = (Centro) unmarshaller.unmarshal(archivo);

        // Mostramos los datos leídos
        System.out.println("Curso: " + centro.getCurso());
        System.out.println("--------------------------------------------------");
        for (Alumno a : centro.getAlumnos()) {
            System.out.println(a);
        }
    }
}