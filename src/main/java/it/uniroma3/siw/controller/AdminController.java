package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.service.*;

@Controller
public class AdminController {

	private final FestivalService festServ;
	private final FilmService filmServ;
	private final RegistaService regServ;
	private final SalaService salaServ;
	private final ProiezioneService proServ;
	
	
	public AdminController(FestivalService festServ, FilmService filmServ, RegistaService regServ, SalaService salaServ,
			ProiezioneService proServ) {
		this.festServ = festServ;
		this.filmServ = filmServ;
		this.regServ = regServ;
		this.salaServ = salaServ;
		this.proServ = proServ;
	}


	@GetMapping("/admin/gestione")
	public String admin(Model model) {
		model.addAttribute("festivals", festServ.findAll());
		model.addAttribute("films", filmServ.findAll());
		model.addAttribute("directors", regServ.findAll());
		model.addAttribute("rooms", salaServ.findAll());
		model.addAttribute("proiezioni", proServ.findAll());
		return "admin/gestione";
	}
}
