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
public class FestivalController {

	private final FestivalService festServ;
	
	public FestivalController(FestivalService festServ) {
		this.festServ = festServ;
	}



	@GetMapping("/festival/{id}/info")
	public String festivalInfo(@PathVariable Long id, Model model) {
		Festival festival = festServ.findById(id);
		model.addAttribute("proiezioni", festival.getProjections());
		model.addAttribute("films", festival.getFilms());
		model.addAttribute("festival", festServ.findById(id));
		
		return "festival";
	}
	
	@GetMapping("/festival/{id}/info/registered")
	public String registered(@PathVariable Long id, Model model) {
		Festival festival = festServ.findById(id);
		model.addAttribute("proiezioni", festival.getProjections());
		model.addAttribute("films", festival.getFilms());
		model.addAttribute("festival", festServ.findById(id));
		
		return "registered/festival";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/admin/festival/{id}/mod")
	public String modifica(@PathVariable Long id, Model model) {
		Festival fest = festServ.findById(id);
		model.addAttribute("fest", fest);
		return "admin/festival";
	}
	
	@GetMapping("/admin/festival/crea")
	public String crea(Model model) {
		model.addAttribute("fest", new Festival());
		return "admin/crea/festival";
	}
	
	@PostMapping("/festival/mod")
	public String modifica(
	        @Valid @ModelAttribute("fest") Festival fest,
	        BindingResult festBindingResult) {

	    if (festBindingResult.hasErrors()) {
	        return "admin/festival";
	    }
	    Festival old = festServ.findById(fest.getId());

	    old.setCity(fest.getCity());
	    old.setDescription(fest.getDescription());
	    old.setName(fest.getName());
	    old.setEndingDate(fest.getEndingDate());
	    old.setStartingDate(fest.getStartingDate());
	    old.setYear(fest.getYear());
	    festServ.save(old);

	    return "redirect:/admin/gestione";
	}
	
	@PostMapping("/festival/nuovo")
	public String nuovo(
	        @Valid @ModelAttribute("fest") Festival fest,
	        BindingResult festBindingResult) {

	    if (festBindingResult.hasErrors()) {
	        return "admin/festival";
	    }
	    festServ.save(fest);

	    return "redirect:/admin/gestione";
	}
	
	@GetMapping("/admin/festival/{id}/canc")
	public String canc(@PathVariable Long id) {
		Festival fest = festServ.findById(id);
		festServ.delete(fest);
		return "redirect:/admin/gestione";
	}

	
}
