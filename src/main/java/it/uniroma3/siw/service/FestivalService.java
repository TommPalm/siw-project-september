package it.uniroma3.siw.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.*;

@Service
public class FestivalService {

	private final FestivalRepository repo;

	public FestivalService(FestivalRepository repo) {
		this.repo = repo;
	}
	
	@Transactional(readOnly=true)
	public Festival findById(Long id){
		return repo.findById(id).orElse(null);
	}
	@Transactional(readOnly=true)
	public List<Festival> findAll(){
		return repo.findAll();
	}
	@Transactional
	public Festival save(Festival fest) {
		return repo.save(fest);
	}
	@Transactional
	public void delete(Festival fest) {
		repo.delete(fest);
	}
	@Transactional(readOnly=true)
	public List<Festival> findByName(String name){
		return repo.findByName(name);
	}
	@Transactional(readOnly=true)
	public List<Festival> findByCityAndYearOrderByStartDateEndDate(String city, int year){
		return repo.findByCityAndYearOrderByStartingDate(city, year);
	}
	
}
