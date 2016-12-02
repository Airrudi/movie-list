package nl.ruudclaassen.movie_list.data;

import java.util.List;

import nl.ruudclaassen.movie_list.model.Login;
import nl.ruudclaassen.movie_list.model.User;

public interface UserDao {
	User getUserByUsername(String username);
	User getUserByUUID(String uuid);
	Login getLoginByUsername(String username);
	
	User saveUser(User user);
	List<User> getAllUsers();	
}
