package nl.ruudclaassen.movie_list.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import nl.ruudclaassen.movie_list.model.Login;
import nl.ruudclaassen.movie_list.model.User;

public interface LoginService extends UserDetailsService{
	Login findByUsername(String username);
	Login saveLogin(User user, String password);
}
