package nl.ruudclaassen.movie_list.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.model.UserMediaStatus;

public interface UserMediaService {
	Map<String, Map<String,UserMediaStatus>> getSeenAndToSee(User user);
	Map<Movie, UserMediaStatus> getItemsSeen(User user);
	//List<Integer> getItemsToSee(User user);
	void addToJudged(User user, String movieId, Map<String, Boolean> judgeResults, int rating);
	
	void removeFromJudged(String username, String mediaId);
	void toggleSeen(String username, String mediaId, boolean seen);	
	void toggleOwned(String username, String mediaId, boolean seen);	
	void toggleTodo(String username, String mediaId, boolean todo);	
	
	Map<Movie, UserMediaStatus> getOwned(User user);
	Map<Movie, UserMediaStatus> getTodos(User user);
	
	Map<Movie, UserMediaStatus> enrichMovies(User user, Set<Movie> movies);
	List<Movie> getFreshMovies(User user);
	
	void addToDone(String movieId);
	void addToSeen(String movieId);
	
}

