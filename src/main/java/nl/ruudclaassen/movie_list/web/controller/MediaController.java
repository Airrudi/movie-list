package nl.ruudclaassen.movie_list.web.controller;


import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import nl.ruudclaassen.movie_list.general.Constants;
import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.model.UserMediaStatus;
import nl.ruudclaassen.movie_list.service.MovieService;
import nl.ruudclaassen.movie_list.service.UserMediaService;
import nl.ruudclaassen.movie_list.service.UserService;

@Controller
public class MediaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MediaController.class);	
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	UserMediaService userMediaService;	

	@Autowired
	UserService userService;
	
	
	private String[] types = {"Movies", "Series", "Books"};	
	
	// TODO: Q: How to redirect all URL to include trailing slash?
	
	@RequestMapping("/media/")
	public String showMedia(Model model) {
		
		model.addAttribute("title", "Media");
		model.addAttribute("types", types);
		
		return Constants.MEDIA;
	}	
	
	// #####################
	// ### VISIBLE PAGES ###
	// #####################
	
	@RequestMapping("/media/movies/{movieId}/")
	public String movieDetails(Model model, @PathVariable String movieId, Principal principal) {
		User user = userService.getUserByUsername(principal.getName());
		
		Movie movie = movieService.getById(movieId);
		UserMediaStatus usm = user.getJudgedMovies().get(movie); // Get seen, owned (and your rating)
		boolean todo = user.getTodos().contains(movie); // Check if movie is on your todo list
		
		model.addAttribute("username", principal.getName());
		model.addAttribute("media", movie);
		model.addAttribute("todo", todo);
		model.addAttribute("owned", usm.isOwned());
		model.addAttribute("seen", usm.isSeen());
		model.addAttribute("rating", usm.getRating());	

		return Constants.MEDIA_DETAIL;
	}	
	
	
	
	@RequestMapping(value = "/{username}/movies/{movieId}", method = RequestMethod.POST)
	public ResponseEntity<Boolean>  saveJudgedMedia(
			Principal principal,
			@PathVariable String mediaId, 
			@RequestParam("seen") boolean seen, 
			@RequestParam("owned") boolean owned, 
			@RequestParam("todo") boolean todo,
			@RequestParam("rating") int rating
	){
		
	/*	User user = userService.getUserByUsername(principal.getName());
		Movie movie = movieService.getById(mediaId);
		
		// TODO: Q: Pass variables in a JSON string?
		Map<String, Boolean> judgeResults = new HashMap<>();
		judgeResults.put("seen", seen);
		judgeResults.put("owned", owned);
		judgeResults.put("todo", todo);
		
		// TODO: Q: Create a new object to hold these values?
		
		userMediaService.addToJudged(user, movie, judgeResults, rating);
		*/
        final HttpHeaders headers = new HttpHeaders();        
        return new ResponseEntity<Boolean>(headers, HttpStatus.OK);
	}
	
	


}
