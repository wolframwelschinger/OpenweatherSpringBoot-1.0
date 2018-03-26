package de.ww.openweather.utils.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wetteraufzeichnung")
public class Wetteraufzeichnung {

	private long id;
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
	
	public Wetteraufzeichnung() {
		super();
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="ort", length=50)
	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	@Column(name="land", length=50)	
	public String getLand() {
		return land;
	}
	
	public void setLand(String land) {
		this.land = land;
	}
	
	@Column(name="geo_laenge")
	public double getGeoLaenge() {
		return geoLaenge;
	}
	
	public void setGeoLaenge(double geoLaenge) {
		this.geoLaenge = geoLaenge;
	}
	
	@Column(name="geo_breite")
	public double getGeoBreite() {
		return geoBreite;
	}
	public void setGeoBreite(double geoBreite) {
		this.geoBreite = geoBreite;
	}
	
	@Column(name="zeitstempel")
	public long getZeitStempel() {
		return zeitStempel;
	}
	
	public void setZeitStempel(long zeitStempel) {
		this.zeitStempel = zeitStempel;
	}
	
	@Column(name="zeitstempel_string")
	public String getZeitString() {
		return zeitString;
	}
	
	public void setZeitString(String zeitString) {
		this.zeitString = zeitString;
	}
	
	@Column(name="temperatur")
	public long getTemperatur() {
		return temperatur;
	}
	
	public void setTemperatur(long temperatur) {
		this.temperatur = temperatur;
	}
	
	@Column(name="temperatur_min")
	public long getTemperatur_min() {
		return temperatur_min;
	}
	
	public void setTemperatur_min(long temperatur_min) {
		this.temperatur_min = temperatur_min;
	}
	
	@Column(name="temperatur_max")
	public long getTemperatur_max() {
		return temperatur_max;
	}
	
	public void setTemperatur_max(long temperatur_max) {
		this.temperatur_max = temperatur_max;
	}
	
	@Column(name="luftfeuchtigkeit")
	public double getLuftFeuchtigkeit() {
		return luftFeuchtigkeit;
	}
	
	public void setLuftFeuchtigkeit(double luftFeuchtigkeit) {
		this.luftFeuchtigkeit = luftFeuchtigkeit;
	}
	
	@Column(name="luftdruck")
	public double getLuftDruck() {
		return luftDruck;
	}
	
	public void setLuftDruck(double luftDruck) {
		this.luftDruck = luftDruck;
	}
	
	@Column(name="windgeschwindigkeit")
	public double getWindGeschwindigkeit() {
		return windGeschwindigkeit;
	}
	
	public void setWindGeschwindigkeit(double windGeschwindigkeit) {
		this.windGeschwindigkeit = windGeschwindigkeit;
	}
	
	@Column(name="windrichtung")
	public double getWindRichtung() {
		return windRichtung;
	}
	
	public void setWindRichtung(double windRichtung) {
		this.windRichtung = windRichtung;
	}
	
	@Column(name="wolken")
	public long getWolken() {
		return wolken;
	}
	public void setWolken(long wolken) {
		this.wolken = wolken;
	}
	
	@Column(name="Beschreibung")
	public String getWetterbeschreibung() {
		return wetterbeschreibung;
	}
	public void setWetterbeschreibung(String wetterbeschreibung) {
		this.wetterbeschreibung = wetterbeschreibung;
	}

	@Column(name="icon_url")
	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

}
