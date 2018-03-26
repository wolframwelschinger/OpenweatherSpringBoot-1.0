package de.ww.openweather.utils;

import java.util.HashMap;

import org.jfree.util.Log;

/**
 * DTO f&uml;r das tagesaktuelle Wetter 
 * @author Wolfram
 *
 */
public class WetterDTO {

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
	private WetterBeschreibung[] wetterbeschreibungen;
	
	private String htmlVorhersageTile;
	
	public WetterDTO() {
		super();
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public double getGeoLaenge() {
		return geoLaenge;
	}

	public void setGeoLaenge(double geoLaenge) {
		this.geoLaenge = geoLaenge;
	}

	public double getGeoBreite() {
		return geoBreite;
	}

	public void setGeoBreite(double geoBreite) {
		this.geoBreite = geoBreite;
	}

	public long getZeitStempel() {
		return zeitStempel;
	}

	public void setZeitStempel(long zeitStempel) {
		this.zeitStempel = zeitStempel;
	}

	public String getZeitString() {
		return zeitString;
	}

	public void setZeitString(String zeitString) {
		this.zeitString = zeitString;
	}

	public long getTemperatur() {
		return temperatur;
	}

	public void setTemperatur(long temperatur) {
		this.temperatur = temperatur;
	}

	public long getTemperatur_min() {
		return temperatur_min;
	}

	public void setTemperatur_min(long temperatur_min) {
		this.temperatur_min = temperatur_min;
	}

	public long getTemperatur_max() {
		return temperatur_max;
	}

	public void setTemperatur_max(long temperatur_max) {
		this.temperatur_max = temperatur_max;
	}

	public double getLuftFeuchtigkeit() {
		return luftFeuchtigkeit;
	}

	public void setLuftFeuchtigkeit(double luftFeuchtigkeit) {
		this.luftFeuchtigkeit = luftFeuchtigkeit;
	}

	public double getLuftDruck() {
		return luftDruck;
	}

	public void setLuftDruck(double luftDruck) {
		this.luftDruck = luftDruck;
	}

	public double getWindGeschwindigkeit() {
		return windGeschwindigkeit;
	}

	public void setWindGeschwindigkeit(double windGeschwindigkeit) {
		this.windGeschwindigkeit = windGeschwindigkeit;
	}

	public double getWindRichtung() {
		return windRichtung;
	}

	public void setWindRichtung(double windRichtung) {
		this.windRichtung = windRichtung;
	}

	public long getWolken() {
		return wolken;
	}

	public void setWolken(long wolken) {
		this.wolken = wolken;
	}

	public WetterBeschreibung[] getWetterbeschreibungen() {
		return wetterbeschreibungen;
	}

	public void setWetterbeschreibungen(WetterBeschreibung[] wetterbeschreibungen) {
		this.wetterbeschreibungen = wetterbeschreibungen;
	}

	@Override
	public String toString() {
		
		StringBuilder sbWetterbeschreibungen = new StringBuilder("");
		if (wetterbeschreibungen != null && wetterbeschreibungen.length > 0) {
			for (int i=0; i<wetterbeschreibungen.length; i++) {
				sbWetterbeschreibungen.append(wetterbeschreibungen[i].toString());
			}
			
		}
		
		String wetter = "\n"
				+ "Ort                 : " + ort + "\n"
				+ "Land                : " + land + "\n"
				+ "geogr. Breite       : " + geoBreite + "\n"
				+ "geogr. Laenge       : " + geoLaenge + "\n"
				+ "Zeit der Berechnung : " + zeitString + "\n"
				+ "Temperatur          : " + temperatur + "\n"
				+ "Temperatur (min)    : " + temperatur_min + "\n"
				+ "Temperatur (max)    : " + temperatur_max + "\n"
				+ "Luftdruck           : " + luftDruck + " hPa\n"
				+ "Luftfeuchtigkeit    : " + luftFeuchtigkeit + " %\n"	
				+ "Windgeschwindigkeit : " + windGeschwindigkeit + " m/s\n"	
				+ "Windrichtung        : " + windRichtung + " Grad\n"	
				+ "Wolken              : " + wolken + "\n"
				+ sbWetterbeschreibungen
				;
		
		return wetter;
	}
	
	
	public String getHtml() {
		StringBuilder html = new StringBuilder();
		html.append("<html> \n");
	    html.append("<head> \n");
	    	html.append("	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> \n");
	    	html.append("    <!-- Bootstrap core CSS --> \n");
	    	html.append("    <link href=\"../../css/bootstrap.min.css\" rel=\"stylesheet\"> \n");
	    	html.append("    <!-- Bootstrap theme --> \n");
	    	html.append("    <link href=\"../..//css/bootstrap-theme.min.css\" rel=\"stylesheet\"> \n");
	    	html.append("    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug --> \n");
	    	html.append("    <link href=\"../../css/ie10-viewport-bug-workaround.css\" rel=\"stylesheet\"> \n");
	    	html.append("  \n");
	    	html.append("    <!-- Custom styles for this template --> \n");
	    	html.append("    <link href=\"theme.css\" rel=\"stylesheet\"> \n");	
	    	html.append("</head> \n");
	    	html.append(" \n");		
	    	html.append("<body> \n");
	    	html.append(" \n");
	    	html.append("	<div style=\"text-align: center\"> \n");
	    	html.append("		<h2><span class=\"label label-primary\">Das Wetter in " + this.getOrt() + "</span></h2> \n");
	    	html.append("	</div> \n");
	    	html.append("	<br /> \n");
//	    	html.append("	<div class=\"alert alert-success\" role=\"alert\"> \n");
//	    	html.append("    	<strong>HTTP-Statuscode: 200</strong>&nbsp;Der Webservice wurde erfolgreich abgefragt \n");
//	    	html.append("    </div> \n");
	    	
	    	String aktivBerlinPankow = "";
	    	String aktivPalmaDeMallorca = "";
	    	
	    	if (this.getOrt().toLowerCase().contains("pankow")) {aktivBerlinPankow = "class=\"active\"";}
	    	if (this.getOrt().toLowerCase().contains("palma")) {aktivPalmaDeMallorca = "class=\"active\"";}
	    	
	    	html.append("<ul class=\"nav nav-tabs\" role=\"tablist\"> \n");
//	    	html.append("    <li role=\"presentation\" " + aktivBerlinPankow + "><a href=\"http://127.0.0.1:9999/wetter/html/berlin_pankow\"\">Berlin Pankow</a></li> \n");
//	    	html.append("    <li role=\"presentation\" " + aktivPalmaDeMallorca + "><a href=\"http://127.0.0.1:9999/wetter/html/palma\"\">Palma de Mallorca</a></li> \n");
	    	html.append("    <li role=\"presentation\" " + aktivBerlinPankow + "><a href=\"./berlin_pankow\"\">Berlin Pankow</a></li> \n");
	    	html.append("    <li role=\"presentation\" " + aktivPalmaDeMallorca + "><a href=\"./palma\"\">Palma de Mallorca</a></li> \n");
	    	html.append("</ul> \n");	    	
	    
	    	html.append("	<table class=\"table table-bordered\"> \n");
	    	html.append("		<tr><td>Ort</td><td>" + this.getOrt() + "</td> \n");
	    	html.append("		<tr><td>Land</td><td>" + this.getLand() + "</td> \n");
	    	html.append("		<tr><td>geogr. Breite</td><td>" + this.getGeoBreite() + "</td> \n");
	    	html.append("		<tr><td>geogr. Laenge</td><td>" + this.getGeoLaenge() + "</td> \n");
	    	html.append("		<tr><td>Zeit der Berechnung</td><td>" + this.getZeitString() + "</td> \n");
	    	html.append("		<tr><td>Temperatur</td><td>" + this.getTemperatur() + "°C</td> \n");
	    	html.append("		<tr><td>Temperatur (min)</td><td>" + this.getTemperatur_min() + "°C</td> \n");
	    	html.append("		<tr><td>Temperatur (max)</td><td>" + this.getTemperatur_max() + "°C</td> \n");
	    	html.append("		<tr><td>Luftdruck</td><td>" + this.getLuftDruck() + " hPa</td> \n");
	    	html.append("		<tr><td>Luftfeuchtigkeit</td><td>" + this.getLuftFeuchtigkeit() + " %</td> \n");
	    	html.append("		<tr><td>Windgeschwindigkeit</td><td>" + this.getWindGeschwindigkeit() + " m/s</td> \n");
	    	html.append("		<tr><td>Windrichtung</td><td>" + this.getWindRichtung() + " Grad</td> \n");
	    	html.append("		<tr><td>Wolken</td><td>" + this.getWolken() + "</td> \n");
	    	html.append("		<tr><td>Beschreibung</td><td>" + this.getWetterbeschreibungen()[0].getDescription() + "</td> \n");
	    	html.append("		<tr><td>Icon</td><td><img src=\"" + this.getWetterbeschreibungen()[0].getIconUrl() + "\" /></td> \n");
	    html.append("	</table> \n");
	    	html.append("</body> \n");
	    	html.append(" \n");
	    	html.append("</html> \n");		
		
		return html.toString();
	}

	public String getHtmlVorhersageTile() {
		Log.debug("\n---------------------------------HTML-Tile\n" + htmlVorhersageTile);
		return htmlVorhersageTile;
		
	}

	public void setHtmlVorhersageTile(String htmlVorhersageTile) {
		this.htmlVorhersageTile = htmlVorhersageTile;
	}
	
	
	
}
