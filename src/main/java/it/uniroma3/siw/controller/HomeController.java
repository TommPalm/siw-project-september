package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import it.uniroma3.siw.service.*;
import java.util.*;
import it.uniroma3.siw.model.*;

@Controller
public class HomeController {

	private final FestivalService festServ;
	
	public HomeController(FestivalService festServ) {
		this.festServ = festServ;
	}

	@GetMapping("/")
	public String home(Model model) {
	    model.addAttribute("festivals", festServ.findAll());
	    return "home";
	}

}
