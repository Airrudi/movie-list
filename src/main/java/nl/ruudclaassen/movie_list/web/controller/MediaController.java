package nl.ruudclaassen.movie_list.web.controller;


import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import nl.ruudclaassen.movie_list.general.Constants;
import nl.ruudclaassen.movie_list.general.Utilities;
import nl.ruudclaassen.movie_list.model.Book;
import nl.ruudclaassen.movie_list.model.Media;
import nl.ruudclaassen.movie_list.model.Series;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.model.UserMediaStatus;
import nl.ruudclaassen.movie_list.service.MediaService;
import nl.ruudclaassen.movie_list.service.UserMediaService;
import nl.ruudclaassen.movie_list.service.UserService;

@Controller
public class MediaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MediaController.class);
	
	
	@Autowired
	MediaService mediaService;
	
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
	
	@RequestMapping("/media/{type}/{mediaUUID}")
	public String movieDetails(Model model, @PathVariable String mediaUUID, Principal principal) {
		User user = userService.getUserByUsername(principal.getName());
		
		Media media = mediaService.getByUUID(mediaUUID);
		UserMediaStatus usm = user.getJudgedMovies().get(media); // Get seen, owned (and your rating)
		boolean todo = user.getTodo().contains(media); // Check if movie is on your todo list
		
		model.addAttribute("username", principal.getName());
		model.addAttribute("media", media);
		model.addAttribute("todo", todo);
		model.addAttribute("owned", usm.isOwned());
		model.addAttribute("seen", usm.isSeen());
		model.addAttribute("rating", usm.getRating());	

		return Constants.MEDIA_DETAIL;
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
	
	@RequestMapping(value = "/image/{uuid}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String uuid) throws IOException {

        byte[] imageContent = mediaService.getImageByMediaUUID(uuid);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }
    
	@RequestMapping(value = "/{username}/media/{mediaUUID}", method = RequestMethod.POST)
	public ResponseEntity<Boolean>  saveJudgedMedia(
			Principal principal,
			@PathVariable String mediaUUID, 
			@RequestParam("seen") boolean seen, 
			@RequestParam("owned") boolean owned, 
			@RequestParam("todo") boolean todo,
			@RequestParam("rating") int rating
	){
		
		User user = userService.getUserByUsername(principal.getName());
		Media media = mediaService.getByUUID(mediaUUID);
		
		// TODO: Q: Pass variables in a JSON string?
		Map<String, Boolean> judgeResults = new HashMap<>();
		judgeResults.put("seen", seen);
		judgeResults.put("owned", owned);
		judgeResults.put("todo", todo);
		
		// TODO: Q: Create a new object to hold these values?
		
		userMediaService.addToJudged(user, media, judgeResults, rating);
		
        final HttpHeaders headers = new HttpHeaders();        
        return new ResponseEntity<Boolean>(headers, HttpStatus.OK);
	}
	
	


}
