package nl.ruudclaassen.movie_list.data;

import nl.ruudclaassen.movie_list.model.Login;

public interface LoginDao {
	Login findByUsername(String username);
	Login saveLogin(Login login);
}
