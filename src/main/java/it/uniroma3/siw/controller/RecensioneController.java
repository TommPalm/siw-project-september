package it.uniroma3.siw.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.service.*;
import jakarta.validation.Valid;
import it.uniroma3.siw.model.*;

@Controller
public class RecensioneController {
	
	private final UtenteService utenteServ;
	private final RecensioneService recServ;
	private final FilmService filmServ;
	private final CredenzialiService credServ;
	
	public RecensioneController(UtenteService utenteServ, RecensioneService recServ, FilmService filmServ, CredenzialiService credServ) {
		this.utenteServ = utenteServ;
		this.recServ = recServ;
		this.filmServ = filmServ;
		this.credServ = credServ;
	}

/*
	@GetMapping("/registered/{id}/recensioni")
	public String recensioni(@PathVariable Long id, Model model) {
		Utente user = utenteServ.findById(id);
		model.addAttribute("reviews", user.getReviews());
		return "registered/recensioni";
	}*/
	@GetMapping("/registered/{id}/recensioni")
    public String recensioni(@PathVariable Long id, Model model) {

        model.addAttribute("userId", id);

        return "registered/recensioni";
    }
	

	@GetMapping("/recensione/{id}/mod")
    public String modifica(
            @PathVariable Long id,
            Model model) {

        Recensione recensione = recServ.findById(id);

        if (recensione == null) {
            return "registered/recensioni";
        }

        model.addAttribute("recensione", recensione);
        model.addAttribute("film", recensione.getFilm());
        model.addAttribute("films", filmServ.findAll());

        return "registered/modRec";
    }
	@GetMapping("/recensione/{id}/canc")
    public String elimina(
            @PathVariable Long id,
            Model model) {

        recServ.delete(id);
        

        return "redirect:/registered/home";
    }

	@GetMapping("/recensione/nuova")
	public String nuovaRecensione(Model model) {

	    model.addAttribute("recensione", new Recensione());
	    model.addAttribute("films", filmServ.findAll());

	    return "registered/newRec";
	}

	@PostMapping("/recensione/nuova")
	public String salvaNuova(
	        @Valid @ModelAttribute("recensione") Recensione recensione,
	        BindingResult recensioneBindingResult,
	        @RequestParam Long filmId,
	        Authentication authentication,
	        Model model) {

		
	    Film film = filmServ.findById(filmId);

	    if (film == null) {

	        model.addAttribute("films", filmServ.findAll());
	        model.addAttribute(
	                "filmError",
	                "Il film selezionato non esiste."
	        );

	        return "registered/newRec";
	    }

	    if (recensioneBindingResult.hasErrors()) {

	        model.addAttribute("films", filmServ.findAll());

	        return "registered/newRec";
	    }
	    String username = authentication.getName();
	    Credenziali cred = credServ.findByUsername(username);
	    Utente user = cred.getUser();

	    /*
	     * Controllo che l'utente non abbia già recensito
	     * questo film.
	     */

	    Recensione existing =
	            recServ.findByUserAndFilm(user, film);

	    if (existing != null) {

	        model.addAttribute("films", filmServ.findAll());

	        model.addAttribute(
	                "reviewError",
	                "Hai già scritto una recensione per questo film."
	        );

	        return "registered/newRec";
	    }

	    recensione.setUser(user);
	    recensione.setFilm(film);
	    recensione.setDate("16/9/2026");

	    recServ.save(recensione);

	    return "redirect:/registered/" + user.getId() + "/recensioni";
	}


	@PostMapping("/recensione")
	public String salva(
	        @Valid @ModelAttribute("recensione") Recensione recensione,
	        BindingResult recensioneBindingResult,
	        @RequestParam Long filmId,
	        Model model) {

	    // Recupero la recensione originale
	    Recensione recensioneOriginale =
	            recServ.findById(recensione.getId());

	    if (recensioneOriginale == null) {
	        return "registered/recensioni";
	    }

	    // Controllo che il film esista
	    Film film = filmServ.findById(filmId);

	    if (film == null) {

	        model.addAttribute("film", recensioneOriginale.getFilm());
	        model.addAttribute("films", filmServ.findAll());

	        model.addAttribute(
	                "filmError",
	                "Il film selezionato non esiste."
	        );

	        return "registered/modRec";
	    }

	    // Controllo validazione voto/testo
	    if (recensioneBindingResult.hasErrors()) {

	        model.addAttribute("film", film);
	        model.addAttribute("films", filmServ.findAll());

	        return "registered/modRec";
	    }


	    /*
	     * CASO 1:
	     * Il film scelto è lo stesso film della recensione originale.
	     *
	     * Aggiorno semplicemente la recensione originale.
	     */
	    if (recensioneOriginale.getFilm().getId().equals(film.getId())) {

	        recensioneOriginale.setVote(recensione.getVote());
	        recensioneOriginale.setText(recensione.getText());

	        recServ.save(recensioneOriginale);

	    } else {

	        /*
	         * CASO 2:
	         * L'utente ha scelto un film diverso.
	         *
	         * Controllo se l'utente ha già una recensione
	         * per il nuovo film.
	         */

	        Recensione altraRecensione =
	                recServ.findByUserAndFilm(
	                        recensioneOriginale.getUser(),
	                        film
	                );

	        if (altraRecensione != null) {

	            /*
	             * L'utente ha già recensito il nuovo film.
	             * Aggiorno quella recensione.
	             *
	             * La recensione originale NON viene modificata.
	             */

	            altraRecensione.setVote(recensione.getVote());
	            altraRecensione.setText(recensione.getText());

	            recServ.save(altraRecensione);

	        } else {

	            /*
	             * L'utente non ha ancora recensito il nuovo film.
	             * Creo una nuova recensione.
	             */

	            Recensione nuovaRecensione = new Recensione();

	            nuovaRecensione.setUser(recensioneOriginale.getUser());
	            nuovaRecensione.setFilm(film);
	            nuovaRecensione.setVote(recensione.getVote());
	            nuovaRecensione.setText(recensione.getText());

	            recServ.save(nuovaRecensione);
	        }
	    }


	    return "redirect:/registered/home";
	}

    
}
