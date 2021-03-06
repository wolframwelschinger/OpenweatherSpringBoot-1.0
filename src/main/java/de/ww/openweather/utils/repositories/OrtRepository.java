package de.ww.openweather.utils.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import de.ww.openweather.utils.persistence.Ort;

//https://docs.spring.io/spring-data/jpa/docs/1.4.3.RELEASE/reference/html/jpa.repositories.html
public interface OrtRepository extends PagingAndSortingRepository<Ort, Long>{

    String QUERY_BY_ORT = "SELECT o FROM Ort o "
            + "WHERE o.ort = :ort";	
	
    String QUERY_BY_LAND = "SELECT o FROM Ort o "
            + "WHERE o.land = :land";	 
    
    String QUERY_ALL = "SELECT o FROM Ort o "
            + "ORDER BY o.ort";    
    // WW Like erweitert gemaess https://stackoverflow.com/questions/10912320/named-query-with-like-in-where-clause
    String KEYWORD_SEARCH_QUERY = "SELECT o FROM Ort o " 
            + "WHERE STR(o.id) LIKE :keyword " 
            + "OR LOWER(o.ort) LIKE LOWER(CONCAT('%',:keyword,'%'))";    
    
	List<Ort> findByOrt(String ort);
	
	List<Ort> findByOrtOrderByIdDesc(String ort);
	
	List<Ort> findByOrtLikeOrderByIdDesc(String ort);
	
	@Query(QUERY_BY_LAND)
	Page<Ort> searchByLand(@Param("land") String land, Pageable pageable);
	
	@Query(QUERY_BY_ORT)
	Page<Ort> searchByOrt(@Param("ort") String ort, Pageable pageable);
	
	@Query(QUERY_ALL)
	List<Ort> findAllOrderByOrt();
	
    @Query(KEYWORD_SEARCH_QUERY)
    Page<Ort	> search(@Param("keyword") String keyword, Pageable pageable);	
	
}
