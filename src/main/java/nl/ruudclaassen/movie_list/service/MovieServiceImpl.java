package nl.ruudclaassen.movie_list.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	public Movie updateMovie(Movie movie) {				
		return movieDao.updateMovie(movie);		
	}

	public List<Movie> getMoviesPerUser(User user) {
		return movieDao.getMoviesPerUser(user);
	}

	public List<Movie> getMoviesPerGenre(Genre genre) {
		return movieDao.getMoviesPerGenre(genre);
	}

	// TODO: Q: Why @Override does not work?
	public Movie saveMovie(Movie movie, MultipartFile image) {
		movie.setUuid(UUID.randomUUID().toString());	
		
		try {
			movie.setImage(image.getBytes());
		} catch (IOException ioe) {
			System.err.println("unable to get byte array from uploaded file");
		}		
		
		return movieDao.saveMovie(movie);
	}

}
