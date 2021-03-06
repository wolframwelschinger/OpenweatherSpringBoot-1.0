package de.ww.openweather.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.GregorianCalendar;
import com.itextpdf.text.log.SysoCounter;

/**
 * Utility-Klasse f&uuml;r Datums-Operationen
 * 
 * @author Wolfram
 *
 */
public class OpenweatherDateUtil {

	/**
	 * Gibt eine List&lt;String&gt; mit sechs Datumsangaben beginnend mit dem aktuellen Tagesdatum zur&uuml;ck. 
	 * @return List&lt;String&gt; mit sechs Datumsangaben beginnend mit dem aktuellen Tagesdatu
	 */
	public static List<String> getDatumsStringRange(){
		List<String> datumsRange = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date today = new Date();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(today);
		System.out.println("Datum: " + sdf.format(cal.getTime()));
		datumsRange.add(sdf.format(cal.getTime()));
		for (int i=1; i<=5; i++) {
			cal.roll(Calendar.DAY_OF_YEAR, 1);
			datumsRange.add(sdf.format(cal.getTime()));
		}
		return datumsRange;
	}
	
	public static void main(String[] args) {
		
		List<String> datumsStringRange = OpenweatherDateUtil.getDatumsStringRange();
		for (String datumsString : datumsStringRange) {
			System.out.println("Datum: " + datumsString);
		}

	}

}
