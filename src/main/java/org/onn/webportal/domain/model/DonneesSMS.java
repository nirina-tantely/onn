package org.onn.webportal.domain.model;

import java.util.Date;

public class DonneesSMS {

	private String id;
	private String indicateur1;
	private String indicateur2;
	private String indicateur3;
	private Localisation localisation;
	private Date date;
	private Date timeStamp;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndicateur1() {
		return this.indicateur1;
	}

	public void setIndicateur1(String indicateur1) {
		this.indicateur1 = indicateur1;
	}

	public String getIndicateur2() {
		return this.indicateur2;
	}

	public void setIndicateur2(String indicateur2) {
		this.indicateur2 = indicateur2;
	}

	public String getIndicateur3() {
		return this.indicateur3;
	}

	public void setIndicateur3(String indicateur3) {
		this.indicateur3 = indicateur3;
	}

	public Localisation getLocalisation() {
		return this.localisation;
	}

	public void setLocalisation(Localisation localisation) {
		this.localisation = localisation;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}