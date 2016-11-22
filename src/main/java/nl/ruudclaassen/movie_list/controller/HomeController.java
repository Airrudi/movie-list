package nl.ruudclaassen.movie_list.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nl.ruudclaassen.movie_list.general.Constants;


@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(Model model) {
		return "redirect:/media/";
	}
}
