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
	
	@Transactional
	public Optional<Recensione> findById(Long id){
		return repo.findById(id);
	}
	@Transactional
	public List<Recensione> findAll(){
		return repo.findAll();
	}
	@Transactional
	public Recensione save(Recensione review) {
		return repo.save(review);
	}
	@Transactional
	public List<Recensione> findAllOrderByVote(){
		return repo.findAllByOrderByVote();
	}
	@Transactional
	public List<Recensione> findByUser(Utente user){
		return repo.findByUser(user);
	}
}
