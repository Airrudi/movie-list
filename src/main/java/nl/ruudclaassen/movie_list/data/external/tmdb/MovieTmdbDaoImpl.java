package nl.ruudclaassen.movie_list.data.external.tmdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import nl.ruudclaassen.movie_list.data.MovieDao;
import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.external.tmdb.MovieDb;


@Repository
public class MovieTmdbDaoImpl implements MovieDao {
	
	@Value("${tmdb.api.key}")
	private String apiKey;
	
	@Autowired
	private MovieResource movieResource;
	
	@Autowired
	private DiscoverResource discoverResource;

	@Override
	public Movie getMovieById(String movieId) {		
		// TODO: Throw exception if movieId is not parsing
		MovieDb movieDb = movieResource.getMovieById(Integer.parseInt(movieId));
		return movieConverter(movieDb);
	}
	
	@Override
	public Map<String, Movie> getMoviesByYear(int year) {
		List<MovieDb> dbMovies = discoverResource.getMoviesByYear(apiKey, "popularity.desc", year);
		Map<String, Movie> movies = movieConverter(dbMovies);
		
		return movies;		
	}
	
	@Override
	public Map<String, Movie> getPopularMovies() {
		List<MovieDb> dbMovies = discoverResource.getPopularMovies();
		Map<String, Movie> movies = movieConverter(dbMovies);
		
		return movies;		
	}	
	
	private Movie movieConverter(MovieDb movieDb){
		Movie movie = new Movie();
		movie.setId(Integer.toString(movieDb.getId()));
		movie.setTitle(movieDb.getTitle());
		movie.setImageUrl(movieDb.getPosterPath());
		movie.setRelease(movieDb.getReleaseDate());
		
		return movie;
	}
	
	private Map<String, Movie> movieConverter(List<MovieDb> movies){
		Map<String, Movie> convertedMovies = new HashMap<>();
		
		for(MovieDb movieDb : movies){
			Movie movie = new Movie();
			movie.setId(Integer.toString(movieDb.getId()));
			movie.setTitle(movieDb.getTitle());
			movie.setImageUrl(movieDb.getPosterPath());
			movie.setRelease(movieDb.getReleaseDate());
			
			convertedMovies.put(movie.getId(), movie);
		}
		
		return convertedMovies;
	}

	@Override
	public List<Movie> getMovies(Set<String> movieIds) {
		List<Movie> movies = new ArrayList<>();
		
		for(String movieId : movieIds){
			movies.add(this.getMovieById(movieId));
		}		
		
		return movies;
	}
	

//	@Override
//	public List<Movie> getMoviesPerGenre(int genreId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
