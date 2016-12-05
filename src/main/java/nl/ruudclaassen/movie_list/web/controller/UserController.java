package nl.ruudclaassen.movie_list.web.controller;

import java.security.Principal;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;

import nl.ruudclaassen.movie_list.general.Constants;
import nl.ruudclaassen.movie_list.model.Movie;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.model.UserMediaStatus;
import nl.ruudclaassen.movie_list.service.UserMediaService;
import nl.ruudclaassen.movie_list.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	UserMediaService userMediaService;
	
	@Autowired
	UserService userService;
	
	
	// #####################
	// ### VISIBLE PAGES ###
	// #####################
	
	

	
	@RequestMapping("/{username}/")
	public String showCategories(Model model, @PathVariable String username){
		model.addAttribute("username", username);
		
		return Constants.USER_HOME;
	}
	
		
	
	@RequestMapping("/{username}/{mediaType}/")
	public String showSubcategories(Model model, Principal principal) {		
		model.addAttribute("username", principal.getName());
		
		return Constants.USER_MEDIA_SUBCATEGORIES; // seen, not seen, owned, todo
	}
	
	@RequestMapping("/{username}/{type}/todo/")
	public String show(Model model, @PathVariable String username) { // @PathVariable TODO: FIX Pathvariable conversion		
		User user = userService.getUserByUsername(username);
		Map<Movie, UserMediaStatus> todos = userMediaService.getTodos(user);
		Map<Movie, UserMediaStatus> ownedByFriends = userService.getOwnedByFriends(user);
		Map<Movie, UserMediaStatus> todoByFriends = userService.getAllFriendTodos(user);
		
		model.addAttribute("ownedByFriends",ownedByFriends);
		model.addAttribute("todoByFriends",todoByFriends);	
		model.addAttribute("title", "Todo's");
		model.addAttribute("type", "todos");
		model.addAttribute("media", todos);
		model.addAttribute("todoList", user.getTodos());
		model.addAttribute("username", username);
		
		return Constants.USER_MEDIA;
	}
	
	@RequestMapping("/{username}/{type}/seen/")
	public String showSeen(Model model, @PathVariable String username) {// TODO: Enum		
		User user = userService.getUserByUsername(username);
		
		Map<String, Map<String, UserMediaStatus>> seenAndToSee = userMediaService.getSeenAndToSee(user);
		Map<String, UserMediaStatus> itemsSeen = seenAndToSee.get("seen");
		
		Map<Movie, UserMediaStatus> ownedByFriends = userService.getOwnedByFriends(user);		
		model.addAttribute("ownedByFriends",ownedByFriends);
		
		model.addAttribute("title", "Seen");
		model.addAttribute("type", "seen");
		model.addAttribute("media", itemsSeen);
		model.addAttribute("todoList", user.getTodos());
		model.addAttribute("username", username);
		
		return Constants.USER_MEDIA;
	}
	
	@RequestMapping("/{username}/{type}/notseen/")
	public String showNotSeen(Model model, @PathVariable String username) {// TODO: Enum
		User user = userService.getUserByUsername(username);
		
		Map<String, Map<String, UserMediaStatus>> seenAndToSee = userMediaService.getSeenAndToSee(user);
		Map<String, UserMediaStatus> notSeen = seenAndToSee.get("toSee");
		
		Map<Movie, UserMediaStatus> ownedByFriends = userService.getOwnedByFriends(user);		
		model.addAttribute("ownedByFriends",ownedByFriends);
		
		model.addAttribute("title", "Not seen");
		model.addAttribute("type", "notseen");
		model.addAttribute("media", notSeen);
		model.addAttribute("todoList", user.getTodos());
		model.addAttribute("username", username);
		
		return Constants.USER_MEDIA;
	}
	
	@RequestMapping("/{username}/{type}/owned/")
	public String showOwned(Model model, @PathVariable String username) {		
		User user = userService.getUserByUsername(username);
		Map<Movie, UserMediaStatus> ownedMedia = userMediaService.getOwned(user);
		
		Map<Movie, UserMediaStatus> ownedByFriends = userService.getOwnedByFriends(user);		
		model.addAttribute("ownedByFriends",ownedByFriends);
		
		model.addAttribute("title", "Owned");
		model.addAttribute("type", "owned");
		model.addAttribute("todoList", user.getTodos());
		model.addAttribute("media", ownedMedia);
		model.addAttribute("username", username);
		
		return Constants.USER_MEDIA;
	}
	
	
	
	
	
	
	
	// #######################
	// ### EDITING ACTIONS ###
	// #######################
	
	@RequestMapping("/media/{mediaUUID}/delete")
	public ResponseEntity<Boolean> removeMedia(
			Model model, 
			Principal principal,
			@PathVariable String mediaUUID			 
	){
		
		// Remove
		String username = principal.getName();
		userMediaService.removeFromJudged(username, mediaUUID);		
		
		final HttpHeaders headers = new HttpHeaders();        
	    return new ResponseEntity<Boolean>(headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/media/{mediaUUID}/toggleSeen", method = RequestMethod.POST)
	public ResponseEntity<Boolean> toggleSeenStatus(
			Model model,
			Principal principal,			
			@PathVariable String mediaUUID,
			@RequestParam("seen") boolean seen 
	){
		
		// Remove
		String username = principal.getName();
		userMediaService.toggleSeen(username, mediaUUID, seen);		
		
		final HttpHeaders headers = new HttpHeaders();        
	    return new ResponseEntity<Boolean>(headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/media/{mediaUUID}/toggleTodo", method = RequestMethod.POST)
	public ResponseEntity<Boolean> toggleTodo(
			Model model,
			Principal principal,
			@PathVariable String mediaUUID,
			@RequestParam("todo") boolean todo 
	){
		
		// Toggle
		String username = principal.getName();
		userMediaService.toggleTodo(username, mediaUUID, todo);		
		
		final HttpHeaders headers = new HttpHeaders();        
	    return new ResponseEntity<Boolean>(headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/media/{mediaUUID}/toggleOwned", method = RequestMethod.POST)
	public ResponseEntity<Boolean> toggleOwned(
			Model model, 
			Principal principal,
			@PathVariable String mediaUUID,
			@RequestParam("owned") boolean owned			
	){
		
		// Toggle
		String username = principal.getName();
		userMediaService.toggleOwned(username, mediaUUID, owned);		
		
		final HttpHeaders headers = new HttpHeaders();        
	    return new ResponseEntity<Boolean>(headers, HttpStatus.OK);
	}
	
	
	
		public static class UserViewModel{
			
			private final User user;
			private final Set<String> owned;
			private final Set<String> seen;
			
			public UserViewModel(User user, Set<String> seen, Set<String> owned) {				
				this.user = user;
				this.owned = owned;
				this.seen = seen;
			}

			public User getUser() {
				return user;
			}
			
			public Set<String> getOwned() {
				return owned;
			}
			
			public Set<String> getSeen() {
				return seen;
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((user == null) ? 0 : user.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				UserViewModel other = (UserViewModel) obj;
				if (user == null) {
					if (other.user != null)
						return false;
				} else if (!user.equals(other.user))
					return false;
				return true;
			}			
		}
		
}
