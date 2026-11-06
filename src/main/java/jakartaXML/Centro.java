package jakartaXML;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

// Coincide con <centro ...>
@XmlRootElement(name = "centro")
@XmlAccessorType(XmlAccessType.FIELD)
public class Centro {

    @XmlAttribute(name = "curso")
    private String curso;

    @XmlElement(name = "alumno")
    private List<Alumno> alumnos = new ArrayList<>();

    public Centro() {
    	
    }

    public String getCurso() { return curso; }
    public List<Alumno> getAlumnos() { return alumnos; }
}