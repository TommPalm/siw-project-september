package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.service.*;
import jakarta.validation.Valid;

@Controller
public class RegistaController {

	private final RegistaService regSer;

	public RegistaController(RegistaService regSer) {
		this.regSer = regSer;
	}
	
	@GetMapping("/regista/{id}/info")
	public String registaInfo(@PathVariable Long id, Model model) {
		Regista regista = regSer.findById(id);
		model.addAttribute("reg", regista);
		model.addAttribute("films", regista.getFilms());
		return "regista";
	}
	
	@GetMapping("/regista/{id}/info/registered")
	public String registered(@PathVariable Long id, Model model) {
		Regista regista = regSer.findById(id);
		model.addAttribute("reg", regista);
		model.addAttribute("films", regista.getFilms());
		return "registered/regista";
	}
	
	////////////////////////////////////////////////////////////////////////////////
	
	
	@GetMapping("/admin/regista/{id}/mod")
	public String modifica(Model model, @PathVariable Long id) {
		model.addAttribute("regista", regSer.findById(id));
		return "admin/regista";
	}
	
	@PostMapping("/regista/mod")
	public String modifica(Model model,
	        @Valid @ModelAttribute("regista") Regista regista,
	        BindingResult registaBindingResult) {

	    if (registaBindingResult.hasErrors()) {
	    	model.addAttribute("regista", regista);
	        return "admin/regista";
	    }
	    Regista old = regSer.findById(regista.getId());

	    old.setCountry(regista.getCountry());
	    old.setBirth(regista.getBirth());
	    old.setName(regista.getName());
	    old.setSurname(regista.getSurname());
	    regSer.save(old);

	    return "redirect:/admin/gestione";
	}
	
	@GetMapping("/admin/regista/{id}/canc")
	public String canc(@PathVariable Long id) {
		Regista r = regSer.findById(id);
		regSer.delete(r);
		return "redirect:/admin/gestione";
	}
	
	@GetMapping("/admin/regista/crea")
	public String crea(Model model) {
	    model.addAttribute("regista", new Regista());
	    return "admin/crea/regista";
	}

	@PostMapping("/regista/crea")
	public String crea(
	        @Valid @ModelAttribute("regista") Regista regista,
	        BindingResult registaBindingResult,
	        Model model) {

	    if (registaBindingResult.hasErrors()) {
	        model.addAttribute("regista", regista);

	        return "admin/crea/regista";
	    }

	    regSer.save(regista);

	    return "redirect:/admin/gestione";
	}
}
