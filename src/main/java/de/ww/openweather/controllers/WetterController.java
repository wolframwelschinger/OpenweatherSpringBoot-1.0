package de.ww.openweather.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import de.ww.openweather.utils.Constants;
import de.ww.openweather.utils.OpenWeatherMapUtil;
import de.ww.openweather.utils.OpenweatherDTO;
import de.ww.openweather.utils.OpenweatherDateUtil;
import de.ww.openweather.utils.WetterBeschreibung;
import de.ww.openweather.utils.WetterDTO;
import de.ww.openweather.utils.WettervorhersageDTO;
import de.ww.openweather.utils.WettervorhersageEintragDTO;
import de.ww.openweather.utils.WettervorhersageTileDTO;
import de.ww.openweather.utils.persistence.Ort;
import de.ww.openweather.utils.persistence.Wetteraufzeichnung;
import de.ww.openweather.utils.repositories.OrtRepository;
import de.ww.openweather.utils.repositories.WetteraufzeichnungRepository;

@Controller
@RequestMapping("wetter")
public class WetterController {

	// Vorhersage
	//http://api.openweathermap.org/data/2.5/forecast?zip=13187,de&APPID=a69fc6770115d5a82cd0d37e359ad4bf&lang=de&units=metric
	//http://api.openweathermap.org/data/2.5/forecast?zip=13187,de&APPID=a69fc6770115d5a82cd0d37e359ad4bf&lang=de&units=metric&mode=json
	
	@Autowired
	private WetteraufzeichnungRepository wetteraufzeichnungRepo;
	
	@Autowired
	private OrtRepository ortRepo;	
	
	private Logger log = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * Holt das Pankower Wetter mit Hilfe eines REST-Templates
	 * 
	 * URL: http://127.0.0.1:9999/wetter/template
	 * 
	 * @see http://www.baeldung.com/rest-template
	 * 
	 * @return JSON-Response als Pojo
	 */
	@RequestMapping(value="/template", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody OpenweatherDTO getTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = Constants.url_Berlin_Pankow;
		  //= "http://127.0.0.1:9999/wetter/html/berlin_pankow";
		ResponseEntity<String> response
		  = restTemplate.getForEntity(resourceUrl + "/1", String.class);
		//assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		
		OpenweatherDTO owDTO = restTemplate
				  .getForObject(resourceUrl + "/1", OpenweatherDTO.class);
		//assertThat(foo.getName(), notNullValue());
		//assertThat(foo.getId(), is(1L));		
		
		log.debug("--- owDTO:\n" + owDTO.toString());
		
		return owDTO;
		
	}	

	/**
	 * Holt das Pankower Wetter mit Hilfe eines REST-Templates
	 * 
	 * @see http://127.0.0.1:9999/wetter/templateString
	 * 
	 * @return JSON-Response als String
	 */
	@RequestMapping(value="/templateString", method = RequestMethod.GET, produces = "text/html")
	public @ResponseBody String getTemplateString() {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = Constants.url_Berlin_Pankow;
		  //= "http://127.0.0.1:9999/wetter/html/berlin_pankow";
		ResponseEntity<String> response
		  = restTemplate.getForEntity(resourceUrl + "/1", String.class);
		return response.getBody();
		
	}
	
	/**
	 * Gibt das Wetter in Berlin Pankow als HTML-Response zur&uuml;ck
	 * http://127.0.0.1:9999/wetter/html/berlin_pankow
	 * @return das Wetter in Berlin Pankow als HTML-Response
	 */	
	@RequestMapping(value="/html/berlin_pankow", method = RequestMethod.GET, produces = "text/html")
	public @ResponseBody String getWetterBerlin() {
		WetterDTO dtoPankow = OpenWeatherMapUtil.getWetter(Constants.url_Berlin_Pankow);
		storeWetteraufzeichnung(dtoPankow);
		return dtoPankow.getHtml();
	}
	
	/**
	 * Gibt das Wetter in Palma als HTML-Response zur&uuml;ck
	 * http://127.0.0.1:9999/wetter/html/palma
	 * @return das Wetter in Palma als HTML-Response
	 */
	@RequestMapping(value="/html/palma", method = RequestMethod.GET, produces = "text/html")
	public @ResponseBody String getWetterPalma() {
		WetterDTO dtoPalma = OpenWeatherMapUtil.getWetter(Constants.url_Palma_de_Mallorca);
		storeWetteraufzeichnung(dtoPalma);
		return dtoPalma.getHtml();
	}
	
	
	/**
	 * Gibt das Wetter in Palma als JSON-Response zur&uuml;ck
	 * http://127.0.0.1:9999/wetter/json/palma
	 * @return das Wetter in Palma als HTML-Response
	 */
	@RequestMapping(value="/json/palma", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody WetterDTO getJsonWetterPalma() {
		WetterDTO dtoPalma = OpenWeatherMapUtil.getWetter(Constants.url_Palma_de_Mallorca);
		return dtoPalma;
	}	
	
	/**
	 * Gibt die Liste mit Wetteraufzeichnung zur&uuml;ck
	 * @return Wetterdaten als HTML-Tabelle
	 */
	@RequestMapping(value="/html/wetterdaten", method = RequestMethod.GET, produces = "text/html")
	public @ResponseBody String getWetterDaten() {
		List<Wetteraufzeichnung> wetteraufzeichnungen = (List<Wetteraufzeichnung>) wetteraufzeichnungRepo.findAll();
		StringBuilder html = new StringBuilder("<table>\n");
		for (Wetteraufzeichnung wa : wetteraufzeichnungen) {
			String s = "Das Wetter in " + wa.getOrt() 
			+ " um " + wa.getZeitString()
			+ ": Temperatur " + wa.getTemperatur() + "°C"
			+ ", " + wa.getWetterbeschreibung();
			html.append("<tr><td>" + s + "</td><td><img src=\""+ wa.getIconUrl() + "\"/></td></tr>\n");		
			log.debug(s);
		}
		html.append("</table>");
		return html.toString();
	}	

	/**
	 * Gubt die Liste mit Wetteraufzeichnung von Palma zur&uuml;ck
	 * @return Liste mit Wetteraufzeichnung von Palma
	 */	
	@RequestMapping(value="/html/wetterdaten/palma", method = RequestMethod.GET, produces = "text/html")
	public @ResponseBody String getWetterDatenPalma() {
//		List<Wetteraufzeichnung> wetteraufzeichnungen = wetteraufzeichnungRepo.findByOrtOrderByIdDesc("Palma de Mallorca");
		List<Wetteraufzeichnung> wetteraufzeichnungen = wetteraufzeichnungRepo.findByOrtLikeOrderByIdDesc("Palma%");
		StringBuilder html = new StringBuilder("<table>\n");
		for (Wetteraufzeichnung wa : wetteraufzeichnungen) {
			String s = "Das Wetter in " + wa.getOrt() 
			+ " um " + wa.getZeitString()
			+ ": Temperatur " + wa.getTemperatur() + "°C"
			+ ", " + wa.getWetterbeschreibung();
			html.append("<tr><td>" + s + "</td><td><img src=\""+ wa.getIconUrl() + "\"/></td></tr>\n");		
			log.debug(s);
		}
		html.append("</table>");
		return html.toString();
	}		
	
	/**
	 * Speichert einen Wetterdatensatz	
	 * @param wetterDTO DTO eines Wetterdatensatzes
	 */
	private void storeWetteraufzeichnung(WetterDTO wetterDTO) {
		Wetteraufzeichnung wetter = new Wetteraufzeichnung();
		wetter.setOrt(wetterDTO.getOrt());
		wetter.setLand(wetterDTO.getLand());
		wetter.setGeoBreite(wetterDTO.getGeoBreite());
		wetter.setGeoLaenge(wetterDTO.getGeoLaenge());
		wetter.setTemperatur(wetterDTO.getTemperatur());
		wetter.setTemperatur_min(wetterDTO.getTemperatur_min());
		wetter.setTemperatur_max(wetterDTO.getTemperatur_max());
		wetter.setLuftDruck(wetterDTO.getLuftDruck());
		wetter.setLuftFeuchtigkeit(wetterDTO.getLuftFeuchtigkeit());
		wetter.setWindGeschwindigkeit(wetterDTO.getWindGeschwindigkeit());
		wetter.setWindRichtung(wetterDTO.getWindRichtung());
		wetter.setWolken(wetterDTO.getWolken());
		wetter.setZeitStempel(wetterDTO.getZeitStempel());
		wetter.setZeitString(wetterDTO.getZeitString());
		
		if (wetterDTO.getWetterbeschreibungen().length > 0) {
			for (int y = 0; y < wetterDTO.getWetterbeschreibungen().length; y++) {
				WetterBeschreibung wetterBeschreibung = wetterDTO.getWetterbeschreibungen()[y];
				wetter.setWetterbeschreibung(wetterBeschreibung.getDescription());
				wetter.setIconUrl(wetterBeschreibung.getIconUrl());
			}
		} else {
			log.info("Keine weiteren Wetterinformationen");
		}		
		
		wetteraufzeichnungRepo.save(wetter);	
		log.info("Wetteraufzeichnung fuer " + wetterDTO.getOrt() + " wurde gespeichert");
	}
	
	
	// <<<<<<<<<<<<<<<<<<<<<< REST - CALLS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	
	
	/**
	 * Gibt eine Liste mit Wetteraufzeichnungsdaten zur&uuml;ck
	 * http://127.0.0.1:9999/wetter/all
	 * @return List mit Wetterdatens&auml;tzen
	 */
	@RequestMapping(value="/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Wetteraufzeichnung> getWetterdatenAll() {
		log.debug("getWetterdatenAll (URL: /wetter/all) ...");
		List<Wetteraufzeichnung> wetteraufzeichnungen = (List<Wetteraufzeichnung>) wetteraufzeichnungRepo.findAll();
		return wetteraufzeichnungen;
	}
	
	/**
	 * Gibt einen Wetteraufzeichnunsdatensatz anhand seines PK zur&uuml;ck
	 * http://127.0.0.1:9999/wetter/byId/4
	 * @param id PK des Wettersdatensatzes
	 * @return Wetterdatensatz mit PK=4
	 */
	@RequestMapping(value="/byId/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Wetteraufzeichnung getWetterdatenById(@PathVariable Long id) {
		Wetteraufzeichnung wetteraufzeichnung = wetteraufzeichnungRepo.findOne(id);
		return wetteraufzeichnung;
	}	
	
	/**
	 * Gibt das Wetter f&uuml;r den Ort <i>ort</i> als JSON-Response zur&uuml;ck
	 * http://127.0.0.1:9999/restservices/wetterByOrt/Berlin
	 * http://127.0.0.1:9999/#!/wetterByOrt/Palma
	 * @param ort Ort
	 * @return das Wetter in Berlin Pankow als HTML-Response
	 */	
	@RequestMapping(value="/byOrt/{ort}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody WetterDTO getWetterByOrt(@PathVariable String ort) {
		log.debug("wetterByOrt: " + ort);
		WetterDTO dto = null;
		if (ort.toLowerCase().indexOf("berlin") > -1) {
			dto = OpenWeatherMapUtil.getWetter(Constants.url_Berlin_Pankow);
		} else if (ort.toLowerCase().indexOf("palma") > -1) {
			dto = OpenWeatherMapUtil.getWetter(Constants.url_Palma_de_Mallorca);
		} else {
			dto = OpenWeatherMapUtil.getWetter(Constants.url_Berlin_Pankow);
		}
		return dto;
	}		
	
	/**
	 * Gibt das Wetter f&uuml;r den Ort <i>id</i> als JSON-Response zur&uuml;ck
	 * http://127.0.0.1:9999/wetter/wetterByOrtId/1
	 * http://127.0.0.1:9999/wetter/wetterByOrtId/9
	 * @param id PK des Ortes
	 * @return das Wetter des ausgew&auml;hlten Ortes als JSON-Response
	 */	
	@RequestMapping(value="/wetterByOrtId/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody WetterDTO getWetterByOrtId(@PathVariable String id) {
		log.debug("wetterByOrtId: " + id);
		WetterDTO dto = null;		
		
		try {
			Long pk = Long.parseLong(id);
			Ort ort = ortRepo.findOne(pk);
			if (ort != null && ort.getUrlOpenweatherOrtAktuell() != null) {
				log.debug("Wetterdaten fuer " + ort.getOrt() + " abfragen...");
				dto = OpenWeatherMapUtil.getWetter(ort.getUrlOpenweatherOrtAktuell());
				log.debug("Wetter: " + dto.toString());
			} else {
				log.info("Der das Wetter fuer den Ort mit dem PK " + id + " konnte nicht ermittelt werden - Standard Berlin Pankow.");
				dto = OpenWeatherMapUtil.getWetter(Constants.url_Berlin_Pankow);
			}
		} catch (Exception e) {
			log.info("Der das Wetter fuer den Ort mit dem PK " + id + " konnte nicht ermittelt werden - Standard Berlin Pankow.");
			dto = OpenWeatherMapUtil.getWetter(Constants.url_Berlin_Pankow);
		}
			 
		return dto;
	}	
	
	
	/**
	 * Gibt die Wettervorhersage f&uuml;r den Ort <i>ort</i> JSON-Response zur&uuml;ck
	 * http://127.0.0.1:9999/wetter/vorhersage/byOrt/Berlin
	 * http://127.0.0.1:9999/wetter/vorhersage/byOrt/Palma
	 * @param ort Ort
	 * @return das Wetter in Berlin Pankow als JSON-Response
	 */	
	//@RequestMapping(value="/vorhersage/byOrt/{ort}", method = RequestMethod.GET, produces = "application/json")
	@RequestMapping(value="/vorhersage/byOrt/{ort}", method = RequestMethod.GET, produces = "text/html")
	//public @ResponseBody WettervorhersageDTO getWettervorhersageByOrt(@PathVariable String ort) {	
	//public @ResponseBody HashMap<String, WettervorhersageTileDTO> getWettervorhersageByOrt(@PathVariable String ort) {
	public @ResponseBody String getWettervorhersageByOrt(@PathVariable String ort) {
		log.debug("wetter/vorhersage/byOrt: " + ort);
		WettervorhersageDTO dto = null;
		if (ort.toLowerCase().indexOf("berlin") > -1) {
			dto = OpenWeatherMapUtil.getWettervorhersage(Constants.url_Berlin_Pankow_vorhersage);
		} else if (ort.toLowerCase().indexOf("palma") > -1) {
			dto = OpenWeatherMapUtil.getWettervorhersage(Constants.url_Palma_de_Mallorca_vorhersage);
		} else {
			dto = OpenWeatherMapUtil.getWettervorhersage("http://api.openweathermap.org/data/2.5/forecast"+
					"?q=" + ort + "&APPID=a69fc6770115d5a82cd0d37e359ad4bf&lang=de&units=metric&mode=json");
		}
		log.debug(dto.toString());


		//HashMap fuer Tile erzeugen
		HashMap<String, WettervorhersageTileDTO> vorhersageMap = OpenWeatherMapUtil.wettervorhersageDTO2TileHashMap(dto);
		
		//return dto;
		//return vorhersageMap;
		return OpenWeatherMapUtil.vorhersage2HtmlTile(vorhersageMap);
	}	

	
}
