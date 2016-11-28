package nl.ruudclaassen.movie_list.service;

import java.util.List;
import java.util.Map;

import nl.ruudclaassen.movie_list.model.Media;
import nl.ruudclaassen.movie_list.model.User;

public interface UserMediaService {
	Map<String, List<Media>> getSeenAndToSee(User user);
	List<Media> getItemsSeen(User user);
	List<Media> getItemsToSee(User user);
	void addToJudged(User user, Media media, Map<String, Boolean> judgeResults);
}
