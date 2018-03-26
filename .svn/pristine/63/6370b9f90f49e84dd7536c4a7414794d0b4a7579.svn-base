package de.ww.openweather.utils.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import de.ww.openweather.utils.persistence.Ort;
import de.ww.openweather.utils.persistence.Wetteraufzeichnung;

//https://docs.spring.io/spring-data/jpa/docs/1.4.3.RELEASE/reference/html/jpa.repositories.html
public interface WetteraufzeichnungRepository extends PagingAndSortingRepository<Wetteraufzeichnung, Long>{
	
    String QUERY_BY_ORT = "SELECT w FROM Wetteraufzeichnung w "
            + "WHERE w.ort = :ort";	
	
    String QUERY_BY_LAND = "SELECT w FROM Wetteraufzeichnung w "
            + "WHERE w.land = :land";		
    
    // WW Like erweitert gemaess https://stackoverflow.com/questions/10912320/named-query-with-like-in-where-clause
    String KEYWORD_SEARCH_QUERY = "SELECT w FROM Wetteraufzeichnung w " 
            + "WHERE STR(w.id) LIKE :keyword " 
            + "OR LOWER(w.ort) LIKE LOWER(CONCAT('%',:keyword,'%')) "
            + "OR LOWER(w.land) LIKE LOWER(CONCAT('%',:keyword,'%'))";     
	
	List<Wetteraufzeichnung> findByOrt(String ort);
	
	List<Wetteraufzeichnung> findByOrtOrderByIdDesc(String ort);
	
	List<Wetteraufzeichnung> findByOrtLikeOrderByIdDesc(String ort);
	
	@Query(QUERY_BY_LAND)
	Page<Wetteraufzeichnung> searchByLand(@Param("land") String land, Pageable pageable);
	
	@Query(QUERY_BY_ORT)
	Page<Wetteraufzeichnung> searchByOrt(@Param("ort") String ort, Pageable pageable);
	
    @Query(KEYWORD_SEARCH_QUERY)
    Page<Wetteraufzeichnung> search(@Param("keyword") String keyword, Pageable pageable);	

}
