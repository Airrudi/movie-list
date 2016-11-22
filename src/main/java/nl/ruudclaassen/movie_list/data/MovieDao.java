package nl.ruudclaassen.movie_list.data;

import java.util.List;

import nl.ruudclaassen.movie_list.model.Genre;
import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;

public interface MovieDao {
	List<Movie> getMovies();

	Movie getMovieById(String uuid);
	
	Movie saveMovie(Movie movie);
	Movie updateMovie(Movie movie);

	List<Movie> getMoviesPerUser(User user);

	List<Movie> getMoviesPerGenre(Genre genre);
}
