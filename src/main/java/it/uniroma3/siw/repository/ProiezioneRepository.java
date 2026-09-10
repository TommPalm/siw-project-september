package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.*;
import java.util.*;
import java.time.*;


public interface ProiezioneRepository extends JpaRepository<Proiezione, Long> {

	List<Proiezione> findByFilm(Film film);
	List<Proiezione> findByDate(LocalDate date);
	@Query(
            "SELECT p FROM Proiezione p " +
            "WHERE p.state = :state " +
                "AND p.date = :date " )
	List<Proiezione> findScheduledByDate(@Param("state") String state,
										@Param("date") String date);
}
