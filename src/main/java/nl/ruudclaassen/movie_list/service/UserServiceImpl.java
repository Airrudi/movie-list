package nl.ruudclaassen.movie_list.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.ruudclaassen.movie_list.data.UserDao;
import nl.ruudclaassen.movie_list.model.Media;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.web.controller.UserController.UserViewModel;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	public void addToDone(Media media) {
		// TODO Auto-generated method stub

	}

	public void addToTodo(Media media) {
		// TODO Auto-generated method stub

	}

	public void addToWishList() {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override	
	@Transactional
	public User saveUser(User user) {
		user.setUuid(UUID.randomUUID().toString());
		return userDao.saveUser(user);
	}

	@Override
	public List<UserViewModel> getAllUsers() {
		List<User> users = userDao.getAllUsers();
		
		return convertToViewModel(users);		
	}
	
	@Override
	public List<UserViewModel> getAllFriends(User user) {
		List<User> friends = user.getFriends(); 
		return convertToViewModel(friends);		
	}
	
	private List<UserViewModel> convertToViewModel(List<User> users){
		List<UserViewModel> viewUsers = new ArrayList<>();
		for(User user : users){
			
			Set<Media> seen = user.getJudgedMovies().entrySet().stream()
					.filter(e -> e.getValue().isSeen())
					.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()))
					.keySet();
			
			Set<Media> owned = user.getJudgedMovies().entrySet().stream()
					.filter(e -> e.getValue().isOwned())
					.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()))
					.keySet();
			
			viewUsers.add(new UserViewModel(user, seen, owned));
		}
		
		return viewUsers;
	}

	@Override
	public List<User> getFriendOwners(User user, Media media) {
		List<UserViewModel> friends = this.convertToViewModel(user.getFriends());
		List<User> friendOwners = new ArrayList<>(); 
		
		for(UserViewModel friend : friends){			
			if(friend.getOwned().contains(media)){
				friendOwners.add(user);
			}
		}
		
		return friendOwners;		
	}

	@Override
	public Set<Media> getMediaOwnedByFriends(User user) {
		List<UserViewModel> friends = this.convertToViewModel(user.getFriends());
		Set<Media> ownedMedia = new HashSet<>(); 
		
		for(UserViewModel friend : friends){
			ownedMedia.addAll(friend.getOwned());
		}		
		
		return ownedMedia;		
	}

	@Override
	public Set<User> getFriendsWithSameTodo(User user, Media media) {
		Set<User> friendsWithSameTodo = new HashSet<>();
		
		for(User friend : user.getFriends()){
			if(friend.getTodo().contains(media)){
				friendsWithSameTodo.add(friend);
			}
		}

		return friendsWithSameTodo;
	}

	@Override
	public Set<Media> getAllFriendTodos(User user) {
		Set<Media> friendTodos = new HashSet<>();
		
		for(User friend : user.getFriends()){
			friendTodos.addAll(friend.getTodo());			
		}

		return friendTodos;
	}

	@Override
	public User getUserByUUID(String uuid) {
		return userDao.getUserByUUID(uuid);		
	}

	@Override
	@Transactional
	public void addUserToFriends(User user, User friend) {
		user.getFriends().add(friend);		
		this.saveUser(user);
	}

	

	
	
	
	
	
	

}
