package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Film {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	private String title;
	private int year;
	private String length;
	private String genre;
	private String country;
	
	@OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
	private List<Proiezione> projections;
	@ManyToMany
	@JoinTable(
	    name = "film_festival",
	    joinColumns = @JoinColumn(name = "film_id"),
	    inverseJoinColumns = @JoinColumn(name = "festival_id")
	)
	private List<Festival> festivals;

	@ManyToOne
	private Regista director;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="film")
	private List<Recensione> reviews;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public List<Proiezione> getProjections() {
		return projections;
	}
	public void setProjections(List<Proiezione> projections) {
		this.projections = projections;
	}
	public List<Festival> getFestivals() {
		return festivals;
	}
	public void setFestivals(List<Festival> festivals) {
		this.festivals = festivals;
	}
	public Regista getDirector() {
		return director;
	}
	public void setDirector(Regista director) {
		this.director = director;
	}
	public List<Recensione> getReviews() {
		return reviews;
	}
	public void setReviews(List<Recensione> reviews) {
		this.reviews = reviews;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
