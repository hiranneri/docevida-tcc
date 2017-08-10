package br.com.doceVida.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="localDateConverter")
public class FacesConvertLocalDate implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		 Locale BRAZIL = new Locale("pt", "BR");
	     return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(BRAZIL));
	    }

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		LocalDate dateValue = (LocalDate) value;

        return dateValue.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
}
