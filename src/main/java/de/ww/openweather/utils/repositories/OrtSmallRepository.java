package de.ww.openweather.utils.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import de.ww.openweather.utils.persistence.OrtSmall;

//https://docs.spring.io/spring-data/jpa/docs/1.4.3.RELEASE/reference/html/jpa.repositories.html
public interface OrtSmallRepository extends PagingAndSortingRepository<OrtSmall, Long>{

}
