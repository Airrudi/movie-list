package nl.ruudclaassen.movie_list.service;

import javax.print.attribute.standard.Media;

import nl.ruudclaassen.movie_list.model.User;

public interface UserService {
	User getUserByUsername(String username);
	
	void addToDone(Media media);
	void addToTodo(Media media);
	
	User saveUser(User user);
	
	void addToWishList();
	
}
