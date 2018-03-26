package de.ww.openweather.utils;

import java.io.Serializable;
import java.util.List;

public class OpenweatherListItemDTO implements Serializable {

	/*
	[{"id":2950159,"name":"Berlin"
		,"coord":{"lat":52.517,"lon":13.3889}
		,"main":{"temp":-3.51,"pressure":1025,"humidity":79,"temp_min":-4,"temp_max":-3}
		,"dt":1519192200,"wind":{"speed":2.1,"deg":20}
		,"sys":{"country":"DE"}
		,"rain":null,"snow":null
		,"clouds":{"all":20}
		,"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02n"}]	
	*/
	
	private int id;
	private String name;
	private OpenweatherListItemMainDTO main;
	private OpenweatherListItemSysDTO sys;
	private List<OpenweatherListItemWeatherItemDTO> weather;
	
	public OpenweatherListItemDTO() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public OpenweatherListItemMainDTO getMain() {
		return main;
	}

	public void setMain(OpenweatherListItemMainDTO main) {
		this.main = main;
	}

	public OpenweatherListItemSysDTO getSys() {
		return sys;
	}

	public void setSys(OpenweatherListItemSysDTO sys) {
		this.sys = sys;
	}

	public List<OpenweatherListItemWeatherItemDTO> getWeather() {
		return weather;
	}

	public void setWeather(List<OpenweatherListItemWeatherItemDTO> weather) {
		this.weather = weather;
	}

	@Override
	public String toString() {
		return "OpenweatherListitemDTO [id=" + id + ", name=" + name 
				+ ", sys=" + sys
				+ ", main=" + main 
				+ ", weather=" + weather
				+ "]";
	}
	
}
