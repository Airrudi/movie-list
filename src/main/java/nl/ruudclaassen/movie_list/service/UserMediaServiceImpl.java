package nl.ruudclaassen.movie_list.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.ruudclaassen.movie_list.data.MediaDao;
import nl.ruudclaassen.movie_list.data.UserMediaStatusDao;
import nl.ruudclaassen.movie_list.model.Media;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.model.UserMediaStatus;

@Service
public class UserMediaServiceImpl implements UserMediaService {

	@Autowired
	MediaDao mediaDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserMediaStatusDao userMediaStatusDao;
	
	@Override
	public Map<String, List<Media>> getSeenAndToSee(User user) {
		List<Media> itemsSeen = new ArrayList<>();
		List<Media> itemsToSee = new ArrayList<>();
		Map<String, List<Media>> seenAndToSeeMovies = new HashMap<>();
		
		Map<Media, UserMediaStatus> judgedMovies = user.getJudgedMovies();		
		
		for(Entry<Media, UserMediaStatus> e : judgedMovies.entrySet()){
			if(e.getValue().isSeen()){
				itemsSeen.add(e.getKey());
			} else {
				itemsToSee.add(e.getKey());
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
	public void addToJudged(User user, Media media, Map<String, Boolean> judgeResults) {
		// TODO: Q: Refactor?
		UserMediaStatus ums = new UserMediaStatus();
		ums.setOwned(judgeResults.get("owned"));
		ums.setSeen(judgeResults.get("seen"));
		//ums = userMediaStatusDao.save(ums);	
		
		// TODO: Q: Create new method that adds a movie to the getjudgedMovies directly?
		Map<Media, UserMediaStatus> judgedMovies = user.getJudgedMovies();
		judgedMovies.put(media, ums);		

		if( judgeResults.get("watchList") ){
			List<Media> todo = user.getTodo();
			todo.add(media);			
		}	 
		
		userService.saveUser(user);		
		
	}
}
