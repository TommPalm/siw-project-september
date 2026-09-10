package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.*;
import it.uniroma3.siw.model.Festival;
import java.util.*;

public interface FestivalRepository extends JpaRepository<Festival,Long>{
		
	List<Festival> findByName(String name);
	
	List<Festival> findByCityAndYearOrderByStartingDate(String city, int year);

}
