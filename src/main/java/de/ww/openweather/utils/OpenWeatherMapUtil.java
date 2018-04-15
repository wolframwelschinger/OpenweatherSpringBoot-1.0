package de.ww.openweather.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * OpenWeatherMapClient
 * @author Wolfram Welschinger
 * @web http://java-buddy.blogspot.com/
 */
public class OpenWeatherMapUtil {


	private static Logger log = LogManager.getLogger("de.ww.openweather.utils.OpenWeatherMapUtil");

	/**
	 * Fragt den OpenweahterMap-REST-Service ab, parst das JSON-Response-Objekt
	 * @param url URL
	 * @return WetterDTO
	 */
	public static WetterDTO getWetter(String url) {
		WetterDTO wetterDTO = null;
		try {
			URL url_weather = new URL(url);
			String result = "";
			HttpURLConnection httpURLConnection = (HttpURLConnection) url_weather.openConnection();
			if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				log.debug("HTTP 200 - o.k.");
				InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream(), Charset.forName("UTF-8"));
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					result += line;
				}
				bufferedReader.close();
				log.debug("Result: \n" + result);
				wetterDTO = parseResult(result);
			} else {
				log.debug("Error in httpURLConnection.getResponseCode()!!!");
			}

			//Vorhersage fetchen
			WettervorhersageDTO wettervorhersageDTO = null;
			if (wetterDTO.getOrt().toLowerCase().indexOf("berlin") > -1) {
				wettervorhersageDTO = OpenWeatherMapUtil.getWettervorhersage(Constants.url_Berlin_Pankow_vorhersage);
			} else if (wetterDTO.getOrt().toLowerCase().indexOf("palma") > -1) {
				wettervorhersageDTO = OpenWeatherMapUtil.getWettervorhersage(Constants.url_Palma_de_Mallorca_vorhersage);
			} else {
				wettervorhersageDTO = OpenWeatherMapUtil.getWettervorhersage("http://api.openweathermap.org/data/2.5/forecast"+
						"?q=" + wetterDTO.getOrt() + "&APPID=a69fc6770115d5a82cd0d37e359ad4bf&lang=de&units=metric&mode=json");
			}
			log.info(">>>>> WettervorhersageDTO: " + wettervorhersageDTO.toString());

			//HashMap fuer Tile erzeugen
			HashMap<String, WettervorhersageTileDTO> vorhersageMap = OpenWeatherMapUtil.wettervorhersageDTO2TileHashMap(wettervorhersageDTO);
			//Tile zuweisen
			wetterDTO.setHtmlVorhersageTile(OpenWeatherMapUtil.vorhersage2HtmlTile(vorhersageMap));


		} catch (MalformedURLException ex) {
			log.error("Fehler: " + ex.getMessage());
		} catch (IOException ex) {
			log.error("Fehler: " + ex.getMessage());
		} catch (JSONException ex) {
			log.error("Fehler: " + ex.getMessage());
		}
		return wetterDTO;
	}

	/**
	 * Fragt den OpenweahterMap-REST-Service ab, parst das JSON-Response-Objekt
	 * @param url URL
	 * @return WettervorhersageDTO
	 */
	public static WettervorhersageDTO getWettervorhersage(String url) {
		WettervorhersageDTO wettervorhersageDTO = null;
		try {
			URL url_weather = new URL(url);
			String result = "";
			HttpURLConnection httpURLConnection = (HttpURLConnection) url_weather.openConnection();
			if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				log.debug("HTTP 200 - o.k.");
				InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream(),Charset.forName("UTF-8"));
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					result += line;
				}
				bufferedReader.close();
				log.debug("Result: \n" + result);
				wettervorhersageDTO = parseVorhersageResult(result);
			} else {
				log.debug("Error in httpURLConnection.getResponseCode()!!!");
			}

		} catch (MalformedURLException ex) {
			log.error("Fehler: " + ex.getMessage());
		} catch (IOException ex) {
			log.error("Fehler: " + ex.getMessage());
		} catch (JSONException ex) {
			log.error("Fehler: " + ex.getMessage());
		}
		return wettervorhersageDTO;
	}

	/**
	 * Parst die JSON-Anwort von OpenWeatherMap
	 * @param json JSON-Antwort
	 * @return WetterDTO
	 * @throws JSONException
	 */
	static private WetterDTO parseResult(String json) throws JSONException {

		WetterDTO wetterDTO = new WetterDTO();

		JSONObject jsonObject = new JSONObject(json);

		// List
		JSONArray jsonList = jsonObject.getJSONArray("list");
		//for (int i = 0; i < jsonList.length(); i++) {
		for (int i = 0; i < 1; i++) {



			JSONObject jsonObj = (JSONObject) jsonList.get(i);

			// name
			String name = (String) jsonObj.get("name");
			wetterDTO.setOrt(name);

			// sys
			JSONObject jsonSys = jsonObj.getJSONObject("sys");
			String country = jsonSys.getString("country");
			wetterDTO.setLand(country);

			// System.out.println("Sonnenaufgang : " + jsonSys.getInt("sunrise"));
			// System.out.println("Sonnenuntergang : " + jsonSys.getInt("sunset"));

			// geografische Koordinaten
			JSONObject jsonCoord = jsonObj.getJSONObject("coord");
			double lat = jsonCoord.getDouble("lat");
			double lon = jsonCoord.getDouble("lon");
			wetterDTO.setGeoLaenge(lon);
			wetterDTO.setGeoBreite(lat);

			// dt
			long dt = jsonObj.getLong("dt");
			wetterDTO.setZeitStempel(dt);
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			Date date = new Date ();
			date.setTime((long)dt*1000);
			wetterDTO.setZeitString(sdf.format(date));

			// main
			JSONObject jsonMain = jsonObj.getJSONObject("main");
			wetterDTO.setTemperatur(jsonMain.getLong("temp"));
			wetterDTO.setTemperatur_min(jsonMain.getLong("temp_min"));
			wetterDTO.setTemperatur_max(jsonMain.getLong("temp_max"));
			wetterDTO.setLuftDruck(jsonMain.getLong("pressure"));
			wetterDTO.setLuftFeuchtigkeit(jsonMain.getLong("humidity"));

			JSONObject jsonWind = jsonObj.getJSONObject("wind");
//			Double result_speed = jsonWind.getDouble("speed");
			wetterDTO.setWindGeschwindigkeit(jsonWind.getDouble("speed"));
			try {
				Double result_deg = jsonWind.getDouble("deg");
				wetterDTO.setWindRichtung(result_deg);
			} catch (Exception e) {
				wetterDTO.setWindRichtung(0);
			}



			//String result_wind = "wind\tspeed: " + result_speed + "\tdeg: " + result_deg;
			//System.out.println("Wind             : " + result_wind);

			// clouds
			JSONObject jsonClouds = jsonObj.getJSONObject("clouds");
//			Long clouds = jsonClouds.getLong("all");
			wetterDTO.setWolken(jsonClouds.getLong("all"));

			JSONArray jsonWeather = jsonObj.getJSONArray("weather");

			if (jsonWeather.length() > 0) {
				WetterBeschreibung[] wetterbeschreibungen = new WetterBeschreibung[jsonWeather.length()];
				for (int y = 0; y < jsonWeather.length(); y++) {
					JSONObject jsonObjWeather = (JSONObject) jsonWeather.get(y);
					//http://openweathermap.org/img/w/04d.png
					wetterbeschreibungen[y] = new WetterBeschreibung(y, jsonObjWeather.getString("main"), jsonObjWeather.getString("description")
							, "http://openweathermap.org/img/w/" + jsonObjWeather.getString("icon") + ".png");
				}
				wetterDTO.setWetterbeschreibungen(wetterbeschreibungen);
			} else {
				log.info("Keine weiteren Wetterinformationen");
			}

		}

		return wetterDTO;

	}

	/**
	 * Parst die JSON-Anwort von OpenWeatherMap
	 * @param json JSON-Antwort
	 * @return WetterDTO
	 * @throws JSONException
	 */
	static private WettervorhersageDTO parseVorhersageResult(String json) throws JSONException {
		log.debug("parseVorhersageResult..");

		WettervorhersageDTO wettervorhersageDTO = new WettervorhersageDTO();
		JSONObject jsonObject = new JSONObject(json);

		try {
			wettervorhersageDTO.setCod(jsonObject.getLong("cod"));
			wettervorhersageDTO.setMessage(jsonObject.getLong("message"));
			wettervorhersageDTO.setCnt(jsonObject.getLong("cnt"));
			// City
			JSONObject jsonCity = jsonObject.getJSONObject("city");
			wettervorhersageDTO.setCityName(jsonCity.getString("name"));
			wettervorhersageDTO.setCityCountry(jsonCity.getString("country"));
			JSONObject jsonCityCoord = jsonCity.getJSONObject("coord");
			wettervorhersageDTO.setCityGeoLat(jsonCityCoord.getDouble("lat"));
			wettervorhersageDTO.setCityGeoLon(jsonCityCoord.getDouble("lon"));

			JSONArray jsonVorhersagen = jsonObject.getJSONArray("list");
			if (jsonVorhersagen != null && jsonVorhersagen.length() >0) {
				for (int i=0; i<jsonVorhersagen.length(); i++) {
					JSONObject jsonEintrag = jsonVorhersagen.getJSONObject(i);
					WettervorhersageEintragDTO eintrag = new WettervorhersageEintragDTO();
					long dt = jsonEintrag.getLong("dt");
					eintrag.setDt(dt);
					SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
					Date date = new Date ();
					date.setTime((long)dt*1000);
					eintrag.setDtString(sdf.format(date));
					// Main
					JSONObject jsonMain = jsonEintrag.getJSONObject("main");
					eintrag.setTemp(jsonMain.getDouble("temp"));
					eintrag.setTemp_min(jsonMain.getDouble("temp_min"));
					eintrag.setTemp_max(jsonMain.getDouble("temp_max"));
					eintrag.setPressure(jsonMain.getDouble("pressure"));
					eintrag.setHumidity(jsonMain.getDouble("humidity"));
					wettervorhersageDTO.getWeather().add(eintrag);
					// Weather
					JSONArray jsonWeather = jsonEintrag.getJSONArray("weather");
					if (jsonWeather.length() > 0) {
						WetterBeschreibung[] wetterbeschreibungen = new WetterBeschreibung[jsonWeather.length()];
						for (int y = 0; y < jsonWeather.length(); y++) {
							JSONObject jsonObjWeather = (JSONObject) jsonWeather.get(y);
							//http://openweathermap.org/img/w/04d.png
							wetterbeschreibungen[y] = new WetterBeschreibung(y, jsonObjWeather.getString("main"), jsonObjWeather.getString("description")
									, "http://openweathermap.org/img/w/" + jsonObjWeather.getString("icon") + ".png");

							log.debug(">>>>>>>>> " + "http://openweathermap.org/img/w/" + jsonObjWeather.getString("icon") + ".png");

						}
						eintrag.setWeather(wetterbeschreibungen);
					} else {
						log.info("Keine weiteren Wetterinformationen");
					}


					// Clouds
					JSONObject jsonClouds = jsonEintrag.getJSONObject("clouds");
					eintrag.setClouds(jsonClouds.getLong("all"));
					// Wind
					JSONObject jsonWind = jsonEintrag.getJSONObject("wind");
					eintrag.setWindSpeed(jsonWind.getLong("speed"));
					eintrag.setWindDeg(jsonWind.getLong("deg"));
					// Sys
					JSONObject jsonSys = jsonEintrag.getJSONObject("sys");
					eintrag.setSysPod(jsonSys.getString("pod"));
					//eintrag.setDt_txt(jsonSys.getString("dt_txt"));
				}
			}

		} catch (Exception e) {
			log.error("Fehler: " + e.getMessage());
		}

		return wettervorhersageDTO;
	}

	/**
	 * Wandelt die Wettervorhersage in eine HashMap Map&lt;String, WettervorhersageTileDTO&gt; um
	 * @param dto WettervorhersageDTO
	 * @return Map&lt;String, WettervorhersageTileDTO&gt;
	 */
	public static HashMap<String, WettervorhersageTileDTO> wettervorhersageDTO2TileHashMap(WettervorhersageDTO dto){

		HashMap<String, WettervorhersageTileDTO> vorhersageMap
				= new HashMap<String, WettervorhersageTileDTO>();				//HasMap für Tile-DTOs (Key=Datum)

		List<WettervorhersageEintragDTO> vorhersagenAllList = dto.getWeather();		//List mit Wettervorhersagedatensaetzen
		List<WettervorhersageEintragDTO> vorhersagenTmpList = new ArrayList<>();	//List mit Wettervorhersagedatensaetzen eines Tages

		String tmpDatum = null;												// aktuelles Schleifendatum
		String tmpDatumOld = "";											// altes Schleifendatum

		WettervorhersageTileDTO tileDTO = null;

		// Iteration ueber die einzelnen Vohersagedatensaetze
		for (WettervorhersageEintragDTO vorhersage : vorhersagenAllList) {

			tmpDatum = vorhersage.getDtString().substring(0, 10);

			// Wechsel des Tagesdatums pruefen
			if (!tmpDatum.equals(tmpDatumOld)) {

				// Datumswechsel mit gefuellter Temperatur-Liste --> Tageszusammenfassung
				if (vorhersagenTmpList != null && vorhersagenTmpList.size() > 0) {
					log.debug("+++++Zusammenfassung fuer den "
							+ tileDTO.getDatumString() + ": "
							+ ", DTO: "  + tileDTO.toString());
					// Map mit Tile-DTO befuellen
					vorhersageMap.put(tileDTO.getDatumString(), tileDTO);
				}


				// Datumswechsel, tempListe-leeren, (neue) Tile-DTO anlegen
				log.info("\n\n>----- Neues Datum: " + tmpDatum);
				vorhersagenTmpList.clear();
				tileDTO = new WettervorhersageTileDTO();
				tileDTO.setDatumString(tmpDatum);
				tileDTO.setOrt(dto.getCityName() + ", " + dto.getCityCountry());
				tileDTO.setBeschreibung(vorhersage.getWeather()[0].getDescription());
				tileDTO.setIconUrl(vorhersage.getWeather()[0].getIconUrl());
				vorhersagenTmpList.add(vorhersage);
			}

			// Temparatur hinzufuegen, Sortierung erneuern
			vorhersagenTmpList.add(vorhersage);
			vorhersagenTmpList.sort(new WettervorhersageEintragDTOComperator());

			// DTO befuellen
			tileDTO.setTempMin(vorhersagenTmpList.get(0).getTemp_min());
			tileDTO.setTempMax(vorhersagenTmpList.get(vorhersagenTmpList.size() -1).getTemp_max());
			tileDTO.setBeschreibung(vorhersage.getWeather()[0].getDescription());

			tileDTO.setIconUrl(vorhersagenTmpList.get(vorhersagenTmpList.size() -1).getWeather()[0].getIconUrl());

			log.debug("----- Datum: " + vorhersage.getDtString() + ", DTO: "
					+ tileDTO.toString());


			tmpDatumOld = tmpDatum;
		}

		// Zusammenfassung letztes Datums
		if (vorhersagenTmpList != null && vorhersagenTmpList.size() > 0) {
			log.debug("+++++ Zusammenfassung fuer den "
					+ tileDTO.getDatumString() + ": "
					+ ", DTO: "  + tileDTO.toString());
			// Map mit Tile-DTO befuellen
			vorhersageMap.put(tileDTO.getDatumString(), tileDTO);
		}

		// Nach Datum sortierte Ausgabe
		List<String> datumsStringRange = OpenweatherDateUtil.getDatumsStringRange();
		for (String datumsString : datumsStringRange) {
			// Anhaengig von der Tageszeit der Abfrage werden morgens 5 Tage,
			// am Nachmittag bzw. Abends 6 Tage dargestellt, da sich das Zeitfenster
			// verschiebt.
			// (Abfrage um 07:00 Uhr -> 38 Vorhersagedatensaetze bis 5 Tag
			// (Abfrage um 08:00 Uhr -> 37 Vorhersagedatensaetze bis 5 Tag
			log.info("\n----------------------------------------------- " + datumsString + " ---\n");
			if (vorhersageMap.containsKey(datumsString)) {
				log.info("\n----------------------------------------------- " + datumsString + " ---\n");
				WettervorhersageTileDTO tmpTileDTO = vorhersageMap.get(datumsString);
				log.info("--- " + tmpTileDTO.getDatumString()
						+ ": Temp(min): " + roundDouble(tmpTileDTO.getTempMin())
						+ ": Temp(max): " + roundDouble(tmpTileDTO.getTempMax())
						+ ": Wetter: " + tmpTileDTO.getBeschreibung()
						+ ": Icon: " + tmpTileDTO.getIconUrl()
				);
			}
			log.info("\n-----------------------------------------------");
		}

		return vorhersageMap;

	}

	/**
	 * Gibt die Wettervohersage als HTML-Tile zur&uuml;ck.
	 * @param vorhersageMap Map&lt;String, WettervorhersageTileDTO&gt;
	 * @return Wettervohersage als HTML-Tile
	 */
	public static String vorhersage2HtmlTile(HashMap<String, WettervorhersageTileDTO> vorhersageMap) {
		StringBuilder html = new StringBuilder();

		html.append("<style type=\"text/css\">/n");
		html.append("td {\n");
		html.append("	text-align: center;\n");
		html.append("	width: 40px;\n");
		html.append("}\n");

		html.append(".td-title {\n");
		html.append("	font-size: 24px;\n");
		html.append("}\n");

		html.append(".td-current {\n");
		html.append("	text-align: center;\n");
		html.append("	width: 60px;\n");
		html.append("	background-color: #efefef;\n");
		html.append("}\n");

		html.append(".low {\n");
		html.append("	text-align: center;\n");
		html.append("	color: #ababab;\n");
		html.append("}\n");
		html.append("</style>\n");

		List<String> datumsStringRange = OpenweatherDateUtil.getDatumsStringRange();

		//WW 10.03.18 23:00:
		//   Nach 22:00 kann fuer das aktuelle Datum keine Wetter-Vorhersage-Info mehr abgerufen werden,
		//   deswegen nach dem ersten Datum suchen, das Vorhersage-Infos enthaelt und Position merken.
		int firstValidInfoPos = 0;
		boolean entryFound = false;
		for (String datumsString : datumsStringRange){
			log.info("Datumsstring: " + datumsString);
			if (!entryFound) {
				if (vorhersageMap.containsKey(datumsString)) {
					entryFound = true;
					log.info("Erster mapEintrag vorhanden fuer: " + datumsString);
				} else {
					log.info("Kein mapEintrag vorhanden fuer: " + datumsString);
					firstValidInfoPos++;
				}
			}
		}

		html.append("<div id=\"vorhersageTile\" style=\"border-color: red; border-style:solid; border-width: 1px; width: 500px;\">\n");
		html.append("<div id=\"ort\" style=\"text-align: center\">\n");
		html.append("		" + vorhersageMap.get(datumsStringRange.get(firstValidInfoPos)).getOrt() + "\n");
		html.append("</div>\n");
		html.append("<div id=\"uebersicht\" style=\"width: 100%\">\n");
		html.append("		<table border=\"0\" style=\"width: 100%;\"><tr>\n");

		int row = 0;
		for (String datumsString : datumsStringRange) {

			if (vorhersageMap.containsKey(datumsString)) {

				WettervorhersageTileDTO tmpTileDTO = vorhersageMap.get(datumsString);
				row++;
				html.append("		<td " + (row ==1 ? "class=\"td-current\"" : "") + ">\n");
				html.append("			<div id=\"tag\" style=\"text-align: center;\">\n");
				html.append("				" + tmpTileDTO.getDatumString().substring(0, 6) + "\n");
				html.append("			</div>\n");
				html.append("			<div id=\"icon\" style=\"text-align: center;\">\n");
				html.append("				<img src=\"" + tmpTileDTO.getIconUrl() + "\" />\n");
				html.append("			</div>\n");
				html.append("			<div id=\"tempBereich\" style=\"text-align: center;\">\n");
				html.append("				" + roundDouble(tmpTileDTO.getTempMax())
						+ "°&nbsp;<spqn class=\"low\">" + roundDouble(tmpTileDTO.getTempMin()) + "°</span>\n");
				html.append("			</div>\n");
				html.append("		</td>\n");

			}
		}

		html.append("	</tr></table>\n");
		html.append("</div>\n");
		html.append("</div>\n");

		return html.toString();
	}

	public static int roundDouble(double value) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(0, RoundingMode.HALF_UP);
		return bd.intValue();
	}

	/**
	 * Vergleicht die Temperatur-Werte der WettervorhersageEintragDTO-Objekte o1, o2.
	 *
	 * @return o1 < o2 --> -1; o1 = o2 --> 0; o1 > o2 1
	 */
	static class WettervorhersageEintragDTOComperator implements Comparator<WettervorhersageEintragDTO>{

		public final int compare(WettervorhersageEintragDTO o1, WettervorhersageEintragDTO o2){
			if (o1.getTemp_max() < o2.getTemp_max()) {
				return -1;
			} else if (o1.getTemp_max() > o2.getTemp_max()) {
				return 1;
			} else {
				return 0;
			}
		}

	}

}
