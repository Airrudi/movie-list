package nl.ruudclaassen.movie_list.service;

import java.util.List;
import java.util.Map;

import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.model.UserMediaStatus;

public interface MovieService {
	Movie getById(String movieId);
	Map<String, Movie> getFreshMovies(User user);
	
	List<Movie> getMovies();
	List<Movie> getMoviesPerGenre(String genreId);
	
	
	Map<Movie, UserMediaStatus> getMoviesPerUser(User user);	
	Map<Movie, UserMediaStatus> getTodos(User user);
}
