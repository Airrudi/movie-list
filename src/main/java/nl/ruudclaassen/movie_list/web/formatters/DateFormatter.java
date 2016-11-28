package nl.ruudclaassen.movie_list.web.formatters;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class DateFormatter implements Formatter<LocalDate> {

	@Override
	public String print(LocalDate date, Locale locale) {		
		return date.toString();
	}

	@Override
	public LocalDate parse(String dateString, Locale locale) throws ParseException {
		return LocalDate.parse(dateString);		
	}	
}