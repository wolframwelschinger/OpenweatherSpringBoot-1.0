package de.ww.openweather.controllers;

import java.beans.PropertyEditorSupport;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hilfsklasse zum Mapping von JSON-Strings auf Param-Klassen
 * 
 * @author Michael Engelhardt, Wolfram Welschinger
 *
 * @param <V> JSON-Typ
 */
public class JsonMappingEditor<V> extends PropertyEditorSupport {

	Logger log = LogManager.getLogger(this.getClass());

	private Class<V> valueType;

	/**
	 * Constructor
	 * @param valueType Typ
	 */
	public JsonMappingEditor(Class<V> valueType) {
		this.valueType = valueType;
	}

	/**
	 * Setzt die JSON-Representation des Typs
	 * @param json JSON-Representation des Typs 
	 */
	@Override
	public void setAsText(String json) throws IllegalArgumentException {
		ObjectMapper jsonMapper = new ObjectMapper();
		try {
			log.info("Erzeuge " + valueType + " aus " + json);
			V value = jsonMapper.readValue(json, valueType);
			setValue(value);
		} catch (IOException ex) {
			throw new IllegalArgumentException(String
					.format("error reading json: %s", json), ex);
		}
	}

	/**
	 * Gibt den Wert des Typs zur&uuml;ck
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V getValue() {
		return (V) super.getValue();
	}
}
