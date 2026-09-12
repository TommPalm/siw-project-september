package it.uniroma3.siw.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.*;

@Service
public class UtenteService {
	
	private final UtenteRepository repo;
	

	public UtenteService(UtenteRepository repo) {
		this.repo = repo;
	}


	@Transactional(readOnly=true)
	public Utente findById(Long id) {
		return repo.findById(id).orElse(null);
	}
}
