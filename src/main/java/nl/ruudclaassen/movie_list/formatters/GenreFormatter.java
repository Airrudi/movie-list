package nl.ruudclaassen.movie_list.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import nl.ruudclaassen.movie_list.model.Genre;
import nl.ruudclaassen.movie_list.service.GenreService;

@Component
public class GenreFormatter implements Formatter<Genre> {
	
	@Autowired
	private GenreService genreService;

	public String print(Genre genre, Locale locale) { 
		return genre.getUuid();
	}

	public Genre parse(String uuid, Locale locale) throws ParseException {
		return genreService.getGenreByUuid(uuid); 
	}   
}