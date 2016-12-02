package nl.ruudclaassen.movie_list.service;

import java.util.List;
import java.util.Set;

import nl.ruudclaassen.movie_list.model.Media;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.web.controller.UserController.UserViewModel;

public interface UserService {
	User getUserByUsername(String username);
	User getUserByUUID(String uuid);
	void addUserToFriends(User user, User friend);	
	
	void addToDone(Media media);
	void addToTodo(Media media);
	
	User saveUser(User user);
	List<UserViewModel> getAllUsers();
	List<UserViewModel> getAllFriends(User user);
	
	List<User> getFriendOwners(User user, Media media);
	Set<Media> getMediaOwnedByFriends(User user);
	Set<User> getFriendsWithSameTodo(User user, Media media);
	Set<Media> getAllFriendTodos(User user);
	
	void addToWishList();
	
}
