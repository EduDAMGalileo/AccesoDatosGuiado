package jakartaXML;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

//Como XML solo maneja texto y LocalDate es un objeto, necesitamos decirle cómo convertir ese texto ("2004-03-11") a LocalDate
public class AdaptadorFecha extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) {
        return (v != null && !v.isBlank()) ? LocalDate.parse(v) : null;
    }

    @Override
    public String marshal(LocalDate v) {
        return (v != null) ? v.toString() : null;
    }
}