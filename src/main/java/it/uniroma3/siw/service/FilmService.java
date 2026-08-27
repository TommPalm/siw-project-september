package it.uniroma3.siw.service;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.*;


@Service
public class FilmService {

	private final FilmRepository repo;

	public FilmService(FilmRepository repo) {
		this.repo = repo;
	}
	
	@Transactional
	public Optional<Film> findById(Long id){
		return repo.findById(id);
	}
	@Transactional
	public List<Film> findAll(){
		return repo.findAll();
	}
	@Transactional
	public Film save(Film film) {
		return repo.save(film);
	}
	@Transactional
	public Film findByTitle(String title) {
		return repo.findByTitle(title);
	}
	@Transactional
	public List<Film> findByGenre(String genre){
		return repo.findByGenre(genre);
	}
	@Transactional
	public List<Film> findByDirector(String director){
		return repo.findByDirector(director);
	}
	@Transactional
	public List<Film> findByYear(int year){
		return repo.findByYear(year);
	}
}
