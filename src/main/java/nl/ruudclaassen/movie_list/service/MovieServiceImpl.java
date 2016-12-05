package nl.ruudclaassen.movie_list.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.ruudclaassen.movie_list.data.MovieDao;
import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.model.UserMediaStatus;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieDao movieDao;
	
	@Autowired
	UserMediaService userMediaService;

	@Override
	public Movie getById(String movieId) {
		return movieDao.getMovieById(movieId);		
	}

	@Override
	public List<Movie> getMovies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> getMoviesPerGenre(String genreId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> getFreshMovies(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Movie, UserMediaStatus> getMoviesPerUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Movie, UserMediaStatus> getTodos(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
