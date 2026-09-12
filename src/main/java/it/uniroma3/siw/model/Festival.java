package it.uniroma3.siw.model;

import java.util.*;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Festival {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String city;
	@Column(nullable=false)
	private int year;
	@Column(length=2000, nullable=true)
	private String description;
	@Column(nullable=false)
	private String startingDate;
	@Column(nullable=false)
	private String endingDate;
	
	@OneToMany(mappedBy = "festival", cascade=CascadeType.ALL)
	private List<Proiezione> projections;
	@ManyToMany(mappedBy= "festivals")
	private Set<Film> films = new HashSet<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}
	public String getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(String endingDate) {
		this.endingDate = endingDate;
	}
	public List<Proiezione> getProjections() {
		return projections;
	}
	public void setProjections(List<Proiezione> projections) {
		this.projections = projections;
	}
	public Set<Film> getFilms() {
		return films;
	}
	public void setFilms(Set<Film> films) {
		this.films = films;
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
		Festival other = (Festival) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
