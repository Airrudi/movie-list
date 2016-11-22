package nl.ruudclaassen.movie_list.service;

import java.util.List;

import nl.ruudclaassen.movie_list.model.Genre;
import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;

public interface MovieService {
	List<Movie> getMovies();
	
	Movie saveMovie(Movie movie);

	Movie getMovieById(String uuid);

	List<Movie> getMoviesPerUser(User user);

	List<Movie> getMoviesPerGenre(Genre genre);
}
