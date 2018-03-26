package de.ww.openweather.utils;

import java.io.Serializable;
import java.util.List;

public class OpenweatherDTO implements Serializable {
	
	/*
	
	{"message":"accurate"
	,"cod":"200"
	,"count":2
	,"list":[{"id":2950159,"name":"Berlin"
			,"coord":{"lat":52.517,"lon":13.3889}
			,"main":{"temp":-3.51,"pressure":1025,"humidity":79,"temp_min":-4,"temp_max":-3}
			,"dt":1519192200,"wind":{"speed":2.1,"deg":20}
			,"sys":{"country":"DE"}
			,"rain":null,"snow":null
			,"clouds":{"all":20}
			,"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02n"}]
		,{"id":2950158,"name":"Berlin"
			,"coord":{"lat":54.0364,"lon":10.4461}
			,"main":{"temp":-3.21,"pressure":1027,"humidity":100,"temp_min":-4,"temp_max":-2}
			,"dt":1519192200,"wind":{"speed":1,"deg":240}
			,"sys":{"country":"DE"}
			,"rain":null,"snow":null
			,"clouds":{"all":75}
			,"weather":[{"id":701,"main":"Mist","description":"mist","icon":"50n"}]
			}]
	}	
	
	*/
	
	public OpenweatherDTO() {
		super();
	}
	
	private String message;
	private int cod;
	private int count;
	private List<OpenweatherListItemDTO> list;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public List<OpenweatherListItemDTO> getList() {
		return list;
	}

	public void setList(List<OpenweatherListItemDTO> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "OpenweatherDTO [message=" + message + ", cod=" + cod + ", count=" + count + ", list=" + list + "]";
	}

	
}
