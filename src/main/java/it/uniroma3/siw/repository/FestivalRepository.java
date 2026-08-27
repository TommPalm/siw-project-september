package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.*;
import it.uniroma3.siw.model.Festival;
import java.util.*;

public interface FestivalRepository extends JpaRepository<Festival,Long>{

	/**
     * Carica tutte le proiezioni insieme ai loro festival in una singola query SQL,
     * risolvendo il problema N+1.
     *
     * Senza questa query, Hibernate userebbe la strategia LAZY di default:
     * 1 query per caricare i festival + 1 query per ogni festival per caricare
     * le proiezioni associate (N+1 query totali).
     *
     * Con LEFT JOIN FETCH, Hibernate carica festival e film in una sola query
     * tramite JOIN sulla tabella di join "festival_proiezioni".
     *
     * DISTINCT è necessario perché il JOIN produce righe duplicate:
     * DISTINCT elimina i duplicati a livello di oggetto Java.
     */
	
	@Query("SELECT DISTINCT f FROM Festival f LEFT JOIN FETCH f.projections")
	List<Festival> findAllWithProjections();
		
	List<Festival> findByName(String name);
	
	List<Festival> findByCityAndYearOrderByStartDateEndDate(String city, int year);

}
