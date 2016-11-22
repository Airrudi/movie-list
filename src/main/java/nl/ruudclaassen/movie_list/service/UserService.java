package nl.ruudclaassen.movie_list.service;

import javax.print.attribute.standard.Media;

public interface UserService {
	void addToDone(Media media);
	void addToTodo(Media media);
		
	
	void addToWishList();
	
}
