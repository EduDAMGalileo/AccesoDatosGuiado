package jakartaXML;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Alumno {

    @XmlAttribute
    private int id;

    @XmlElement
    private String nombre;

    @XmlElement
    @XmlJavaTypeAdapter(AdaptadorFecha.class)
    private LocalDate nacimiento;

    @XmlElement
    private String correo;

    // La etiqueta contenedora <matriculas>
    @XmlElementWrapper(name = "matriculas")
    
    // Cada elemento repetido <matricula>
    @XmlElement(name = "matricula")         
    private List<Matricula> matriculas = new ArrayList<>();

    public Alumno() {
    	
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + nombre + " (" + nacimiento + ") | " + correo + 
               " | Matrículas: " + matriculas;
    }
}