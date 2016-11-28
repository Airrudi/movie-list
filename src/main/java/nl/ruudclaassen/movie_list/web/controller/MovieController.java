package nl.ruudclaassen.movie_list.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import nl.ruudclaassen.movie_list.general.Constants;
import nl.ruudclaassen.movie_list.model.Genre;
import nl.ruudclaassen.movie_list.model.Media;
import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.service.GenreService;
import nl.ruudclaassen.movie_list.service.MediaService;
import nl.ruudclaassen.movie_list.service.MovieService;
import nl.ruudclaassen.movie_list.service.UserMediaService;
import nl.ruudclaassen.movie_list.service.UserService;

@Controller
public class MovieController {

	@Autowired
	GenreService genreService;

	@Autowired
	MovieService movieService;
	
	@Autowired
	MediaService mediaService;
	
	@Autowired
	UserMediaService userMediaService;
	
	@Autowired
	UserService userService;

	@RequestMapping("/media/movies/")
	public String showMovies(Model model) {

		List<Movie> movies = movieService.getMovies();
		List<String> tableHeaders = new ArrayList<String>(Arrays.asList("Image", "Title", "Score", "Genre"));
		
		model.addAttribute("title", "Movies");
		model.addAttribute("items", movies);
		model.addAttribute("tableHeaders", tableHeaders);

		return Constants.MEDIA;
	}

	// Edit
	@RequestMapping("/media/movies/add")
	public String newMovie(Model model) {
		List<Genre> genres = genreService.getGenres();

		model.addAttribute("title", "Movies");
		model.addAttribute("genres", genres);
		model.addAttribute("Media", new Movie());

		return Constants.EDIT_MEDIA;
	}
	
	@RequestMapping("/media/movies/{mediaUUID}/edit")
	public String editMovie(Model model, @PathVariable String mediaUUID) {
		List<Genre> genres = genreService.getGenres();
		
		Movie movie = (Movie) mediaService.getByUUID(mediaUUID);

		model.addAttribute("title", "Movies");
		model.addAttribute("genres", genres);
		model.addAttribute("Media", movie);

		return Constants.EDIT_MEDIA;
	}
	
	@RequestMapping("/media/movies/{mediaUUID}/delete")
	public String deleteMovie(Model model, @PathVariable String mediaUUID) {		
		mediaService.deleteMedia(mediaUUID);
		return Constants.REDIRECT_MEDIA;
	}

	@RequestMapping(value = "/media/movies/", method = RequestMethod.POST)
	public String addMovie(Movie movie, @RequestParam MultipartFile file) {

		try {
			movieService.saveMovie(movie, file.getBytes());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Constants.REDIRECT_MOVIES;
	}

	@RequestMapping("/{username}/movies/")
	public String showOverview(Model model, @PathVariable String username) {
		User user = userService.getUserByUsername(username);
		
		Map<String, List<Media>> seenAndToSee = userMediaService.getSeenAndToSee(user); 
		
		List<Media> itemsSeen = seenAndToSee.get("seen");
		List<Media> itemsToSee = seenAndToSee.get("toSee");		
		
		model.addAttribute("itemsSeen", itemsSeen);
		model.addAttribute("itemsToSee", itemsToSee);		
		model.addAttribute("username", username);
		
		return Constants.USER_MEDIA;
	}

	// API
//	@RequestMapping(value = "/{userUUID}/movies/add/", method = RequestMethod.POST)
//	public String saveMovieToList(Model model) {
//		return Constants.REDIRECT_SWIPER;
//	}
//
//	@RequestMapping(value = "/{userUUID}/movies/{uuid}/update/", method = RequestMethod.POST)
//	public String updateMovieInList(Model model) {
//		return Constants.REDIRECT_SWIPER;
//	}
//
//	@RequestMapping(value = "/{userUUID}/movies/{uuid}/delete/", method = RequestMethod.POST)
//	public String deleteMovieFromList(Model model) {
//		return Constants.REDIRECT_SWIPER;
//	}
//
//	@RequestMapping(value = "/{userUUID}/movies/swiper/", method = RequestMethod.POST)
//	public String selectMovies(Model model) {
//		return Constants.SWIPER;
//	}
}