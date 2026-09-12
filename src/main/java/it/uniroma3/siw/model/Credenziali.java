package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Credenziali {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String password;
	@NotBlank
	@Column(unique=true)
	private String username;
	private String user_role; // ADMIN, USER

	@OneToOne(cascade = CascadeType.ALL)
	private Utente user;
	
	
	public Utente getUser() {
		return user;
	}
	public void setUser(Utente user) {
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return user_role;
	}
	public void setRole(String role) {
		this.user_role = role;
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
		Credenziali other = (Credenziali) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
