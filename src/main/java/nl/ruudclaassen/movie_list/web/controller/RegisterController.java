package nl.ruudclaassen.movie_list.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import nl.ruudclaassen.movie_list.general.Constants;
import nl.ruudclaassen.movie_list.model.User;
import nl.ruudclaassen.movie_list.service.LoginService;
import nl.ruudclaassen.movie_list.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/register/")
	public String registerForm(Model model) {
		
		model.addAttribute("title", "Register");
		model.addAttribute("user", new User());		
		
		return Constants.REGISTER;
	}
	
	@RequestMapping(value = "/register/", method=RequestMethod.POST)
	public String registerUser(
			User user,
			@RequestParam("password") String password 
	) {	    
		// Create new user
		userService.saveUser(user);
		
		// Create login
		loginService.saveLogin(user, password);
		
		// TODO: Step to activate account via email?
		
	    return Constants.REDIRECT_LOGIN; 
	}
}
