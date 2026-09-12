package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.service.*;
import jakarta.validation.Valid;
import it.uniroma3.siw.model.*;

@Controller
public class FilmController {
	
	private final FilmService filmServ;
	private final RegistaService registaServ;
	private final FestivalService festivalServ;

	public FilmController(
	        FilmService filmServ,
	        RegistaService registaServ,
	        FestivalService festivalServ) {

	    this.filmServ = filmServ;
	    this.registaServ = registaServ;
	    this.festivalServ = festivalServ;
	}


	@GetMapping("/film/{id}/info")
	public String infoFilm(@PathVariable Long id, Model model) {
		Film film = filmServ.findById(id);
		model.addAttribute("reviews", film.getReviews());
		model.addAttribute("film", film);
		model.addAttribute("proiezioni", film.getProjections());
		return "film";
	}
	
	@GetMapping("/film/{id}/info/registered")
	public String registere(@PathVariable Long id, Model model) {
		Film film = filmServ.findById(id);
		model.addAttribute("reviews", film.getReviews());
		model.addAttribute("film", film);
		model.addAttribute("proiezioni", film.getProjections());
		return "registered/film";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/admin/film/{id}/mod")
	public String modifica(Model model, @PathVariable Long id) {
		model.addAttribute("film", filmServ.findById(id));
		model.addAttribute("festivals", festivalServ.findAll());
		return "admin/film";
	}
	
	@PostMapping("/film/mod")
	public String modifica(Model model,
	        @Valid @ModelAttribute("film") Film film,
	        BindingResult filmBindingResult) {

	    if (filmBindingResult.hasErrors()) {
	    	model.addAttribute("film", film);
			model.addAttribute("festivals", festivalServ.findAll());
	        return "admin/film";
	    }
	    Film old = filmServ.findById(film.getId());

	    old.setCountry(film.getCountry());
	    old.setTitle(film.getTitle());
	    old.setGenre(film.getGenre());
	    old.setLength(film.getLength());
	    old.setYear(film.getYear());
	    old.setFestivals(film.getFestivals());
	    filmServ.save(old);

	    return "redirect:/admin/gestione";
	}
	
	@GetMapping("/admin/film/{id}/canc")
	public String canc(@PathVariable Long id) {
		Film film = filmServ.findById(id);
		filmServ.delete(film);
		return "redirect:/admin/gestione";
	}
	
	@GetMapping("/admin/film/crea")
	public String crea(Model model) {
	    model.addAttribute("film", new Film());
	    model.addAttribute("registi", registaServ.findAll());
	    model.addAttribute("festivals", festivalServ.findAll());

	    return "admin/crea/film";
	}

	@PostMapping("/film/crea")
	public String crea(
	        @Valid @ModelAttribute("film") Film film,
	        BindingResult filmBindingResult,
	        Model model) {

	    if (filmBindingResult.hasErrors()) {
	        model.addAttribute("registi", registaServ.findAll());
	        model.addAttribute("festivals", festivalServ.findAll());

	        return "admin/crea/film";
	    }

	    filmServ.save(film);

	    return "redirect:/admin/gestione";
	}

}
