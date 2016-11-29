package nl.ruudclaassen.movie_list.service;

import java.util.List;
import java.util.Map;

import nl.ruudclaassen.movie_list.model.Media;
import nl.ruudclaassen.movie_list.model.Media.MediaType;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.model.UserMediaStatus;

public interface UserMediaService {
	Map<String, Map<Media,UserMediaStatus>> getSeenAndToSee(User user);
	List<Media> getItemsSeen(User user);
	List<Media> getItemsToSee(User user);
	void addToJudged(User user, Media media, Map<String, Boolean> judgeResults);
	
	void removeFromJudged(String username, String mediaUUID);
	void toggleSeen(String username, String mediaUUID, boolean seen);	
	void toggleOwned(String username, String mediaUUID, boolean seen);	
	void toggleTodo(String username, String mediaUUID, boolean todo);	
	
	Map<Media, UserMediaStatus> getOwned(User user, MediaType mediaType);
	Map<Media, UserMediaStatus> getTodo(User user, MediaType mediaType);
	
}

