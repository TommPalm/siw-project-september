package it.uniroma3.siw.model;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
public class Proiezione {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String date;
	@Column(nullable=false)
	private String time;
	private String state; //cancelled, completed, scheduled
	
	@ManyToOne
	private Festival festival;
	@ManyToOne
	private Sala room;
	@ManyToOne
	private Film film;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Festival getFestival() {
		return festival;
	}
	public void setFestival(Festival festival) {
		this.festival = festival;
	}
	public Sala getRoom() {
		return room;
	}
	public void setRoom(Sala room) {
		this.room = room;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
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
		Proiezione other = (Proiezione) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
