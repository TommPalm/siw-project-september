package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.model.*;
import java.util.*;

public interface RecensioneRepository extends JpaRepository<Recensione, Long> {

	List<Recensione> findByUser(Utente user);
	
	List<Recensione> findAllByOrderByVote();
}
