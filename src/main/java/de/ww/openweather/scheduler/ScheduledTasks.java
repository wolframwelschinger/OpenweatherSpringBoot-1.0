package de.ww.openweather.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.ww.openweather.utils.Constants;
import de.ww.openweather.utils.OpenWeatherMapUtil;
import de.ww.openweather.utils.WetterBeschreibung;
import de.ww.openweather.utils.WetterDTO;
import de.ww.openweather.utils.persistence.Wetteraufzeichnung;
import de.ww.openweather.utils.repositories.WetteraufzeichnungRepository;

/**
 * Scheduler f&uuml;r Tasks 
 * @author Wolfram Welschinger
 *
 */
@Component
public class ScheduledTasks {

	private final Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	private WetteraufzeichnungRepository wetteraufzeichnungRepo;
	
	/**
	 * Speichert st&uuml;ndlich beim Erreichen der 5. Minute die Wetterinformation
	 */
	@Scheduled(cron="0 0 6-20 * * *")
	public void storeWeatherInformation() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		WetterDTO dtoPalma = OpenWeatherMapUtil.getWetter(Constants.url_Palma_de_Mallorca);
		WetterDTO dtoBerlinPankow = OpenWeatherMapUtil.getWetter(Constants.url_Berlin_Pankow);
		storeWetteraufzeichnung(dtoPalma);
		log.info("cron: Wetterinformation fuer Palma gespeichert um {}", dateFormat.format(new Date()));
		storeWetteraufzeichnung(dtoBerlinPankow);
		log.info("cron: Wetterinformation fuer Berlin Pankow gespeichert um {}", dateFormat.format(new Date()));
	}	
	
	/**
	 * Speichert einen Wetterdatensatz	
	 * @param wetterDTO DTO eines Wetterdatensatzes
	 */
	private void storeWetteraufzeichnung(WetterDTO wetterDTO) {
		Wetteraufzeichnung wetter = new Wetteraufzeichnung();
		wetter.setOrt(wetterDTO.getOrt());
		wetter.setLand(wetterDTO.getLand());
		wetter.setGeoBreite(wetterDTO.getGeoBreite());
		wetter.setGeoLaenge(wetterDTO.getGeoLaenge());
		wetter.setTemperatur(wetterDTO.getTemperatur());
		wetter.setTemperatur_min(wetterDTO.getTemperatur_min());
		wetter.setTemperatur_max(wetterDTO.getTemperatur_max());
		wetter.setLuftDruck(wetterDTO.getLuftDruck());
		wetter.setLuftFeuchtigkeit(wetterDTO.getLuftFeuchtigkeit());
		wetter.setWindGeschwindigkeit(wetterDTO.getWindGeschwindigkeit());
		wetter.setWindRichtung(wetterDTO.getWindRichtung());
		wetter.setWolken(wetterDTO.getWolken());
		wetter.setZeitStempel(wetterDTO.getZeitStempel());
		wetter.setZeitString(wetterDTO.getZeitString());
		
		if (wetterDTO.getWetterbeschreibungen().length > 0) {
			for (int y = 0; y < wetterDTO.getWetterbeschreibungen().length; y++) {
				WetterBeschreibung wetterBeschreibung = wetterDTO.getWetterbeschreibungen()[y];
				wetter.setWetterbeschreibung(wetterBeschreibung.getDescription());
				wetter.setIconUrl(wetterBeschreibung.getIconUrl());
			}
		} else {
			log.info("Keine weiteren Wetterinformationen");
		}		
		
		wetteraufzeichnungRepo.save(wetter);	
		log.info("Wetteraufzeichnung fuer " + wetterDTO.getOrt() + " wurde gespeichert");
	}	
	
//	/**
//	 * Gibt alle 5 Sekunden die Uhrzeit aus
//	 */
//	//@Scheduled(cron="")
//	@Scheduled(fixedRate=5000)
//	public void reportCurrentTime() {
//		log.info("fixedRate: Es ist jetzt {}", dateFormat.format(new Date()));
//	}
//
	/**
	 *
	 *
	 * https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
	 * "0 0 * * * *" = the top of every hour of every day.
	 * "*\/10 * * * * *" = every ten seconds." (Ohne Backslash vor dem Teiler!)
	 * "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
	 * "0 0 6,19 * * *" = 6:00 AM and 7:00 PM every day.
	 * "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day.
	 * "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
	 * "0 0 0 25 12 ?" = every Christmas Day at midnight
	 *
	 */
	@Scheduled(cron="*/10 * * * * *")
	public void reportCurrentTimeCron() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy HH:mm:ss");
		log.info("cron: Es ist jetzt {}", dateFormat.format(new Date()));
	}

//	/**
//	 * Speichert st&uuuml;ndlich beim Erreichen der 5. Minute die Wetterinformation
//	 */
//	@Scheduled(cron="0 5 * * * *")
//	public void storeWeatherInformation() {
//		WetterDTO dtoPalma = OpenWeatherMapUtil.getWetter(Constants.url_Palma_de_Mallorca);
//		WetterDTO dtoBerlinPankow = OpenWeatherMapUtil.getWetter(Constants.url_Berlin_Pankow);
//		storeWetteraufzeichnung(dtoPalma);
//		log.info("cron: Wetterinformation fuer Palma gespeichert um {}", dateFormat.format(new Date()));
//		storeWetteraufzeichnung(dtoBerlinPankow);
//		log.info("cron: Wetterinformation fuer Berlin Pankow gespeichert um {}", dateFormat.format(new Date()));
//	}	
	
}
