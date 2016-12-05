package nl.ruudclaassen.movie_list.service;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.ruudclaassen.movie_list.data.MovieDao;
import nl.ruudclaassen.movie_list.data.UserMediaStatusDao;
import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.model.UserMediaStatus;

@Service
public class UserMediaServiceImpl implements UserMediaService {

	@Autowired
	MovieService movieService;
	
	@Autowired
	MovieDao movieDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserMediaStatusDao userMediaStatusDao;
	
	
	@Override
	public Map<String, Map<String,UserMediaStatus>> getSeenAndToSee(User user) {
		Map<String, Map<String, UserMediaStatus>> seenAndToSeeMovies = new HashMap<>();
		Map<String, UserMediaStatus> itemsSeen = new HashMap<>();
		Map<String, UserMediaStatus> itemsToSee = new HashMap<>();
		
		Map<String, UserMediaStatus> judgedMovies = user.getJudgedMovies();		
		
		for(Entry<String, UserMediaStatus> e : judgedMovies.entrySet()){
			if(e.getValue().isSeen()){
				itemsSeen.put(e.getKey(), e.getValue());
			} else {
				itemsToSee.put(e.getKey(), e.getValue());
			}
		}
		
		seenAndToSeeMovies.put("seen", itemsSeen);
		seenAndToSeeMovies.put("toSee", itemsToSee);
		
		return seenAndToSeeMovies;
	}

	@Override
	public Map<Movie, UserMediaStatus> getTodos(User user) {
		Set<Movie> movies = user.getTodos();
		//List<Movie> movies = movieDao.getMovies(movieIds);		
		
		return this.enrichMovies(user, movies);
	}
	
	// Enrich movies with your data (seen / owned / rating)
	public Map<Movie, UserMediaStatus> enrichMovies(User user, Set<Movie> movies){
		Map<Movie, UserMediaStatus> enrichedMovies = new HashMap<>();
		Map<String, UserMediaStatus> enrichments = user.getJudgedMovies();
		
		for(Movie movie : movies){			
			enrichedMovies.put(movie, enrichments.get(movie.getId()));
		}
		
		return enrichedMovies;
	}

	@Override
	public Map<Movie, UserMediaStatus> getItemsSeen(User user) {
		Map<String, UserMediaStatus> itemsSeen = user.getJudgedMovies();
		Map<Movie, UserMediaStatus> seen = new HashMap<>();
		
		// TODO: Q: What is better, Entry<> or looping through keyset?
		for(String movieId : itemsSeen.keySet()){
			if(itemsSeen.get(movieId).isSeen()){
				seen.put(movieService.getById(movieId), itemsSeen.get(movieId));
			}
		}
		
		return seen;
	}

	@Override
	@Transactional
	public void addToJudged(User user, String mediaId, Map<String, Boolean> judgeResults, int rating) {
//		Map<Movie, UserMediaStatus> judgedMovies = user.getJudgedMovies();
//		
//		UserMediaStatus ums = new UserMediaStatus();
//		ums.setOwned(judgeResults.get("owned"));
//		ums.setSeen(judgeResults.get("seen"));
//		ums.setRating(rating);		
//		
//		judgedMovies.put(Movie, ums);		
//
//		if( judgeResults.get("todo") ){
//			Set<Movie> movies = user.getTodos();
//			todo.add(mediaId);			
//		}	 
//		
//		userService.saveUser(user);
	}

	@Override
	@Transactional
	public void removeFromJudged(String username, String mediaId){
//		User user = userService.getUserByUsername(username);		
//		
//		Map<String, UserMediaStatus> judgedMedia = user.getJudgedMovies();
//		judgedMedia.remove(mediaId);
//		Set<String> todoMedia = user.getTodos();		
//
//		if( todoMedia.contains(mediaId)){
//			todoMedia.remove(mediaId);
//		}
//		
//		userService.saveUser(user);
	}

	@Override
	@Transactional
	public void toggleSeen(String username, String movieId, boolean seen) {
		User user = userService.getUserByUsername(username);		
		
		Map<String, UserMediaStatus> judgedMedia = user.getJudgedMovies();
		UserMediaStatus usm = judgedMedia.get(movieId);
		usm.setSeen(seen);
		
		userService.saveUser(user);		
	}	
	
	@Override
	@Transactional
	public void toggleOwned(String username, String mediaId, boolean owned) {
		User user = userService.getUserByUsername(username);		
		
		Map<String, UserMediaStatus> judgedMedia = user.getJudgedMovies();
		UserMediaStatus usm = judgedMedia.get(mediaId);
		usm.setOwned(owned);
		
		userService.saveUser(user);		
	}	
	
	
	@Override
	@Transactional
	public void toggleTodo(String username, String mediaId, boolean todo) {
//		User user = userService.getUserByUsername(username);		
//		
//		// TODO: Q: What if these are not int, but String for imdb / rottentomatoes / etc?
//		Set<String> mediaTodo = user.getTodos();
//		
//		if(todo){
//			mediaTodo.add(mediaId);
//		} else {
//			mediaTodo.remove(mediaId);
//		}
//		
//		userService.saveUser(user);		
	}

	@Override
	public Map<Movie, UserMediaStatus> getOwned(User user) {
		// TODO: Switch statement for every mediaType?
		Map<String, UserMediaStatus> usm = user.getJudgedMovies();
		Map<Movie, UserMediaStatus> owned = new HashMap<>();
		
		for(Entry<String,UserMediaStatus> e : usm.entrySet()){
			if(e.getValue().isOwned()){
				Movie movie = movieService.getById(e.getKey());
				owned.put(movie, e.getValue());
			}
		}
		
		return owned;
	}

	@Override
	public void addToDone(String movieId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addToSeen(String movieId) {
		// TODO Auto-generated method stub
		
//	}@Override
//	public Map<Movie, UserMediaStatus> getMoviesPerUser(User user) {
//		return movieDao.getMoviesPerUser(user);		
	}

	@Override
	public List<Movie> getFreshMovies(User user) {
		// TODO: Q: Check which movies to collect instead of continuously querying the api?
		
		Map<String, Movie> movies = movieDao.getPopularMovies();
		Map<String, UserMediaStatus> judgedMovies = user.getJudgedMovies();
		
		for(String movieId : judgedMovies.keySet()){
			movies.remove(movieId);
		}
		
		return (List<Movie>) movies.values();
	}
	
	public List<Movie> getMoviesByYear(User user) {
		// If no settings are set, get default values (if there are any movies left with default filtering)
		int year = Year.now().getValue();			
		
		// TODO: Get best movies of current year (and year before if month < 6)
		Map<String, Movie> movies = movieDao.getMoviesByYear(year);		
		Map<String, UserMediaStatus> judgedMovies = user.getJudgedMovies();
		
		for(String movieId : judgedMovies.keySet()){
			movies.remove(movieId);
		}
		
		return (List<Movie>) movies.values();
	}	
}
