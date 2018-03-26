package de.ww.openweather.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.ww.openweather.controllers.param.OrtFilterParam;
import de.ww.openweather.controllers.param.PageParam;
import de.ww.openweather.controllers.param.SortParam;
import de.ww.openweather.utils.persistence.Ort;
import de.ww.openweather.utils.repositories.OrtRepository;
import de.ww.openweather.utils.repositories.OrtSmallRepository;
/**
 * REST-Controller f&uuml;r CRUD-Operationen der Entity Ort
 * @author Wolfram Welschinger
 *
 */
@RestController
@RequestMapping("/restservices")
public class OrtRestController {

	@Autowired
	private OrtRepository ortRepo;

	@Autowired
	private OrtSmallRepository ortSmallRepo;	
	
//	@Value(value = "classpath:jasperreports/ort_liste.jrxml")
	@Value(value = "classpath:jasperreports/ort_liste_mit_logo.jrxml")	
	private Resource reportJrxml;	
	
	//Property aus application.properties lesen
	@Value("${spring.datasource.url}")
	private String springDatasourceUrl;	
	
	@Value("${spring.datasource.username}")
	private String springDatasourceUsername;		
	
	@Autowired
	DataSource dataSource;
	
	private Logger log = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * Gibt alle Orte zur&uuml;ck
	 * http://127.0.0.1:9999/restservices/ort/all
	 * @return List mit Orten, von denen Wetterdaten abgerufen werden soll
	 */
	@RequestMapping(value="/ort/all")
	public @ResponseBody List<Ort> getListAllOrt() {
		log.debug("getWetterdatenAll (URL: /wetter/all) ...");
		List<Ort> orte = (List<Ort>) ortRepo.findAllOrderByOrt();
		return orte;
	}

	
    /**
     * Liefert eine sortierte und gefilterte Liste von Katalogwerten mit Paging.
     * 
     * http://127.0.0.1:9999/restservices/ort
     * 
     * @param filter Filter
     * @param sort Sortierung
     * @param page Seite
     * @return eine Seite der gefilterten und sortierten Datenmenge von Orten
     */
	@RequestMapping("/ort")
	public Page<Ort> listOrt(
		@RequestParam(required = false) OrtFilterParam filter,
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
			if (filter.getLand() != null) {
				//Ort gesetzt
				log.info("searchByLand(" + filter.getLand() + ")");
				return ortRepo.searchByLand(filter.getLand(), pageRequest);
			} else if (filter.getOrt() != null) {
				//Ort gesetzt
				log.info("searchByOrt(" + filter.getOrt() + ")");
				return ortRepo.searchByOrt(filter.getOrt(), pageRequest);
			} 
			if (filter.getKeyword() != null) {
				log.info("search(" + filter.getKeyword() + ")");
				return ortRepo.search(filter.getKeyword(), pageRequest);
			}
		}
		//Keine Keywords gesetzt
		else {
			if (filter == null){
				log.info("findAll()");
				return ortRepo.findAll(pageRequest);//OK
			}
			if (filter.getLand() != null) {
				log.info("searchByLand(" + filter.getLand() + ")");
				return ortRepo.searchByLand(filter.getLand(), pageRequest);//OK
			} else if (filter.getOrt() != null) {
				log.info("searchBySOrt(" + filter.getOrt() + ")");
				return ortRepo.searchByOrt(filter.getOrt(), pageRequest);//OK
			} 
		}	
		
		//default
		log.info("findAll()");
		return ortRepo.findAll(pageRequest);//OK
	}
	
	/**
	 * Gibt einen Ort anhand des PK zur&uuml;ck
	 * http://127.0.0.1:9999/restservices/ort/1
	 * @param id PK des Ortes
	 * @return Ortdatensatz mit PK=1
	 */
	@RequestMapping(value="/ort/{id}")
	public @ResponseBody Ort getWetterdatenById(@PathVariable Long id) {
		log.debug("Ort-ID: " + id);
		Ort ort = ortRepo.findOne(id);
		
		try {
			Connection conn = dataSource.getConnection();
			log.debug(">>>>>>>>> Datasource: " + conn.getMetaData().getDatabaseProductVersion());
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ort;
	}	
	
	@RequestMapping(value="/ortByName/{ort}")
	public @ResponseBody Ort getWetterdatenById(@PathVariable String ort) {
		log.debug("Ort-ID: " + ort);
		Ort ortEntity = ortRepo.findByOrt(ort).get(0);
		
		try {
			Connection conn = dataSource.getConnection();
			log.debug(">>>>>>>>> Datasource: " + conn.getMetaData().getDatabaseProductVersion());
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ortEntity;
	}
	
	/**
	 * Speichert einen Ort-Datensatz
	 * @param ort Ort-Objekt
	 * @return Gespeichertes Ort-Objekt
	 */
	@RequestMapping(value="/ort", method = RequestMethod.POST)
	public Ort save(@RequestBody Ort ort) {
		log.debug("save - Ort: " + ort.getOrt());
		if (ort.getUrlOpenweatherOrtAktuell() == null && ort.getOrt() != null) {
			ort.setUrlOpenweatherOrtAktuell("http://api.openweathermap.org/data/2.5/find?q=" 
					+ ort.getOrt()+ "&units=metric&type=accurate&mode=json&APPID=a69fc6770115d5a82cd0d37e359ad4bf&lang=de");
		}
		if (ort.getUrlOpenweatherOrtVorhersage() == null && ort.getOrt() != null) {
			ort.setUrlOpenweatherOrtVorhersage("http://api.openweathermap.org/data/2.5/forecast?q=" + 
					ort.getOrt() + "&APPID=a69fc6770115d5a82cd0d37e359ad4bf&lang=de&units=metric&mode=json");
		}
		ortRepo.save(ort);
		return ort;
	}
	
	/**
	 * L&ouml;scht einen Ort-Datensatz
	 * @param id PK des Datensatzes als JSON-Objekt {"deletedId" : pk}
	 * @return DeleteResult-Objekt welches im Attribut id den PK enth&auml;lt
	 */
	@RequestMapping(value = "/ort/{id}", method = RequestMethod.DELETE)
	public DeleteResult delete(@PathVariable Long id) {
		log.debug("delete - zu loeschende ID: " + id);
		ortRepo.delete(id);
		return new DeleteResult(id);  // Uebermitteln der deletedId
	} 	

}
