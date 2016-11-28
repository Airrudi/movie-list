package nl.ruudclaassen.movie_list.data;

import java.util.List;

import nl.ruudclaassen.movie_list.model.Media;
import nl.ruudclaassen.movie_list.model.User;

public interface MediaDao {
	byte[] getImageByMediaUUID(String uuid);
	Media getByUUID(String uuid);
	
	List<Media> getItemsSeen(User user);
	List<Media> getItemsToSee(User user);
	
	void addToJudged(Media media);
	void deleteMedia(String uuid);	
}
