package de.ww.openweather.utils;

import java.util.List;

import org.json.JSONObject;

/**
 * Ein Eintrag der Wettervorhersage
 * @author Wolfram Welschinger
 *
 */
public class WettervorhersageEintragDTO {
	
	/*
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
	*/
	

	private long dt;									// Zeit-Stempel
	private String dtString;							// Datum, Zeit als String			
	private double temp;								// Temperatur
	private double temp_min;							// Temperatur (min)
	private double temp_max;							// Temperatur (max)
	private double pressure;							// Luftdruck
	private double humidity;							// luftfeuchtigkeit
	private WetterBeschreibung[] weather;		    // beschreibungen	
	private double windSpeed;						// Windgeschwindigkeit;
	private double windDeg;							// Windrichtung;
	private long clouds;								// Wolken;
	private String sysPod;							// Period of Day?
	private String dt_txt;							// Datum-Zeit-String
	
	/**
	 * Constructor
	 */
	public WettervorhersageEintragDTO() {
		super();
	}

	/**
	 * Gibt den Zeitstempel zur&uuml;ck.
	 * @return Zeitstempel
	 */
	public long getDt() {
		return dt;
	}

	/**
	 * Setzt den Zeitstempel.
	 * @param dt Zeitstempel
	 */
	public void setDt(long dt) {
		this.dt = dt;
	}

	/**
	 * Gibt den Zeitstempel als String zur&uuml;ck.
	 * @return Zeitstempel als String
	 */
	public String getDtString() {
		return dtString;
	}

	/**
	 * Setzt den Zeitstempel als String
	 * @param dtString Zeitstempel als String
	 */
	public void setDtString(String dtString) {
		this.dtString = dtString;
	}

	/**
	 * Gibt die Temperatur zur&uuml;ck
	 * @return Temperatur in ° Celsius
	 */
	public double getTemp() {
		return temp;
	}

	/**
	 * Setzt die Temperatur in °C.
	 * @param temp Temperatur in °C
	 */
	public void setTemp(double temp) {
		this.temp = temp;
	}

	/**
	 * Gibt die minimale Temperatur zur&uuml;ck
	 * @return minimale Temperatur in ° Celsius
	 */
	public double getTemp_min() {
		return temp_min;
	}

	/**
	 * Setzt die minimale Temperatur in °C.
	 * @param temp_min minimale Temperatur in °C
	 */
	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}

	/**
	 * Gibt die maximale Temperatur zur&uuml;ck
	 * @return maximale Temperatur in ° Celsius
	 */
	public double getTemp_max() {
		return temp_max;
	}
	
	/**
	 * Setzt die maximale Temperatur in °C.
	 * @param temp_max maximale Temperatur in °C
	 */
	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}

	/**
	 * Gibt den Luftdruck zur&uuml;ck
	 * @return Luftdruck
	 */
	public double getPressure() {
		return pressure;
	}

	/**
	 * Setzt den Luftdruck
	 * @param pressure Luftdruck
	 */
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	
	/**
	 * Gibt die Luftfeuchtigkeit zur&uuml;ck
	 * @return Luftfeuchtigkeit
	 */
	public double getHumidity() {
		return humidity;
	}

	/**
	 * Setzt die Luftfeuchtigkeit
	 * @param humidity Luftfeuchtigkeit
	 */
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	/**
	 * Gibt eine Array mit Wetterbeschreibung zur&uuml;ck.
	 * @return Array mit Wetterbeschreibung
	 */
	public WetterBeschreibung[] getWeather() {
		return weather;
	}

	/**
	 * Belegt das Array mit Wetterbeschreibungen
	 * @param weather Array mit Wetterbeschreibungen
	 */
	public void setWeather(WetterBeschreibung[] weather) {
		this.weather = weather;
	}

	/**
	 * Gibt die Windgeschwindigkeit zur&uuml;ck
	 * @return Windgeschwindigkeit
	 */
	public double getWindSpeed() {
		return windSpeed;
	}

	/**
	 * Setzt die Windgeschwindigkeit
	 * @param windSpeed Windgeschwindigkeit
	 */
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	/**
	 * Gibt die Windrichtung zur&uuml;ck.
	 * @return Windrichtung in Grad
	 */
	public double getWindDeg() {
		return windDeg;
	}

	/**
	 * Setzt die Windrichtung in Grad
	 * @param windDeg Windrichtung in Grad
	 */
	public void setWindDeg(double windDeg) {
		this.windDeg = windDeg;
	}

	/**
	 * Gibt die Bew&ouml;lkung zur&uuml;ck.
	 * @return Bew&ouml;lkung
	 */
	public long getClouds() {
		return clouds;
	}

	/**
	 * Setzt die Bew&ouml;lkung
	 * @param clouds Bew&ouml;lkung
	 */
	public void setClouds(long clouds) {
		this.clouds = clouds;
	}

	public String getSysPod() {
		return sysPod;
	}

	public void setSysPod(String sysPod) {
		this.sysPod = sysPod;
	}

	/**
	 * Gibt Datum und Zeit als Zeichenkette zur&uuml;ck.
	 * @return Datum und Zeit als Zeichenkette zur&uuml;ck
	 */
	public String getDt_txt() {
		return dt_txt;
	}

	/**
	 * Setzt Datum und Zeit als Zeichenkette
	 * @param dt_txt Datum und Zeit als Zeichenkette
	 */
	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}

	/**
	 * Gibt eine String-Represensation des Objekts zur&uuml;ck
	 * 
	 * @return String-Represensation des Objekts
	 */
	public String toString() {
		boolean hasWeather = false;
		StringBuilder sbWeather = new StringBuilder();
		if (weather != null && weather.length > 0) {
			for (int i=0; i< weather.length; i++) {
				hasWeather = true;
				sbWeather.append("\n" + weather[i].toString() + " ");
			}	
		}
		return "["
				+ "dt:" + dt
				+ ", dtString: " + dtString
				+ ", temp: " + temp
				+ ", temp_min:" + temp_min
				+ ", temp_max:" + temp_max
				+ ", pressure: " + pressure
				+ ", humidity: " + humidity
				+ ", clouds: " + clouds
				+ ", windSpeed: " + windSpeed				
				+ ", windDeg: " + windDeg
				+ ", sysPod: "  + sysPod
				+ ", dt_txt" + dt_txt
				+ (hasWeather ? sbWeather.toString() : "")
				+ "]";
	}
	
}
