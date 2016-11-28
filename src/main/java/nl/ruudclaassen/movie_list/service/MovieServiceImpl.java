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

	public Movie getMovieByUUID(String uuid) {
		return movieDao.getMovieByUUID(uuid);
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

	@Override
	public Movie saveMovie(Movie movie, byte[] image) {
		if(image.length > 0){
			movie.setImage(image);
		}
		
		if( "".equals(movie.getUuid()) ){
			movie.setUuid(UUID.randomUUID().toString());			
			return movieDao.saveMovie(movie);
			
		} else {
			Movie existingMovie = this.getMovieByUUID(movie.getUuid());
			
			if(movie.getImage() != null){
				existingMovie.setImage(movie.getImage());
			}
			existingMovie.setTitle(movie.getTitle());
			existingMovie.setGenres(movie.getGenres());
			existingMovie.setScore(movie.getScore());
			existingMovie.setDuration(movie.getDuration());
			existingMovie.setRelease(movie.getRelease());
			existingMovie.setSummary(movie.getSummary());
			
			return movieDao.saveMovie(existingMovie);
		}
	}

	@Override
	public List<Movie> getFreshMoviesByUser(User user) {
		return movieDao.getFreshMoviesByUser(user);
	}

}
