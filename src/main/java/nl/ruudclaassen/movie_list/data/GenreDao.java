package nl.ruudclaassen.movie_list.data;

import java.util.List;

import nl.ruudclaassen.movie_list.model.Genre;

public interface GenreDao {
	List<Genre> getGenres();	
	Genre getGenreByUuid(String uuid);
}