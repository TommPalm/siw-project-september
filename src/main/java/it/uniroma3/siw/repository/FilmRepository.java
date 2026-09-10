package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.*;
import it.uniroma3.siw.model.*;
import java.util.*;

public interface FilmRepository extends JpaRepository<Film, Long> {
	List<Film> findByDirector(String director);
	Film findByTitle(String title);
	List<Film> findByYear(int year);
	List<Film> findByGenre(String genre);
	
	
    @Query("SELECT DISTINCT f FROM Film f LEFT JOIN FETCH f.festivals")
	List<Film> findAllWithFestival();
}
