package de.ww.openweather.utils;

import java.io.Serializable;

public class OpenweatherListItemWeatherItemDTO implements Serializable {

	//"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02n"}
	
	private int id;
	
	private String main;
	private String description;
	private String icon;
	
	public OpenweatherListItemWeatherItemDTO() {
		super();
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "OpenweatherListitemWeatherItemDTO [id=" + id 
				+ ", main=" + main + ", description=" + description + ", icon="
				+ icon 
				+ "]";
	}
	
}
