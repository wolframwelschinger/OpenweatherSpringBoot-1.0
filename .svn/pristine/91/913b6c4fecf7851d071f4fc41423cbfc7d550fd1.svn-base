package de.ww.openweather.controllers.param;

import org.apache.commons.lang3.StringUtils;

//import org.apache.commons.lang3.StringUtils;

/**
 * Filterparam für die Enit&auml;t Ort, muss in RestControllerAdvice gebunden werden!
 * @author Wolfram Welschinger
 *
 */
public class OrtFilterParam {

	private String keyword;
	private Long id;
	private String ort;
	private String land;
	private String urlOpenweatherOrtAktuell;
	private String urlOpenweatherOrtVorhersage;
    
	/**
	 * Constructor
	 */
	public OrtFilterParam() {
	}

	/**
	 * Constructor
	 * @param keyword Keyword
	 */
	public OrtFilterParam(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * Gibt den Keyword-Filter der Suche zur&uuml;ck.
	 * @return Keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	
	/**
	 * Setzt den Keyword-Filter der Suche.
	 * @param keyword Keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * Gibt den PK-Filter der Suche zur&uuml;ck.
	 * @return PK-Filter der Suche
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setzt den PK-Filter der Suche
	 * @param id PK-Filter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gibt den Ort-Filter der Suche zur&uuml;ck.
	 * @return OrtFilter der Suche
	 */
	public String getOrt() {
		return ort;
	}

	/**
	 * Setzt den Ort-Filter der Suche
	 * @param ort Ort-Filter
	 */	
	public void setOrt(String ort) {
		this.ort = ort;
	}

	/**
	 * Gibt den Land-Filter der Suche zur&uuml;ck.
	 * @return LandFilter der Suche
	 */	
	public String getLand() {
		return land;
	}

	/**
	 * Setzt den Land-Filter der Suche.
	 * @param land Land-Filter
	 */	
	public void setLand(String land) {
		this.land = land;
	}

	/**
	 * Gibt den URL-Filter (Wetter aktuell) der Suche zur&uuml;ck.
	 * @return URL-Filter (Wetter aktuell)
	 */		
	public String getUrlOpenweatherOrtAktuell() {
		return urlOpenweatherOrtAktuell;
	}

	/**
	 * Setzt den URL-Filter (Wetter aktuell)
	 * @param urlOpenweatherOrtAktuell URL-Filter (Wetter aktuell)
	 */
	public void setUrlOpenweatherOrtAktuell(String urlOpenweatherOrtAktuell) {
		this.urlOpenweatherOrtAktuell = urlOpenweatherOrtAktuell;
	}

	/**
	 * Gibt den URL-Filter (Wettervorhersage) der Suche zur&uuml;ck.
	 * @return URL-Filter (Wettervorhersage)
	 */		
	public String getUrlOpenweatherOrtVorhersage() {
		return urlOpenweatherOrtVorhersage;
	}

	/**
	 * Setzt den URL-Filter (Wettervorhersage) der Suche
	 * @param urlOpenweatherOrtVorhersage den URL-Filter (Wettervorhersage) der Suche
	 */
	public void setUrlOpenweatherOrtVorhersage(String urlOpenweatherOrtVorhersage) {
		this.urlOpenweatherOrtVorhersage = urlOpenweatherOrtVorhersage;
	}
	
	/**
	 * @return Gibt true zur&uuml;ck, wenn der Keyword-Filter leer ist
	 */
	public boolean isEmpty() {
		return StringUtils.isBlank(getKeyword());
	}
	
	/**
	 * Gibt das Objekt als formatierte Zeichenkette zur&uuml;ck.
	 */
	@Override
	public String toString() {
		return "OrtFilterParam [keyword=" + keyword + ", id=" + id + ", ort=" + ort + ", land=" + land
				+ ", urlOpenweatherOrtAktuell=" + urlOpenweatherOrtAktuell + ", urlOpenweatherOrtVorhersage="
				+ urlOpenweatherOrtVorhersage + "]";
	}

}