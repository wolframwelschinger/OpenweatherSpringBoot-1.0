package de.ww.openweather.utils;

/**
 * DTO f&uuml;r die Beschreibung des tagesaktuellen Wetters
 * @author Wolfram
 *
 */
public class WetterBeschreibung {
	
	private int id;
	private String main;
	private String description;
	private String iconUrl;
	
	public WetterBeschreibung(int id, String main, String description, String iconUrl) {
		super();
		this.id = id;
		this.main = main;
		this.description = description;
		this.iconUrl = iconUrl;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
	public String toString() {
		return ""
			+ "Beschreibung        : " + description + "\n"
			+ "Icon                : " + iconUrl + "\n"
			;
	}
	
}
