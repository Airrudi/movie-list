package nl.ruudclaassen.movie_list.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import nl.ruudclaassen.movie_list.general.Constants;

@Controller
public class UserController {
	
	@RequestMapping("/{username}/")
	public String userHomePage(Model model, @PathVariable String username){
		model.addAttribute("username", username);
		
		return Constants.USER_HOME;
	}
}
