package nl.ruudclaassen.movie_list.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.ruudclaassen.movie_list.data.MovieDao;
import nl.ruudclaassen.movie_list.data.UserDao;
import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.model.UserMediaStatus;
import nl.ruudclaassen.movie_list.web.controller.UserController.UserViewModel;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	MovieDao movieDao;
	
	@Autowired
	UserMediaService userMediaService;

	public void addToDone(Movie movie) {
		// TODO Auto-generated method stub

	}

	public void addToTodo(Movie movie) {
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
			
			Set<String> seen = user.getJudgedMovies().entrySet().stream()
					.filter(e -> e.getValue().isSeen())
					.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()))
					.keySet();
			
			Set<String> owned = user.getJudgedMovies().entrySet().stream()
					.filter(e -> e.getValue().isOwned())
					.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()))
					.keySet();
			
			viewUsers.add(new UserViewModel(user, seen, owned));
		}
		
		return viewUsers;
	}

	@Override
	public List<User> getFriendOwners(User user, Movie movie) {
//		List<UserViewModel> friends = this.convertToViewModel(user.getFriends());
//		List<User> friendOwners = new ArrayList<>(); 
//		
//		for(UserViewModel friend : friends){			
//			if(friend.getOwned().contains(movieId)){
//				friendOwners.add(user);
//			}
//		}
		
		return null;		
	}

	@Override
	public Map<Movie, UserMediaStatus> getOwnedByFriends(User user) {
//		List<UserViewModel> friends = this.convertToViewModel(user.getFriends());
//		Set<Integer> owned = new HashSet<>(); 
//		
//		for(UserViewModel friend : friends){
//			owned.addAll(friend.getOwned());
//		}		
		
		return null;		
	}

	@Override
	public Set<User> getFriendsWithSameTodo(User user, String movieId) {
		Set<User> friendsWithSameTodo = new HashSet<>();
		
		for(User friend : user.getFriends()){
			if(friend.getTodos().contains(movieId)){
				friendsWithSameTodo.add(friend);
			}
		}

		return friendsWithSameTodo;
	}

	@Override
	public Map<Movie, UserMediaStatus> getAllFriendTodos(User user) {
//		Set<String> friendTodos = new HashSet<>();
//		
//		for(User friend : user.getFriends()){
//			friendTodos.addAll(friend.getTodos());			
//		}
//		
//		List<Movie> movies = movieDao.getMovies(friendTodos);
//		
//		return userMediaService.enrichMovies(user, movies);
		
		return null;
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

	@Override
	public void addToDone(String movieId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addToTodo(String movieId) {
		// TODO Auto-generated method stub
		
	}

	

	
	
	
	
	
	

}
