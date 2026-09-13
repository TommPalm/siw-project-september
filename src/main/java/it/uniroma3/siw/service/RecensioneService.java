package it.uniroma3.siw.service;

import org.springframework.stereotype.Service;
import java.util.*;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.*;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecensioneService {

	private final RecensioneRepository repo;

	public RecensioneService(RecensioneRepository repo) {
		this.repo = repo;
	}
	
	@Transactional(readOnly=true)
	public Recensione findById(Long id){
		return repo.findById(id).orElse(null);
	}
	@Transactional(readOnly=true)
	public List<Recensione> findAll(){
		return repo.findAll();
	}
	@Transactional
	public Recensione save(Recensione review) {
		return repo.save(review);
	}
	@Transactional(readOnly=true)
	public List<Recensione> findAllOrderByVote(){
		return repo.findAllByOrderByVote();
	}
	@Transactional(readOnly=true)
	public List<Recensione> findByUser(Utente user){
		return repo.findByUser(user);
	}
	@Transactional(readOnly=true)
	public Recensione findByUserAndFilm(Utente user, Film film) {
	    return repo.findByUserAndFilm(user, film).orElse(null);
	}
	@Transactional
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Transactional
	public Recensione updateRecensione(
	        Long recensioneId,
	        Integer vote,
	        String text) {

	    Recensione recensione = findById(recensioneId);

	    if (recensione == null) {
	        return null;
	    }

	    recensione.setVote(vote);
	    recensione.setText(text);

	    return save(recensione);
	}


}
