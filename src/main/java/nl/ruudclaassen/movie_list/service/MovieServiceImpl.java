package nl.ruudclaassen.movie_list.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.ruudclaassen.movie_list.data.MovieDao;
import nl.ruudclaassen.movie_list.model.Genre;
import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieDao movieDao;

	public List<Movie> getMovies() {
		return movieDao.getMovies();
	}

	public Movie getMovieById(String uuid) {
		return movieDao.getMovieById(uuid);
	}

	public Movie saveMovie(Movie movie) {
		movie.setUuid(UUID.randomUUID().toString());		
		return movieDao.saveMovie(movie);		
	}
	
	public Movie updateMovie(Movie movie) {				
		return movieDao.updateMovie(movie);		
	}

	public List<Movie> getMoviesPerUser(User user) {
		return movieDao.getMoviesPerUser(user);
	}

	public List<Movie> getMoviesPerGenre(Genre genre) {
		return movieDao.getMoviesPerGenre(genre);
	}

}
