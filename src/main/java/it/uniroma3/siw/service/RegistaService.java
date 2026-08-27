package it.uniroma3.siw.service;

import it.uniroma3.siw.repository.RegistaRepository;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.*;

@Service
public class RegistaService {

	private final RegistaRepository repo;

	public RegistaService(RegistaRepository repo) {
		this.repo = repo;
	}
	
	@Transactional
	public Regista findBySurname(String surname) {
		return repo.findBySurname(surname);
	}
	@Transactional
	public Optional<Regista> findById(Long id){
		return repo.findById(id);
	}
	@Transactional
	public List<Regista> findAll(){
		return repo.findAll();
	}
	@Transactional
	public Regista save(Regista director) {
		return repo.save(director);
	}
}
