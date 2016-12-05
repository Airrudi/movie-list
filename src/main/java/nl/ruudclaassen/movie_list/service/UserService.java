package nl.ruudclaassen.movie_list.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.model.UserMediaStatus;
import nl.ruudclaassen.movie_list.web.controller.UserController.UserViewModel;

public interface UserService {
	User getUserByUsername(String username);
	User getUserByUUID(String uuid);
	void addUserToFriends(User user, User friend);	
	
	void addToDone(String movieId);
	void addToTodo(String movieId);
	
	User saveUser(User user);
	List<UserViewModel> getAllUsers();
	List<UserViewModel> getAllFriends(User user);
	
	List<User> getFriendOwners(User user, Movie movie);
	Map<Movie, UserMediaStatus> getOwnedByFriends(User user);
	Set<User> getFriendsWithSameTodo(User user, String movieId);
	Map<Movie, UserMediaStatus> getAllFriendTodos(User user);
	
	void addToWishList();
	
}
