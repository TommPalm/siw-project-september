package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.service.*;
import jakarta.validation.Valid;
import it.uniroma3.siw.model.*;

@Controller
public class ProiezioneController {

	private final ProiezioneService proServ;
	private final FestivalService feServ;
	private final FilmService fiServ;
	private final SalaService saServ;

	public ProiezioneController(ProiezioneService proServ, SalaService saServ, FilmService fiServ, FestivalService feServ) {
		this.proServ = proServ;
		this.feServ = feServ;
		this.fiServ = fiServ;
		this.saServ = saServ;
	}
	
	
	@GetMapping("/admin/proiezione/{id}/mod")
	public String modifica(Model model, @PathVariable Long id) {
		model.addAttribute("proiezione", proServ.findById(id));
		model.addAttribute("festivals", feServ.findAll());
		model.addAttribute("films", fiServ.findAll());
		model.addAttribute("sale", saServ.findAll());
		return "admin/proiezione";
	}
	
	@PostMapping("/proiezione/mod")
	public String modifica(
	        @Valid @ModelAttribute("proiezione") Proiezione proiezione,
	        BindingResult proiezioneBindingResult,
	        @RequestParam Long festivalId,
	        @RequestParam Long filmId,
	        @RequestParam Long roomId,
	        Model model) {

	    if (proiezioneBindingResult.hasErrors()) {
	        model.addAttribute("festivals", feServ.findAll());
	        model.addAttribute("films", fiServ.findAll());
	        model.addAttribute("sale", saServ.findAll());
	        return "admin/proiezione";
	    }

	    Proiezione old = proServ.findById(proiezione.getId());

	    old.setDate(proiezione.getDate());
	    old.setTime(proiezione.getTime());
	    old.setState(proiezione.getState());

	    old.setFestival(feServ.findById(festivalId));
	    old.setFilm(fiServ.findById(filmId));
	    old.setRoom(saServ.findById(roomId));

	    proServ.save(old);

	    return "redirect:/admin/gestione";
	}

	
	@GetMapping("/admin/proiezione/{id}/canc")
	public String canc(@PathVariable Long id) {
		Proiezione r = proServ.findById(id);
		proServ.delete(r);
		return "redirect:/admin/gestione";
	}
	
	@GetMapping("/admin/proiezione/crea")
	public String crea(Model model) {
	    model.addAttribute("proiezione", new Proiezione());
	    model.addAttribute("festivals", feServ.findAll());
		model.addAttribute("films", fiServ.findAll());
		model.addAttribute("sale", saServ.findAll());
	    return "admin/crea/proiezione";
	}

	@PostMapping("/proiezione/crea")
	public String crea(
	        @Valid @ModelAttribute("proiezione") Proiezione proiezione,
	        BindingResult proiezioneBindingResult,
	        Model model) {

	    if (proiezioneBindingResult.hasErrors()) {
	        model.addAttribute("festivals", feServ.findAll());
			model.addAttribute("films", fiServ.findAll());
			model.addAttribute("sale", saServ.findAll());
	        return "admin/crea/proiezione";
	    }

	    proServ.save(proiezione);

	    return "redirect:/admin/gestione";
	}
}
