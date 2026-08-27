package it.uniroma3.siw.model;

import java.util.*;
import java.util.Objects;
import java.time.*;

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
	private String year;
	@Column(length=2000, nullable=true)
	private String description;
	@Column(nullable=false)
	private LocalDate startDate;
	@Column(nullable=false)
	private LocalDate endDate;
	
	@OneToMany(mappedBy = "festival", cascade=CascadeType.ALL)
	private List<Proiezione> projections;
	@ManyToMany(mappedBy= "festivals")
	private List<Film> films;
	
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public List<Proiezione> getProjections() {
		return projections;
	}
	public void setProjections(List<Proiezione> projections) {
		this.projections = projections;
	}
	public List<Film> getFilms() {
		return films;
	}
	public void setFilms(List<Film> films) {
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
