package nl.ruudclaassen.movie_list.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.ruudclaassen.movie_list.general.Constants;

@Controller
public class MovieController {	
	
	@RequestMapping("/{userUUID}/movies/")
	public String showOverview(Model model) {
		return Constants.MEDIA;
	} 
	
	
	// API
	@RequestMapping(value = "/{userUUID}/movies/add/", method = RequestMethod.POST)
	public String saveMovieToList(Model model) {		
		return Constants.REDIRECT_SWIPER;	
	}	
	
	@RequestMapping(value = "/{userUUID}/movies/{uuid}/update/", method = RequestMethod.POST)
	public String updateMovieInList(Model model) {		
		return Constants.REDIRECT_SWIPER;	
	}	
	
	@RequestMapping(value = "/{userUUID}/movies/{uuid}/delete/", method = RequestMethod.POST)
	public String deleteMovieFromList(Model model) {		
		return Constants.REDIRECT_SWIPER;	
	}	
	
	@RequestMapping(value = "/{userUUID}/movies/swiper/", method = RequestMethod.POST)
	public String selectMovies(Model model) {
		return Constants.SWIPER;
	}
		
	
}