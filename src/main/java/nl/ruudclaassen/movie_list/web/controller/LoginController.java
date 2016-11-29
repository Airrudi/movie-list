package nl.ruudclaassen.movie_list.web.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.ruudclaassen.movie_list.general.Constants;
import nl.ruudclaassen.movie_list.model.Login;

@Controller
public class LoginController {
	
	@RequestMapping("/login/")
	public String showLogin(Model model) {
		
		model.addAttribute("title", "Login");
		model.addAttribute("login", new Login());		
		
		return Constants.LOGIN;
	}
	
	@RequestMapping(value = "/logout/", method=RequestMethod.POST)
	public String logout(HttpSession session) {
	    session.invalidate();
	    return Constants.REDIRECT_LOGIN; 
	}
	
	@RequestMapping("/login/success")
	public String redirectLoginSuccess(Principal principal){
		return "redirect:/" + principal.getName() + "/";
	}
}
