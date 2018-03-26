package de.ww.openweather.controllers.param;

/**
 * Parameter-Klasse f&uuml;r das JPA-Paging
 * @author wolfram
 *
 */
public class PageParam {

	public static final int DEFAULT_INDEX = 0;
	public static final int DEFAULT_SIZE = 10;

	private int index; // Nummer der Seite
	private int size; // Anzahl der Datensaetze pro Seite

	/**
	 * Constructor mit Default-Vorbelegung (Seite 1, max. 10 Datens&auml;tze)
	 */
	public PageParam() {
		this(DEFAULT_INDEX);
	}

	/**
	 * Constructor
	 * 
	 * @param index
	 *            Seitennummer
	 */
	public PageParam(int index) {
		this(index, DEFAULT_SIZE);
	}

	/**
	 * Constructor
	 * 
	 * @param index
	 *            Seitennummer
	 * @param size
	 *            Anzahl der Datens&auml;tze pro Seite
	 */
	public PageParam(int index, int size) {
		this.index = index;
		this.size = size;
	}

	/**
	 * Gibt die Seite zur&uuml;ck
	 * 
	 * @return Seite
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Setzt die Seite
	 * 
	 * @param index
	 *            Seite
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Gibt die Anzahl der Datens&auml;tze pro Seite zur&uuml;ck
	 * 
	 * @return Anzahl der Datens&auml;tze pro Seite
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Setzt die Anzahl der Datens&auml;tze pro Seite
	 * 
	 * @param size
	 *            Anzahl der Datens&auml;tze pro Seite
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Gibt die String-Repr&auml;sentation des JSON-Parameter-Valueobjekts
	 * zur&uuml;ck
	 * 
	 * @return String-Repr&auml;sentation des JSON-Parameter-Valueobjekts
	 */
	@Override
	public String toString() {
		return "PageParam [index=" + index + ", size=" + size + "]";
	}
}
