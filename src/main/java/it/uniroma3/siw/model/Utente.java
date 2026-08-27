package it.uniroma3.siw.model;

import java.util.*;
import jakarta.persistence.*;

@Entity
public class Utente {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Credenziali credentials;
	@OneToMany(mappedBy="user")
	private List<Recensione> reviews;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Credenziali getCredentials() {
		return credentials;
	}
	public void setCredentials(Credenziali credentials) {
		this.credentials = credentials;
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
		Utente other = (Utente) obj;
		return Objects.equals(id, other.id);
	}

	
}
