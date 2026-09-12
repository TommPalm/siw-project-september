package it.uniroma3.siw.service;

import it.uniroma3.siw.repository.RegistaRepository;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.*;

@Service
public class RegistaService {

	private final RegistaRepository repo;

	public RegistaService(RegistaRepository repo) {
		this.repo = repo;
	}
	
	@Transactional
	public void delete(Regista r) {
		repo.delete(r);
	}
	
	@Transactional(readOnly=true)
	public Regista findBySurname(String surname) {
		return repo.findBySurname(surname);
	}
	@Transactional(readOnly=true)
	public Regista findById(Long id){
		return repo.findById(id).orElse(null);
	}
	@Transactional(readOnly=true)
	public List<Regista> findAll(){
		return repo.findAll();
	}
	@Transactional
	public Regista save(Regista director) {
		return repo.save(director);
	}
}
