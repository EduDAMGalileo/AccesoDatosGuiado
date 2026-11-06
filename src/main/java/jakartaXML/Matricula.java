package jakartaXML;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class Matricula {

    @XmlAttribute(name = "modulo")
    private String modulo;

    @XmlValue // El texto suelto que va dentro: "Acceso a datos"
    private String nombre;

    public Matricula() {
    	
    }

    @Override
    public String toString() {
        return modulo + " - " + nombre;
    }
}