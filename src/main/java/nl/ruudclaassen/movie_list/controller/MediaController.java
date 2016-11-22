package nl.ruudclaassen.movie_list.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.ruudclaassen.movie_list.general.Constants;
import nl.ruudclaassen.movie_list.model.Book;
import nl.ruudclaassen.movie_list.model.Genre;
import nl.ruudclaassen.movie_list.model.Media;
import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.Series;
import nl.ruudclaassen.movie_list.service.GenreService;
import nl.ruudclaassen.movie_list.service.MovieService;

@Controller
public class MediaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MediaController.class);
	
	@Autowired
	GenreService genreService;
	
	@Autowired
	MovieService movieService;
	
	private String[] types = {"Movies", "Series", "Books"};	
	
	// TODO: Q: How to redirect all URL to include trailing slash?
	
	@RequestMapping("/media/")
	public String showMedia(Model model) {
		
		model.addAttribute("title", "Media");
		model.addAttribute("types", types);
		
		return Constants.MEDIA;
	}	
	
	@RequestMapping("/media/movies/")
	public String showMovies(Model model) {
		LOGGER.info("bllaaaaaaaaaaaaaaaaaaaaaaaaa");
		LOGGER.debug("debugggggggggggggggggggggggg");
		System.out.print(types);
		model.addAttribute("title", "Movies");
		model.addAttribute("types", types);
		
		return Constants.MEDIA;
	}
	
	@RequestMapping(value = "/media/movies/", method = RequestMethod.POST)
	public String addMovie(Movie movie, Model model) {
		movieService.saveMovie(movie);
		
		return Constants.MEDIA;
	}
	
	@RequestMapping("/media/series/")
	public String showSeries(Model model) {
		
		model.addAttribute("title", "Series");
		model.addAttribute("types", types);
		
		return Constants.MEDIA;
	}
	
	@RequestMapping(value = "/media/series/", method = RequestMethod.POST)
	public String addSerie(Series series, Model model) {
		return Constants.MEDIA;
	}
	
	@RequestMapping("/media/books/")
	public String showBooks(Model model) {
		
		model.addAttribute("title", "Books");
		model.addAttribute("types", types);
		
		return Constants.MEDIA;
	}
	
	@RequestMapping(value = "/media/books/", method = RequestMethod.POST)
	public String addBook(Book book, Model model) {		
		return Constants.MEDIA;
	}
	
	// Edit
	@RequestMapping("/media/movies/add")
	public String editMovie(Model model) {	
		List<Genre> genres = genreService.getGenres();
		
		model.addAttribute("title", "Movies");
		model.addAttribute("types", types);
		model.addAttribute("genres", genres);
		model.addAttribute("Media", new Movie());
		
		return Constants.EDIT_MEDIA;
	} 
	
	@RequestMapping("/media/series/add")
	public String editSerie(Model model) {
		
		model.addAttribute("title", "Series");
		model.addAttribute("types", types);
		//model.addAttribute("genres", genres);
		model.addAttribute("Media", new Series());
		
		return Constants.EDIT_MEDIA;
	}
	
	@RequestMapping("/media/books/add")
	public String editBook(Model model) {
		
		model.addAttribute("title", "Books");
		model.addAttribute("types", types);
		//model.addAttribute("genres", genres);
		model.addAttribute("Media", new Book());
		
		return Constants.EDIT_MEDIA;
	}
}
