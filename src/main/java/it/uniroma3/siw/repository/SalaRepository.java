package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.uniroma3.siw.model.*;
import java.util.*;

public interface SalaRepository extends JpaRepository<Sala, Long> {

	List<Sala> findByAddress(String address);
	List<Sala> findByName(String name);
}
