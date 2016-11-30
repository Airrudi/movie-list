package nl.ruudclaassen.movie_list.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.ruudclaassen.movie_list.data.MediaDao;
import nl.ruudclaassen.movie_list.data.UserMediaStatusDao;
import nl.ruudclaassen.movie_list.model.Media;
import nl.ruudclaassen.movie_list.model.Media.MediaType;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.model.UserMediaStatus;

@Service
public class UserMediaServiceImpl implements UserMediaService {

	@Autowired
	MediaDao mediaDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MediaService mediaService;
	
	@Autowired
	UserMediaStatusDao userMediaStatusDao;
	
	@Override
	public Map<String, Map<Media,UserMediaStatus>> getSeenAndToSee(User user) {
		Map<String, Map<Media, UserMediaStatus>> seenAndToSeeMovies = new HashMap<>();
		Map<Media, UserMediaStatus> itemsSeen = new HashMap<>();
		Map<Media, UserMediaStatus> itemsToSee = new HashMap<>();
		
		Map<Media, UserMediaStatus> judgedMovies = user.getJudgedMovies();		
		
		for(Entry<Media, UserMediaStatus> e : judgedMovies.entrySet()){
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
	public List<Media> getItemsToSee(User user) {
		return mediaDao.getItemsToSee(user);
	}

	@Override
	public List<Media> getItemsSeen(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void addToJudged(User user, Media media, Map<String, Boolean> judgeResults, int rating) {
		
		UserMediaStatus ums = new UserMediaStatus();
		ums.setOwned(judgeResults.get("owned"));
		ums.setSeen(judgeResults.get("seen"));
		ums.setRating(rating);
		//ums = userMediaStatusDao.save(ums);	
		
		// TODO: Q: Create new method that adds a movie to the getjudgedMovies directly?
		Map<Media, UserMediaStatus> judgedMovies = user.getJudgedMovies();
		judgedMovies.put(media, ums);		

		if( judgeResults.get("todo") ){
			Set<Media> todo = user.getTodo();
			todo.add(media);			
		}	 
		
		userService.saveUser(user);
	}

	@Override
	@Transactional
	public void removeFromJudged(String username, String mediaUUID){
		User user = userService.getUserByUsername(username);
		Media media = mediaService.getByUUID(mediaUUID);
		
		Map<Media, UserMediaStatus> judgedMedia = user.getJudgedMovies();
		judgedMedia.remove(media);
		Set<Media> todoMedia = user.getTodo();		

		if(todoMedia.contains(media)){
			todoMedia.remove(media);
		}
		
		userService.saveUser(user);
	}

	@Override
	@Transactional
	public void toggleSeen(String username, String mediaUUID, boolean seen) {
		User user = userService.getUserByUsername(username);
		Media media = mediaService.getByUUID(mediaUUID);
		
		Map<Media, UserMediaStatus> judgedMedia = user.getJudgedMovies();
		UserMediaStatus usm = judgedMedia.get(media);
		usm.setSeen(seen);
		
		userService.saveUser(user);		
	}	
	
	@Override
	@Transactional
	public void toggleOwned(String username, String mediaUUID, boolean owned) {
		User user = userService.getUserByUsername(username);
		Media media = mediaService.getByUUID(mediaUUID);
		
		Map<Media, UserMediaStatus> judgedMedia = user.getJudgedMovies();
		UserMediaStatus usm = judgedMedia.get(media);
		usm.setOwned(owned);
		
		userService.saveUser(user);		
	}	
	
	
	@Override
	@Transactional
	public void toggleTodo(String username, String mediaUUID, boolean todo) {
		User user = userService.getUserByUsername(username);
		Media media = mediaService.getByUUID(mediaUUID);
		
		Set<Media> mediaTodo = user.getTodo();
		
		if(todo){
			mediaTodo.add(media);
		} else {
			mediaTodo.remove(media);
		}
		
		userService.saveUser(user);		
	}

	@Override
	public Map<Media, UserMediaStatus> getOwned(User user, MediaType mediaType) {
		// TODO: Switch statement for every mediaType?
		Map<Media, UserMediaStatus> usm = user.getJudgedMovies();
		Map<Media, UserMediaStatus> owned = new HashMap<>();
		
		for(Entry<Media,UserMediaStatus> e : usm.entrySet()){
			if(e.getValue().isOwned()){
				owned.put(e.getKey(), e.getValue());
			}
		}
		
		return owned;
	}

	@Override
	public Map<Media, UserMediaStatus> getTodo(User user, MediaType mediaType) {
		// TODO: For all other media as well (not just movies)
		
		Set<Media> todoMedia = user.getTodo();
		Map<Media, UserMediaStatus> judgedMedia = user.getJudgedMovies();
		Map<Media, UserMediaStatus> todoMap = new HashMap<>();
		
		
		for(Media media : todoMedia){
			
			if(judgedMedia.get(media) != null){
				todoMap.put(media, judgedMedia.get(media));
			}			
		}
		
		return todoMap;

	}
	
	
	
}
