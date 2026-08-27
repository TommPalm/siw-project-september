package it.uniroma3.siw.service;

import it.uniroma3.siw.repository.SalaRepository;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.*;

@Service
public class SalaService {

	private final SalaRepository repo;

	public SalaService(SalaRepository repo) {
		this.repo = repo;
	}
	
	@Transactional
	public List<Sala> findAll(){
		return repo.findAll();
	}
	@Transactional
	public Optional<Sala> findById(Long id){
		return repo.findById(id);
	}
	@Transactional
	public Sala save(Sala sala) {
		return repo.save(sala);
	}
	
	@Transactional
	public List<Sala> findByAddress(String address){
		return repo.findByAddress(address);
	}
	@Transactional
	public List<Sala> findByName(String name){
		return repo.findByName(name);
	}
}
