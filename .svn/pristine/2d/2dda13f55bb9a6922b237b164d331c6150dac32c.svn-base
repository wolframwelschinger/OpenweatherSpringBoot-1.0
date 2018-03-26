package de.ww.openweather.utils;

import java.io.Serializable;

/**
 * DTO f&uuml;r das HTML-Tile der Wettervorhersage
 * @author Wolfram
 *
 */
public class WettervorhersageTileDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2227040375551013783L;
	private String ort;
	private String datumString;
	private double tempMin;
	private double tempMax;
	private String beschreibung;
	private String iconUrl;
	
	/**
	 * Constructor
	 */
	public WettervorhersageTileDTO() {
		super();
	}

	/**
	 * Gibt den Ort zur&uuml;ck.
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
	 * Gibt das Datum als String zur&uuml;ck.
	 * @return Datum als String
	 */
	public String getDatumString() {
		return datumString;
	}

	/**
	 * Setzt den Datums-String.
	 * @param datumString Datum als Zeichenkette
	 */
	public void setDatumString(String datumString) {
		this.datumString = datumString;
	}

	/**
	 * Gibt die minimale Temperatur zur&uuml;ck.
	 * @return minimale Temperatur in ° C
	 */
	public double getTempMin() {
		return tempMin;
	}

	/**
	 * Setzt die minimale Temperatur
	 * @param tempMin minimale Temperatur
	 */
	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}

	/**
	 * Gibt die maximale Temperatur zur&uuml;ck.
	 * @return maximale Temperatur in ° C
	 */
	public double getTempMax() {
		return tempMax;
	}

	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}
	
	/**
	 * Gibt die Wettebeschreibung zur&uuml;ck.
	 * @return Wetterbeschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * Gibt den URL zum Wetter-Icon zur&uuml;ck.
	 * @return URL zum Wetter-Icon
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	/**
	 * Gibt eine String-Representation des Objects zur&uuml;ck.
	 * @return String-Representation des Objects
	 */
	@Override
	public String toString() {
		return "WettervorhersageTileDTO [\ndatumString=" + datumString 
				+ "\n, ort=" + ort
				+ "\n, tempMin=" + tempMin 
				+ ", tempMax=" + tempMax
				+ "\n, beschr.=" + beschreibung
				+ "\n, iconUrl=" + iconUrl
				+ "]\n";
	}
	
}
