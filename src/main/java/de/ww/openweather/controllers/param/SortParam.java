package de.ww.openweather.controllers.param;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * Parameter-Klasse f&uuml;r das Sortierung von JPA-Datasets
 * @author wolfram
 *
 */
public class SortParam {

	public static final String DEFAULT_ORDER_BY = "id";

	public static Order createOrder(Direction dir, String field, boolean ignoreCase) {
		Order o = new Order(dir, field);
		return ignoreCase ? o.ignoreCase() : o;
	}

	private String orderBy;
	private boolean reverse;
	private boolean ignoreCase;

	/**
	 * Constructor
	 */
	public SortParam() {
		this(DEFAULT_ORDER_BY);
	}

	/**
	 * Constructor
	 * @param orderBy Sortierreihenfolge
	 */
	public SortParam(String orderBy) {
		this(orderBy, false);
	}

	/**
	 * Constructor
	 * @param orderBy Datenfeld, nach dem sortiert werden soll
	 * @param reverse true wenn absteigend, false wenn aufsteigend sortiert werden soll
	 */
	public SortParam(String orderBy, boolean reverse) {
		this(orderBy, reverse, false);
	}

	/**
	 * Constructor
	 * @param orderBy Datenfeld, nach dem sortiert werden soll
	 * @param reverse true wenn absteigend, false wenn aufsteigend sortiert werden soll
	 * @param ignoreCase true, wenn Gro&szlig;- / Kleinschreibung ignoriert werden soll
	 */
	public SortParam(String orderBy, boolean reverse, boolean ignoreCase) {
		this.orderBy = orderBy;
		this.reverse = reverse;
		this.ignoreCase = ignoreCase;
	}

	/**
	 * Gibt das Datenfeld, nach dem sortiert werden soll zur&uuml;ck
	 * @return Datenfeld, nach dem sortiert werden soll
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * Setzt das Datenfeld, nach dem sortiert werden soll
	 * @param orderBy Datenfeld, nach dem sortiert werden soll
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return gibt true zur&uuml;ck, wenn absteigend sortiert werden soll, anderefalls false
	 */
	public boolean isReverse() {
		return reverse;
	}

	/**
	 * Setter f&uuml;r die Sortierreihenfolge
	 * @param reverse true zur&uuml;ck, wenn absteigend sortiert werden soll, anderefalls false
	 */
	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	/**
	 * Gibt die Sortierreihenfolge (ASC bzw. DESC) zur&uuml;ck
	 * @return ASC true wenn aufsteigend; DESC wenn absteigend soriert werden soll
	 */
	public Direction getDirection() {
		return isReverse() ? Direction.DESC : Direction.ASC;
	}

	/**
	 * @return gibt true, wenn die Gro&szlig;- / Kleinschreibung ignoriert wird
	 */
	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	/**
	 * Regelt, ob Gro&szlig;- / Kleinschreibung ignoriert werden soll
	 * @param ignoreCase true, wenn Gro&szlig;- / Kleinschreibung ignoriert werden soll
	 */
	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	/**
	 * Gibt die Sortierung zur&uuml;ck.
	 * @see #createOrder(Direction, String, boolean)
	 * @return Sortierung
	 */
	public Sort toDataSort() {
		return new Sort(createOrder(getDirection(), getOrderBy(), isIgnoreCase()));
	}

	/**
	 * Gibt das Objekt als formatierte Zeichenkette zur&uuml;ck
	 */
	@Override
	public String toString() {
		return "SortParam [orderBy=" + orderBy + ", reverse=" + reverse + ", ignoreCase=" + ignoreCase + "]";
	}
	
}