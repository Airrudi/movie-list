package nl.ruudclaassen.movie_list.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import nl.ruudclaassen.movie_list.general.Constants;
import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.service.MovieService;
import nl.ruudclaassen.movie_list.service.UserService;

@Controller
public class SwiperController {
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/{username}/swiper/")
	public String loadSwiper(@PathVariable String username, Model model){
		// TODO: All other media as well (not just movies)
		
		User user = userService.getUserByUsername(username);
		List<Movie> movies = movieService.getFreshMoviesByUser(user);
		
		model.addAttribute("title", "Swiper");
		model.addAttribute("user", user);
		model.addAttribute("media", movies);
		
		return Constants.SWIPER;
	}

}
