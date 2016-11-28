package nl.ruudclaassen.movie_list.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import nl.ruudclaassen.movie_list.model.Login;

public interface LoginService extends UserDetailsService{
	Login findByUsername(String username);
}
