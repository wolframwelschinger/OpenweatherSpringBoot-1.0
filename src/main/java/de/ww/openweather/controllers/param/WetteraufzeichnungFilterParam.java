package de.ww.openweather.controllers.param;

import org.apache.commons.lang3.StringUtils;

//import org.apache.commons.lang3.StringUtils;

/**
 * Filterparam für die Enit&auml;t Wetteraufzeichnung, muss in RestControllerAdvice gebunden werden!
 * @author Wolfram Welschinger
 *
 */
public class WetteraufzeichnungFilterParam {

	private String keyword;
	private Long id;
	private String ort;
	private String land;
	private double geoLaenge;
	private double geoBreite;
	private long zeitStempel;
	private String zeitString;
	private long temperatur;
	private long temperatur_min;
	private long temperatur_max;
	private double luftFeuchtigkeit;
	private double luftDruck;
	private double windGeschwindigkeit;
	private double windRichtung;
	private long wolken;
	private String wetterbeschreibung;
	private String iconUrl;
    
	/**
	 * Constructor
	 */
	public WetteraufzeichnungFilterParam() {
	}

	/**
	 * Constructor
	 * @param keyword Keyword
	 */
	public WetteraufzeichnungFilterParam(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * Gibt das Keyword zur&uuml;ck
	 * @return Keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * Setzt das Keyword
	 * @param keyword Keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * Gibt die ID zur&uuml;ck
	 * @return ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setzt die ID
	 * @param id ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gibt den Ort zur&uuml;ck
	 * @return Ort
	 */
	public String getOrt() {
		return ort;
	}

	/**
	 * Setzt den Ort
	 * @param ort Ort
	 */
	public void setOrt(String ort) {
		this.ort = ort;
	}

	/**
	 * Gibt das Land zur&uuml;ck
	 * @return Land
	 */
	public String getLand() {
		return land;
	}

	/**
	 * Setzt das Land
	 * @param land Land
	 */
	public void setLand(String land) {
		this.land = land;
	}

	/**
	 * Gibt die geografische L&auml;nge des Ortes zur&uuml;ck
	 * @return geografische L&auml;nge des Ortes
	 */
	public double getGeoLaenge() {
		return geoLaenge;
	}

	/**
	 * Setzt die geografische L&auml;nge des Ortes
	 * @param geoLaenge geografische L&auml;nge des Ortes
	 */
	public void setGeoLaenge(double geoLaenge) {
		this.geoLaenge = geoLaenge;
	}

	/**
	 * Gibt geografische Breite des Ortes
	 * @return geografische Breite des Ortes
	 */
	public double getGeoBreite() {
		return geoBreite;
	}

	/**
	 * Setzt die geografische Breite des Ortes
	 * @param geoBreite geografische Breite des Ortes
	 */
	public void setGeoBreite(double geoBreite) {
		this.geoBreite = geoBreite;
	}

	/**
	 * Gibt den Timestamp des REST-Service-Calls zur&uuml;ck
	 * @return Timestamp des REST-Service-Calls zur&uuml;ck
	 */
	public long getZeitStempel() {
		return zeitStempel;
	}

	/**
	 * Setzt den Timestamp des REST-Service-Calls
	 * @param zeitStempel Timestamp des REST-Service-Calls
	 */
	public void setZeitStempel(long zeitStempel) {
		this.zeitStempel = zeitStempel;
	}

	/**
	 * Gibt den Timestamp des REST-Service-Calls als String zur&uuml;ck
	 * @return Timestamp des REST-Service-Calls als String
	 */
	public String getZeitString() {
		return zeitString;
	}

	/**
	 * Setzt den Timestamp des REST-Service-Calls als String
	 * @param zeitString Timestamp des REST-Service-Calls als String
	 */
	public void setZeitString(String zeitString) {
		this.zeitString = zeitString;
	}

	/**
	 * Gibt die Temperatur zur&uuml;ck
	 * @return Temperatur
	 */
	public long getTemperatur() {
		return temperatur;
	}

	/**
	 * Setzt die Temperatur
	 * @param temperatur Temperatur
	 */
	public void setTemperatur(long temperatur) {
		this.temperatur = temperatur;
	}

	/**
	 * Gibt die Temperatur (min.) zur&uuml;ck
	 * @return Temperatur (min.)
	 */	
	public long getTemperatur_min() {
		return temperatur_min;
	}

	/**
	 * Setzt die Temperatur (min.)
	 * @param temperatur_min Temperatur (min.)
	 */
	public void setTemperatur_min(long temperatur_min) {
		this.temperatur_min = temperatur_min;
	}

	/**
	 * Gibt die Temperatur (max.) zur&uuml;ck
	 * @return Temperatur (max.)
	 */
	public long getTemperatur_max() {
		return temperatur_max;
	}

	/**
	 * Setzt die Temperatur (max.)
	 * @param temperatur_max Temperatur (max.)
	 */
	public void setTemperatur_max(long temperatur_max) {
		this.temperatur_max = temperatur_max;
	}

	/**
	 * Gibt die Luftfeuchtigkeit zur&uuml;ck
	 * @return Luftfeuchtigkeit
	 */
	public double getLuftFeuchtigkeit() {
		return luftFeuchtigkeit;
	}

	/**
	 * Setzt die Luftfeuchtigkeit
	 * @param luftFeuchtigkeit Luftfeuchtigkeit
	 */
	public void setLuftFeuchtigkeit(double luftFeuchtigkeit) {
		this.luftFeuchtigkeit = luftFeuchtigkeit;
	}

	/**
	 * Gibt den Luftdruck zur&uuml;ck
	 * @return Luftdruck
	 */
	public double getLuftDruck() {
		return luftDruck;
	}

	/**
	 * Setzt den Luftdruck
	 * @param luftDruck Luftdruck
	 */
	public void setLuftDruck(double luftDruck) {
		this.luftDruck = luftDruck;
	}

	/**
	 * Gibt die Windgeschwindigkeit zur&uuml;ck
	 * @return Windgeschwindigkeit
	 */
	public double getWindGeschwindigkeit() {
		return windGeschwindigkeit;
	}

	/**
	 * Setzt die Windgeschwindigkeit
	 * @param windGeschwindigkeit Windgeschwindigkeit
	 */
	public void setWindGeschwindigkeit(double windGeschwindigkeit) {
		this.windGeschwindigkeit = windGeschwindigkeit;
	}

	/**
	 * Gibt die Windrichtung in Grad zur&uuml;ck
	 * @return Windrichtung in Grad
	 */	
	public double getWindRichtung() {
		return windRichtung;
	}

	/**
	 * Setzt die Windrichtung in Grad
	 * @param windRichtung Windrichtung in Grad
	 */
	public void setWindRichtung(double windRichtung) {
		this.windRichtung = windRichtung;
	}

	/**
	 * Gibt den Grad der Bew&ouml;lkung zur&uuml;ck
	 * @return Grad der Bew&ouml;lkung
	 */
	public long getWolken() {
		return wolken;
	}

	/**
	 * Setzt den Grad der Bew&ouml;lkung
	 * @param wolken Grad der Bew&ouml;lkung
	 */
	public void setWolken(long wolken) {
		this.wolken = wolken;
	}

	/**
	 * Gibt die Wetterbeschreibung zur&uuml;ck
	 * @return Wetterbeschreibung
	 */
	public String getWetterbeschreibung() {
		return wetterbeschreibung;
	}

	/**
	 * Setzt die Wetterbeschreibung
	 * @param wetterbeschreibung Wetterbeschreibung
	 */
	public void setWetterbeschreibung(String wetterbeschreibung) {
		this.wetterbeschreibung = wetterbeschreibung;
	}

	/**
	 * Gibt den Link zum Icon zur&uuml;ck
	 * @return Link zum Icon
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * Setzt den Link zum Icon
	 * @param iconUrl Link zum Icon
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	/**
	 * @return true, wenn das Keyword leer ist
	 */
	public boolean isEmpty() {
		return StringUtils.isBlank(getKeyword());
	}
	
	/**
	 * Gibt das Objekt als formatierte Zeichenkette zur&uuml;ck
	 */
	@Override
	public String toString() {
		return "WetteraufzeichnungParam [keyword=" + keyword + ", id=" + id + ", ort=" + ort + ", land=" + land
				+ ", geoLaenge=" + geoLaenge + ", geoBreite=" + geoBreite + ", zeitStempel=" + zeitStempel
				+ ", zeitString=" + zeitString + ", temperatur=" + temperatur + ", temperatur_min=" + temperatur_min
				+ ", temperatur_max=" + temperatur_max + ", luftFeuchtigkeit=" + luftFeuchtigkeit + ", luftDruck="
				+ luftDruck + ", windGeschwindigkeit=" + windGeschwindigkeit + ", windRichtung=" + windRichtung
				+ ", wolken=" + wolken + ", wetterbeschreibung=" + wetterbeschreibung + ", iconUrl=" + iconUrl + "]";
	}

	
	
}