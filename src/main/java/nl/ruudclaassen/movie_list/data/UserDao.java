package nl.ruudclaassen.movie_list.data;

import nl.ruudclaassen.movie_list.model.Login;
import nl.ruudclaassen.movie_list.model.User;

public interface UserDao {
	User getUserByUsername(String username);
	Login getLoginByUsername(String username);
	
	User saveUser(User user);
}
