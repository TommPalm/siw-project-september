package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.*;

@Service
public class ProiezioneService {

	private final ProiezioneRepository repo;

	public ProiezioneService(ProiezioneRepository repo) {
		this.repo = repo;
	}
	
	@Transactional
	public Optional<Proiezione> findById(Long id){
		return repo.findById(id);
	}
	
	@Transactional
	public List<Proiezione> findAll(){
		return repo.findAll();
	}
	@Transactional
	public Proiezione save(Proiezione pro) {
		if(pro.getState()!="SCHEDULED" && pro.getState()!="CANCELLED" && pro.getState()!="COMPLETED") {
			pro.setState("SCHEDULED");
		}
		return repo.save(pro);
	}
	@Transactional
	public List<Proiezione> findByFilm(Film film){
		return repo.findByFilm(film);
	}
	@Transactional
	public List<Proiezione> findByDate(LocalDate date){
		return repo.findByDate(date);
	}
	@Transactional
	public List<Proiezione> findScheduledByDate(String date){
		return repo.findScheduledByDate("SCHEDULED", date);
	}
}
