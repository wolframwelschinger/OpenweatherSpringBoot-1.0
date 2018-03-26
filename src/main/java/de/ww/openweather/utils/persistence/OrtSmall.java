package de.ww.openweather.utils.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ort")
public class OrtSmall {

	private long id;
	private String ort;
	private String land;
	
	public OrtSmall() {
		super();
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="ort", length=50)
	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	@Column(name="land", length=50)	
	public String getLand() {
		return land;
	}
	
	public void setLand(String land) {
		this.land = land;
	}

	@Override
	public String toString() {
		return "Ort [id=" + id + ", ort=" + ort + ", land=" + land + "]";
	}

}
