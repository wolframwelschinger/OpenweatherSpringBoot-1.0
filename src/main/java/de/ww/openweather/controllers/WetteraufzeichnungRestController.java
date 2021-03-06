package de.ww.openweather.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.ww.openweather.controllers.param.PageParam;
import de.ww.openweather.controllers.param.SortParam;
import de.ww.openweather.controllers.param.WetteraufzeichnungFilterParam;
import de.ww.openweather.utils.persistence.Wetteraufzeichnung;
import de.ww.openweather.utils.repositories.WetteraufzeichnungRepository;

@RestController
@RequestMapping("/restservices")
public class WetteraufzeichnungRestController {

	@Autowired
	private WetteraufzeichnungRepository wetteraufzeichnungRepo;

	private Logger log = LogManager.getLogger(this.getClass().getName());

	/**
     * Liefert eine sortierte und gefilterte Liste von Wetteraufzeichnungen mit Paging.
     * 
     * @param filter Filter
     * @param sort Sortierung
     * @param page Seite
     * @return gefilterte und sortierte Liste von Wetteraufzeichnungswerten
     */
	@RequestMapping("/wetteraufzeichnung")
	public Page<Wetteraufzeichnung> listOrt(
		@RequestParam(required = false) WetteraufzeichnungFilterParam filter,
		@RequestParam(defaultValue = "{}") SortParam sort,
		@RequestParam(defaultValue = "{\"index\":0,\"size\":10}") PageParam page) {	
		
		log.info("Filter: " + filter);
		log.info("Sort: " + sort);
		log.info("Page: " + page);
		
		//findAll
		// http://localhost:9999/restservices/ort?filter=%7B%7D&page=%7B7D&sort=%7B%7D

		// HTML URL Encoding: https://www.w3schools.com/TagS/ref_urlencode.asp
		// %7B = {
		// %22 = "
		// %7D =}
		// %3A = :
		// Alle Orte in ES, sortiert nach Ort, 1. Seite
		// http://localhost:9999/restservices/ort?filter={"land":"ES"}&sort={"orderBy":"ort"}&page={"index":0}
		// http://localhost:9999/restservices/ort?filter=%7B%22land%22%3A%22ES%22%7D&sort=%7B%22orderBy%22%3A%22ort%22%7D&page=%7B%22index%22%3A0%7D
		// Alle Orte in DE, sortiert nach Ort, 1. Seite
		// http://localhost:9999/restservices/ort?filter={"land":"DE"}&sort={"orderBy":"ort"}&page={"index":0}
		// http://localhost:9999/restservices/ort?filter=%7B%22land%22%3A%22DE%22%7D&sort=%7B%22orderBy%22%3A%22ort%22%7D&page=%7B%22index%22%3A0%7D
		PageRequest pageRequest = new PageRequest(page.getIndex(), page.getSize(), sort.toDataSort());
		
		//Keywords gesetzt
		if (filter != null && !filter.isEmpty()) {
//			if (filter.getKatalogId() != null) {
//				//KatalogId und Keyword gesetzt
//				log.info("search(" + filter.getKatalogId() + ", " + filter.getKeyword() + ")");
//				return ortRepo.searchByKatalogIdAndKeyword(filter.getKatalogId(), filter.getKeyword(), pageRequest);
//			} else if (filter.getAnwendungId() != null) {
//				//AnwendungId und Keyword gesetzt
//				log.info("searchBySystemAndKeyword(" + filter.getAnwendungId() + ", " + filter.getKeyword() + ")");
//				return katalogwertRepository.searchByAnwendungIdAndKeyword(filter.getAnwendungId(), filter.getKeyword(),pageRequest);
//			} else {
//				//Nur Keyword gesetzt (Suche ueber die Spalten id, schluessel und bezeichnung)
//				log.info("search(" + filter.getKeyword() + ")");
//				return katalogwertRepository.search(filter.getKeyword(), pageRequest);//OK
//			}
			if (filter.getKeyword() != null) {
				log.info("search(" + filter.getKeyword() + ")");
				return wetteraufzeichnungRepo.search(filter.getKeyword(), pageRequest);
			}			
		}
		//Keine Keywords gesetzt
		else {
			if (filter == null){
				log.info("findAll()");
				return wetteraufzeichnungRepo.findAll(pageRequest);//OK
			}
			if (filter.getLand() != null) {
				log.info("searchByLand(" + filter.getLand() + ")");
				return wetteraufzeichnungRepo.searchByLand(filter.getLand(), pageRequest);//OK
			} else if (filter.getOrt() != null) {
				log.info("searchBySOrt(" + filter.getOrt() + ")");
				return wetteraufzeichnungRepo.searchByOrt(filter.getOrt(), pageRequest);//OK
			} 
			
		}		
		
		//default
		log.info("findAll()");
		return wetteraufzeichnungRepo.findAll(pageRequest);//OK
		
	}
	
}
