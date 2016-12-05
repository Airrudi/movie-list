package nl.ruudclaassen.movie_list.data;

import java.util.List;
import java.util.Map;
import java.util.Set;

import nl.ruudclaassen.movie_list.model.Movie;

public interface MovieDao {
	Movie getMovieById(String movieId);	 
	List<Movie> getMovies(Set<String> movies);
	
	Map<String, Movie> getMoviesByYear(int year);
	Map<String, Movie> getPopularMovies();
	
}
