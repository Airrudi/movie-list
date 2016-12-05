package nl.ruudclaassen.movie_list.web.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.ruudclaassen.movie_list.general.Constants;
import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.service.MovieService;
import nl.ruudclaassen.movie_list.service.UserService;
import nl.ruudclaassen.movie_list.web.controller.UserController.UserViewModel;

@Controller
public class FriendController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MovieService movieService;
	
	@RequestMapping("{username}/friends/")
	public String showUsers(Model model, @PathVariable String username){
		User user = userService.getUserByUsername(username);
		
		// TODO: Extra function to convert to UserViewModel?)
		List<UserViewModel> viewModelUsers = userService.getAllUsers();
		List<UserViewModel> viewModelFriends = userService.getAllFriends(user);
		
		model.addAttribute("title","Friends");
		model.addAttribute("viewModelUsers",viewModelUsers);
		model.addAttribute("viewModelFriends",viewModelFriends);		
		
		return Constants.USERS;
	}
	
	@RequestMapping("{username}/friends/own/{movieId}")
	public String showOwnedByFriends(Model model, @PathVariable String movieId, @PathVariable String username){		
		User user  = userService.getUserByUsername(username);
		
		Movie movie = movieService.getById(movieId);
		List<User> friendOwners = userService.getFriendOwners(user, movie);
		
		model.addAttribute("title","Owned by friends");
		model.addAttribute("media", movie);
		//model.addAttribute("filters", filters);
		model.addAttribute("friendOwners", friendOwners);
		
		return Constants.USERS;
	}
	
	// TODO: Friends, needs to be verified 2 ways to enable friendship (not just randomly adding people)
	// TODO: Show all todo's
	@RequestMapping("{username}/friends/todo/{movieId}")
	public String showFriendsWithSameTodo(Model model, @PathVariable String movieId, @PathVariable String username){		
//		User user = userService.getUserByUsername(username);		
//		Movie movie = movieService.getById(movieId);
//		Set<User> friendWithSameTodo = userService.getFriendsWithSameTodo(user, movie);
//		
//		model.addAttribute("title","Shared todo's");
//		model.addAttribute("media", movie);
//		//model.addAttribute("filters", filters);
//		model.addAttribute("friendWithSameTodo", friendWithSameTodo);
//		
		return Constants.USERS;
	}
	
	// EDIT
	
	// TODO: Adding and removing users from friends
	@RequestMapping(value = "/friends/{userUUID}/add", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addUserToFriends(
			Model model,
			Principal principal,			
			@PathVariable String userUUID
	){
		// Remove		 
		User user = userService.getUserByUsername(principal.getName());
		User friend = userService.getUserByUUID(userUUID);
		userService.addUserToFriends(user, friend);		
		
		final HttpHeaders headers = new HttpHeaders();
	    return new ResponseEntity<Boolean>(headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/friends/{userUUID}/delete", method = RequestMethod.POST)
	public ResponseEntity<Boolean> removeUserFromFriends(
			Model model,
			Principal principal,			
			@PathVariable String userUUID
	){
		// Remove		 
		User user = userService.getUserByUsername(principal.getName());
		User friend = userService.getUserByUUID(userUUID);
		userService.addUserToFriends(user, friend);		
		
		final HttpHeaders headers = new HttpHeaders();
	    return new ResponseEntity<Boolean>(headers, HttpStatus.OK);
	}
	
}
