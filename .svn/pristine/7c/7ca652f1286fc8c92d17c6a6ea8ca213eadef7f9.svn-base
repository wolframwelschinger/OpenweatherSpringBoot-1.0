package de.ww.openweather.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

/**
 * DTO f&uuml;r die sechst&auml;gige Wettervorhersage
 * @author Wolfram
 *
 */
public class WettervorhersageDTO {
	
	/*
	cod	"200"
	message	0.0047
	cnt	40
	list	[40]
		dt	1505649600
		main	
			temp	17.93
			temp_min	17.36
			temp_max	17.93
			pressure	1021.33
			sea_level	1027.12
			grnd_level	1021.33
			humidity	79
			temp_kf	0.57
		weather	
			0	
				id	802
				main	"Clouds"
				description	"Überwiegend bewölkt"
				icon	"03d"
		clouds	
			all	36
		wind	
			speed	2.08
			deg	133.003
		sys	
			pod	"d"
			dt_txt	"2017-09-17 12:00:00"
	city	
		name	"Berlin"
		coord	
			lat	52.5731
			lon	13.4171
		country	"DE"	
	*/
	
	private long cod;									// HTTP-Status-Code
	private double message;								// Message-ID
	private long cnt; 									// Anzahl der Listenelemente fuer die Vorhersage
	private List<WettervorhersageEintragDTO> weather;		// Eintrag der Vorhersagen;
	private String cityName;								// Name des Orte
	private double cityGeoLat;							// geografische Breite
	private double cityGeoLon;							// geografische Laenge
	private String cityCountry;							// Land
	
	/**
	 * Constructor
	 */
	public WettervorhersageDTO() {
		super();
		if (weather == null) {
			weather = new ArrayList<WettervorhersageEintragDTO>();
		} else {
			weather.clear();
		}
	}

	/**
	 * Gibt den HTTP-Statuscode zur&uuml;ck,
	 * @return HTTP-Statuscode
	 */
	public long getCod() {
		return cod;
	}

	public void setCod(long cod) {
		this.cod = cod;
	}

	/**
	 * Gibt die Message-ID zur&uuml;ck.
	 * @return Message-ID
	 */
	public double getMessage() {
		return message;
	}

	public void setMessage(double message) {
		this.message = message;
	}

	/**
	 * Die Anzahl der Listenelemente der Vorhersage zur&uuml;ck.
	 * @return Anzahl der Listenelemente der Vorhersage
	 */
	public long getCnt() {
		return cnt;
	}

	public void setCnt(long cnt) {
		this.cnt = cnt;
	}

	/**
	 * Gibt eine Liste mit den einzelnen Datens&auml;tzen der Wettervorhersage zur&uuml;ck.
	 * @return Liste mit den einzelnen Datens&auml;tzen der Wettervorhersage
	 */
	public List<WettervorhersageEintragDTO> getWeather() {
		return weather;
	}

	public void setWeather(List<WettervorhersageEintragDTO> weather) {
		this.weather = weather;
	}

	/**
	 * Gibt den Namen des Ortes der Wettervorhersage zur&uuml;ck
	 * @return Name des Ortes der Wettervorhersage 
	 */
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * Gibt die geografische Breite des Ortes zur&uuml;ck.
	 * @return geografische Breite des Ortes
	 */
	public double getCityGeoLat() {
		return cityGeoLat;
	}

	public void setCityGeoLat(double cityGeoLat) {
		this.cityGeoLat = cityGeoLat;
	}

	/**
	 * Gibt die geografische L&auml;nge des Ortes zur&uuml;ck.
	 * @return geografische L&auml;nge des Ortes
	 */
	public double getCityGeoLon() {
		return cityGeoLon;
	}

	public void setCityGeoLon(double cityGeoLon) {
		this.cityGeoLon = cityGeoLon;
	}

	/**
	 * Gibt das Land, in dem der Ort liegt zur&uuml;ck.
	 * @return Land, in dem der Ort liegt
	 */
	public String getCityCountry() {
		return cityCountry;
	}

	public void setCityCountry(String cityCountry) {
		this.cityCountry = cityCountry;
	}
	
	/**
	 * Gibt eine String-Representation des Objekts zur&uuml;ck.
	 * @return String-Representation des Objekts
	 */
	public String toString() {
		StringBuilder entries = new StringBuilder();
		boolean hasWeather = false;
		if (this.getWeather() != null && weather.size() >0) {
			hasWeather = true;
			for (int i=0; i< weather.size(); i++) {
				entries.append("  " + weather.get(i).toString() + "\n");
			}
		}
		return "[cod: " + cod
				+ ", message: " + message + "\n"
				+ ", cnt: " + cnt + "\n"
				+ ", city: [name: " + cityName + "\n"
				+ ", country: " + cityCountry + "\n"
				+ ", lat: " + cityGeoLat + "\n"
				+ ", lon: " + cityGeoLon + "\n"
				+ (hasWeather ? entries.toString() : "")
				+ "]"
		;
	}
	
	
}
