package it.uniroma3.siw.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.service.*;

@Controller
public class UtenteController {
	
	private final CredenzialiService credServ;
	private final FestivalService festServ;

	public UtenteController(CredenzialiService credServ, FestivalService festServ) {
		this.credServ = credServ;
		this.festServ = festServ;
	}

	@GetMapping("/registered/home")
    public String userHome(Authentication authentication, Model model) {

        String username = authentication.getName();
        Credenziali cred = credServ.findByUsername(username);
        model.addAttribute("user", cred.getUser());
        model.addAttribute("username", username);
        model.addAttribute("festivals", festServ.findAll());

        return "registered/home";
    }
	
}
