package nl.ruudclaassen.movie_list.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nl.ruudclaassen.movie_list.general.Constants;
import nl.ruudclaassen.movie_list.service.UserService;


@Controller
public class HomeController {
	
	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String home(Model model, Principal principal) {
		// TODO: Toon de nieuwste films, series, boeken
		
		String username = principal.getName();		
		model.addAttribute("username", username);
		
		return Constants.HOMEPAGE;
	}
}
