package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.*;
import it.uniroma3.siw.model.*;

public interface CredenzialiRepository extends JpaRepository<Credenziali, Long> {

	Credenziali findByUsername(String username);
}
