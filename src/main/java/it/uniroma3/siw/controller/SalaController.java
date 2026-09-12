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
public class SalaController {

	private final SalaService salaServ;

	public SalaController(SalaService salaServ) {
		this.salaServ = salaServ;
	}
	
	@GetMapping("/admin/sala/{id}/mod")
	public String modifica(Model model, @PathVariable Long id) {
		model.addAttribute("sala", salaServ.findById(id));
		return "admin/sala";
	}
	
	@PostMapping("/sala/mod")
	public String modifica(Model model,
	        @Valid @ModelAttribute("sala") Sala sala,
	        BindingResult salaBindingResult) {

	    if (salaBindingResult.hasErrors()) {
	    	model.addAttribute("sala", sala);
	        return "admin/sala";
	    }
	    Sala old = salaServ.findById(sala.getId());

	    old.setCapacity(sala.getCapacity());
	    old.setName(sala.getName());
	    old.setAddress(sala.getAddress());
	    salaServ.save(old);

	    return "redirect:/admin/gestione";
	}
	
	@GetMapping("/admin/sala/{id}/canc")
	public String canc(@PathVariable Long id) {
		Sala r = salaServ.findById(id);
		salaServ.delete(r);
		return "redirect:/admin/gestione";
	}
	
	@GetMapping("/admin/sala/crea")
	public String crea(Model model) {
	    model.addAttribute("sala", new Sala());
	    return "admin/crea/sala";
	}

	@PostMapping("/sala/crea")
	public String crea(
	        @Valid @ModelAttribute("sala") Sala sala,
	        BindingResult salaBindingResult,
	        Model model) {

	    if (salaBindingResult.hasErrors()) {
	        model.addAttribute("sala", sala);

	        return "admin/crea/sala";
	    }

	    salaServ.save(sala);

	    return "redirect:/admin/gestione";
	}
}
